package com.desafio.jumarket.DTOs

data class ProdutoDTO(
    val id : Long,
    val nome : String,
    val unidadeDeMedida : String,
    val precoUnitario : Double,
    val categoriaId: Long
)
