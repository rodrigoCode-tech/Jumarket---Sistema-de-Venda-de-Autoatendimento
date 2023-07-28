package com.desafio.jumarket.Services

import com.desafio.jumarket.DTOs.CarrinhoDTO
import com.desafio.jumarket.DTOs.ItemCarrinhoDTO

interface CarrinhoService {
    fun criarCarrinho(carrinhoDTO: CarrinhoDTO): CarrinhoDTO
    fun buscarCarrinhoPorId(id: Long): CarrinhoDTO?
    fun listarCarrinhos(): List<CarrinhoDTO>
    fun atualizarCarrinho(id: Long, carrinhoDTO: CarrinhoDTO): CarrinhoDTO?
    fun excluirCarrinho(id: Long)
    fun adicionarProduto(carrinhoId: Long, itemCarrinhoDTO: ItemCarrinhoDTO): CarrinhoDTO
    fun removerProduto(carrinhoId: Long, produtoId: Long): CarrinhoDTO
}