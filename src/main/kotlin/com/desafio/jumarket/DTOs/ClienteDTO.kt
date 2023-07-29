package com.desafio.jumarket.DTOs

import jakarta.validation.constraints.NotBlank
import org.hibernate.validator.constraints.br.CPF

data class ClienteDTO(
    val id : Long?,
    @field:NotBlank
    val nome : String,
    @field:NotBlank
    val cpf : String
)
