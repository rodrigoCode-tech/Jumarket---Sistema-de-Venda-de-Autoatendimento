package com.desafio.jumarket.Services

import com.desafio.jumarket.DTOs.CarrinhoDTO
import com.desafio.jumarket.DTOs.ItemCarrinhoDTO
import com.desafio.jumarket.Models.ItemCarrinho

interface CarrinhoService {

    fun listarItensPorCliente(clienteId: Long): List<ItemCarrinhoDTO>
    fun excluirCarrinho(id: Long)
    fun adicionarProduto(clienteId : Long, carrinhoId: Long, itemCarrinhoDTO: ItemCarrinhoDTO): CarrinhoDTO
    fun removerProduto(carrinhoId: Long, produtoId: Long): CarrinhoDTO
}