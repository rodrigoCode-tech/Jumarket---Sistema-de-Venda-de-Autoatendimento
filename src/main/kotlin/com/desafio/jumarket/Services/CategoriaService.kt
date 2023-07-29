package com.desafio.jumarket.Services

import com.desafio.jumarket.DTOs.CategoriaDTO

interface CategoriaService {
    fun criarCategoria(categoriaDTO: CategoriaDTO): CategoriaDTO
    fun buscarCategoriaPorId(id: Long): CategoriaDTO?
    fun listarCategorias(): List<CategoriaDTO>
    fun atualizarCategoria(id: Long, categoriaDTO: CategoriaDTO): CategoriaDTO?
    fun excluirCategoria(id: Long)

}