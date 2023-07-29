package com.desafio.jumarket.services

import com.desafio.jumarket.dtos.CategoriaDTO

interface CategoriaService {
    fun criarCategoria(categoriaDTO: CategoriaDTO): CategoriaDTO
    fun buscarCategoriaPorId(id: Long): CategoriaDTO?
    fun listarCategorias(): List<CategoriaDTO>
    fun atualizarCategoria(id: Long, categoriaDTO: CategoriaDTO): CategoriaDTO?
    fun excluirCategoria(id: Long)

}