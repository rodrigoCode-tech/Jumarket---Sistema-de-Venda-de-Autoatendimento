package com.desafio.jumarket.dtos

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive

data class ItemCarrinhoDTO(
    val id: Long? = null,
    @field:NotNull
    val produto: ItemCarrinhoProdutoDTO,
    @field:Positive
    val quantidade: Int
)
