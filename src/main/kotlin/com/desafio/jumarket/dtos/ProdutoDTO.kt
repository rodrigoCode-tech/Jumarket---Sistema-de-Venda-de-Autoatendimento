package com.desafio.jumarket.dtos

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive

data class ProdutoDTO(
    val id: Long?,
    @field:NotBlank
    val nome: String,
    @field:NotBlank
    val unidadeDeMedida: String,
    @field:Positive
    val precoUnitario: Double,
    @field:NotNull
    val categoria: CategoriaDTO
)
