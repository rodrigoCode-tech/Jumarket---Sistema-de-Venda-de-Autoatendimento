package com.desafio.jumarket.services.impl

import com.desafio.jumarket.dtos.CarrinhoDTO
import com.desafio.jumarket.dtos.ItemCarrinhoDTO
import com.desafio.jumarket.models.ItemCarrinho
import com.desafio.jumarket.repositories.CarrinhoRepository
import com.desafio.jumarket.repositories.ClienteRepository
import com.desafio.jumarket.repositories.ProdutoRepository
import com.desafio.jumarket.services.CarrinhoService
import org.springframework.stereotype.Service

@Service
class CarrinhoServiceImp(
    private val carrinhoRepository: CarrinhoRepository,
    private val clienteRepository: ClienteRepository,
    private val produtoRepository: ProdutoRepository
) : CarrinhoService{

    override fun listarItensPorCliente(clienteId: Long): List<ItemCarrinhoDTO> {
        val cliente = clienteRepository.findById(clienteId)
            .orElseThrow { RuntimeException("Cliente não encontrado") }

        val carrinhosDoCliente = carrinhoRepository.findByCliente(clienteId)

        val itensCarrinho = carrinhosDoCliente.flatMap { carrinho ->
            carrinho.itens.map { itemCarrinho ->
                ItemCarrinhoDTO(
                    produtoId = itemCarrinho.produto.id,
                    quantidade = itemCarrinho.quantidade
                )
            }
        }

        return itensCarrinho
    }

    override fun excluirCarrinho(id: Long) {
        carrinhoRepository.deleteById(id)
    }

    override fun adicionarProduto(clienteId: Long, carrinhoId: Long, itemCarrinhoDTO: ItemCarrinhoDTO): CarrinhoDTO {
        val cliente = clienteRepository.findById(clienteId)
            .orElseThrow { RuntimeException("Cliente não encontrado") }

        val carrinho = carrinhoRepository.findByCliente(clienteId)
            .find { it.id == carrinhoId }
            ?: throw RuntimeException("Carrinho não encontrado para o cliente informado")

        val produto = produtoRepository.findById(itemCarrinhoDTO.produtoId!!)
            .orElseThrow { RuntimeException("Produto não encontrado") }

        val itemCarrinho = ItemCarrinho(produto = produto, quantidade = itemCarrinhoDTO.quantidade)
        carrinho.itens.add(itemCarrinho)

        val carrinhoAtualizado = carrinhoRepository.save(carrinho)

        return CarrinhoDTO(
            id = carrinhoAtualizado.id,
            clienteId = carrinhoAtualizado.cliente.id,
            produtos = carrinhoAtualizado.itens.map { item ->
                ItemCarrinhoDTO(
                    produtoId = item.produto.id,
                    quantidade = item.quantidade
                )
            }
        )
    }

    override fun removerProduto(carrinhoId: Long, produtoId: Long): CarrinhoDTO {
        val carrinho = carrinhoRepository.findById(carrinhoId)
            .orElseThrow { RuntimeException("Carrinho não encontrado") }

        val itemCarrinho = carrinho.itens.find { it.produto.id == produtoId }
            ?: throw RuntimeException("Produto não encontrado no carrinho")

        carrinho.itens.remove(itemCarrinho)
        carrinhoRepository.save(carrinho)

        return CarrinhoDTO(
            id = carrinho.id,
            clienteId = carrinho.cliente.id,
            produtos = carrinho.itens.map { item ->
                ItemCarrinhoDTO(
                    produtoId = item.produto.id,
                    quantidade = item.quantidade
                )
            }
        )
    }
}