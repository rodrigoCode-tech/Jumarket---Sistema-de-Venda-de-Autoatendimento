package com.desafio.jumarket.dtos

import jakarta.validation.constraints.NotBlank

data class ClienteDTO(
    val id : Long?,
    @field:NotBlank
    val nome : String,
    @field:NotBlank
    val cpf : String
)
