package com.desafio.jumarket.dtos

import com.desafio.jumarket.models.ItemVenda
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive

data class ItemVendaDTO(
    val id: Long? = null,
    @field:NotNull
    val produto: ItemVendaProdutoDTO,
    @field:Positive
    val quantidade: Int
)
