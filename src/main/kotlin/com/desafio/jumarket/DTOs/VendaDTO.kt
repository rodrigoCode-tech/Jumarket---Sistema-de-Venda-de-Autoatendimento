package com.desafio.jumarket.DTOs

data class VendaDTO(
    val id: Long?,
    val carrinhoId: Long,
    val valorTotal: Double,
    val formaPagamento: String
)
