package com.desafio.jumarket.services

import com.desafio.jumarket.dtos.CarrinhoDTO
import com.desafio.jumarket.dtos.ItemCarrinhoDTO

interface CarrinhoService {

    fun listarItensPorCliente(clienteId: Long): List<ItemCarrinhoDTO>
    fun excluirCarrinho(id: Long)
    fun adicionarProduto(clienteId : Long, carrinhoId: Long, itemCarrinhoDTO: ItemCarrinhoDTO): CarrinhoDTO
    fun removerProduto(carrinhoId: Long, produtoId: Long): CarrinhoDTO
}