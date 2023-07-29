package com.desafio.jumarket.services.impl

import com.desafio.jumarket.enums.FormaDePagamento
import com.desafio.jumarket.models.Carrinho
import com.desafio.jumarket.models.Venda
import com.desafio.jumarket.repositories.CarrinhoRepository
import com.desafio.jumarket.repositories.ClienteRepository
import com.desafio.jumarket.repositories.VendaRepository
import com.desafio.jumarket.services.VendaService
import org.springframework.stereotype.Service

@Service
class VendaServiceImpl (
    private val vendaRepository: VendaRepository,
    private val carrinhoRepository: CarrinhoRepository,
    private val clienteRepository: ClienteRepository
)   : VendaService{
    override fun finalizarVenda(formaDePagamento: FormaDePagamento, usuarioId: Long): Venda {
        val cliente = clienteRepository.findById(usuarioId)
            .orElseThrow { RuntimeException("Cliente não encontrado") }

        val carrinho = carrinhoRepository.findByCliente(usuarioId).firstOrNull()
            ?: throw RuntimeException("Carrinho não encontrado para o cliente")

        val valorTotal = calcularValorTotal(carrinho)

        val venda = Venda(
            carrinho = carrinho,
            valorTotal = valorTotal,
            formaDePagamento = formaDePagamento,
        )
        carrinhoRepository.delete(carrinho)

        return vendaRepository.save(venda)

    }

    override fun listarVendas(): List<Venda> {
        return vendaRepository.findAll()
    }

    private fun calcularValorTotal(carrinho: Carrinho): Double {
        var valorTotal = 0.0
        carrinho.itens.forEach { produtoQuantidade ->
            valorTotal += produtoQuantidade.produto.precoUnitario * produtoQuantidade.quantidade
        }
        return valorTotal
    }

}