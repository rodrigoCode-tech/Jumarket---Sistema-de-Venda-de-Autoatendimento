package com.desafio.jumarket.DTOs

import jakarta.validation.constraints.NotBlank

data class CategoriaDTO(
    val id: Long?,
    @field:NotBlank
    val nome: String
)
