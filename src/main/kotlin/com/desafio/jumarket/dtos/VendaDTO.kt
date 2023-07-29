package com.desafio.jumarket.dtos

data class VendaDTO(
    val id: Long?,
    val itens : List<ItemVendaDTO>? = null,
    val valorTotal: Double,
    val formaPagamento: String
)
