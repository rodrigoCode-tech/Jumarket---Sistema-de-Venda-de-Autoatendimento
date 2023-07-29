package com.desafio.jumarket.dtos

import jakarta.validation.constraints.NotNull

data class CarrinhoDTO(
    val id: Long? = null,
    @field:NotNull
    val cliente: ClienteDTO,
    val produtos: List<ItemCarrinhoDTO>? = null
)
