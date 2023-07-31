package com.desafio.jumarket.services.impl

import com.desafio.jumarket.dtos.ItemVendaDTO
import com.desafio.jumarket.dtos.ItemVendaProdutoDTO
import com.desafio.jumarket.dtos.VendaDTO
import com.desafio.jumarket.enums.FormaDePagamento
import com.desafio.jumarket.exception.DataNotFoundException
import com.desafio.jumarket.models.Carrinho
import com.desafio.jumarket.models.ItemVenda
import com.desafio.jumarket.models.Venda
import com.desafio.jumarket.repositories.CarrinhoRepository
import com.desafio.jumarket.repositories.VendaRepository
import com.desafio.jumarket.services.VendaService
import org.springframework.stereotype.Service

@Service
class VendaServiceImpl(
    private val vendaRepository: VendaRepository,
    private val carrinhoRepository: CarrinhoRepository
) : VendaService {
    override fun finalizarVenda(formaDePagamento: FormaDePagamento, carrinhoId: Long): VendaDTO {
        val carrinho = carrinhoRepository.findById(carrinhoId)
            .orElseThrow { DataNotFoundException("Carrinho não encontrado para o cliente") }

        val valorTotal = calcularValorTotal(carrinho)

        var venda = Venda(
            cliente = carrinho.cliente,
            valorTotal = valorTotal,
            formaDePagamento = formaDePagamento
        )
        val itens = carrinho.itens.map { item ->
            ItemVenda(
                produto = item.produto,
                quantidade = item.quantidade,
                venda = venda
            )
        }.toMutableList()
        venda.itens.addAll(itens)

        venda = vendaRepository.save(venda)
        carrinhoRepository.delete(carrinho)
        return mapToDto(venda)
    }

    override fun listarVendas(): List<VendaDTO> {
        return vendaRepository.findAll()
            .map { venda -> mapToDto(venda) }
    }

    fun calcularValorTotal(carrinho: Carrinho): Double {
        var valorTotal = 0.0
        carrinho.itens.forEach { produtoQuantidade ->
            valorTotal += produtoQuantidade.produto.precoUnitario * produtoQuantidade.quantidade
        }
        return valorTotal
    }

    private fun mapToDto(venda: Venda) = venda.let {
        VendaDTO(
            id = it.id,
            valorTotal = it.valorTotal,
            formaPagamento = it.formaDePagamento.name,
            itens = it.itens.map { item ->
                ItemVendaDTO(
                    id = item.id,
                    produto = item.produto.run { ItemVendaProdutoDTO(id!!, nome) },
                    quantidade = item.quantidade
                )
            })
    }
}