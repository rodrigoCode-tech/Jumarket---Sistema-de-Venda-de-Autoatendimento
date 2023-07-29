package com.desafio.jumarket.services

import com.desafio.jumarket.dtos.ProdutoDTO

interface ProdutoService {
    fun criarProduto(produtoDTO: ProdutoDTO): ProdutoDTO
    fun buscarProdutoPorId(id: Long): ProdutoDTO?
    fun listarProdutos(): List<ProdutoDTO>
    fun atualizarProduto(id: Long, produtoDTO: ProdutoDTO): ProdutoDTO?
    fun excluirProduto(id: Long)
}