package com.desafio.jumarket.services.impl

import com.desafio.jumarket.dtos.CarrinhoDTO
import com.desafio.jumarket.dtos.ClienteDTO
import com.desafio.jumarket.dtos.ItemCarrinhoDTO
import com.desafio.jumarket.dtos.ItemCarrinhoProdutoDTO
import com.desafio.jumarket.exception.DataNotFoundException
import com.desafio.jumarket.models.Carrinho
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
) : CarrinhoService {

    override fun abrirCarrinho(clienteId: Long): CarrinhoDTO {
        val cliente = clienteRepository.findById(clienteId)
            .orElseThrow { DataNotFoundException("Cliente não encontrado") }

        val carrinho = carrinhoRepository.save(Carrinho(cliente = cliente))

        return carrinho.let {
            CarrinhoDTO(
                id = it.id,
                cliente = it.cliente.run { ClienteDTO(id, nome, cpf) }
            )
        }
    }

    override fun listarItensPorCliente(clienteId: Long): List<ItemCarrinhoDTO> {

        val existeCliente = clienteRepository.existsById(clienteId)
        if (!existeCliente) {
            throw DataNotFoundException("Cliente não encontrado")
        }

        val carrinhosDoCliente = carrinhoRepository.findByCliente(clienteId)

        val itensCarrinho = carrinhosDoCliente.flatMap { carrinho ->
            carrinho.itens.map { itemCarrinho ->
                ItemCarrinhoDTO(
                    id = itemCarrinho.id,
                    produto = itemCarrinho.produto.run { ItemCarrinhoProdutoDTO(id!!, nome) },
                    quantidade = itemCarrinho.quantidade
                )
            }
        }

        return itensCarrinho
    }

    override fun excluirCarrinho(id: Long) {
        getCarrinho(id)
        carrinhoRepository.deleteById(id)
    }

    override fun adicionarProduto(carrinhoId: Long, itemCarrinhoDTO: ItemCarrinhoDTO): CarrinhoDTO {
        var carrinho = getCarrinho(carrinhoId)

        val produto = produtoRepository.findById(itemCarrinhoDTO.produto.id)
            .orElseThrow { DataNotFoundException("Produto não encontrado") }

        val itemCarrinho = ItemCarrinho(produto = produto, quantidade = itemCarrinhoDTO.quantidade)
        carrinho.itens.add(itemCarrinho)

        carrinho = carrinhoRepository.save(carrinho)

        return carrinho.let {
            CarrinhoDTO(
                id = it.id,
                cliente = it.cliente.run { ClienteDTO(id, nome, cpf) },
                produtos = it.itens.map { item ->
                    ItemCarrinhoDTO(
                        id = item.id,
                        produto = item.produto.run { ItemCarrinhoProdutoDTO(id!!, nome) },
                        quantidade = item.quantidade
                    )
                }
            )
        }
    }

    override fun removerItem(carrinhoId: Long, itemId: Long): CarrinhoDTO {
        val carrinho = getCarrinho(carrinhoId)

        val itemCarrinho = carrinho.itens.find { it.id == itemId }
            ?: throw DataNotFoundException("Item não encontrado no carrinho")

        carrinho.itens.remove(itemCarrinho)
        carrinhoRepository.save(carrinho)

        return carrinho.let {
            CarrinhoDTO(
                id = it.id,
                cliente = it.cliente.run { ClienteDTO(id, nome, cpf) },
                produtos = it.itens.map { item ->
                    ItemCarrinhoDTO(
                        id = item.id,
                        produto = item.produto.run { ItemCarrinhoProdutoDTO(id!!, nome) },
                        quantidade = item.quantidade
                    )
                }
            )
        }
    }

    fun getCarrinho(carrinhoId: Long) = carrinhoRepository.findById(carrinhoId)
        .orElseThrow { DataNotFoundException("Carrinho não encontrado") }
}