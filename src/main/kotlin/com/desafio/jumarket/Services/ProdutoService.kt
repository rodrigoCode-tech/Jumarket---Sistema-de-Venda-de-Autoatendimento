package com.desafio.jumarket.Services

import com.desafio.jumarket.DTOs.ProdutoDTO

interface ProdutoService {
    fun criarProduto(produtoDTO: ProdutoDTO): ProdutoDTO
    fun buscarProdutoPorId(id: Long): ProdutoDTO?
    fun listarProdutos(): List<ProdutoDTO>
    fun atualizarProduto(id: Long, produtoDTO: ProdutoDTO): ProdutoDTO?
    fun excluirProduto(id: Long)
}