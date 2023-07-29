package com.desafio.jumarket.dtos

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class ItemVendaProdutoDTO(
    @field:NotNull
    val id: Long,
    @field:NotBlank
    val nome: String
)
