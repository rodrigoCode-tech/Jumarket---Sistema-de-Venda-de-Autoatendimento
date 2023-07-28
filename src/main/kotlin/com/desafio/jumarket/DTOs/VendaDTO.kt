package com.desafio.jumarket.DTOs

data class VendaDTO(
    val id: Long = 0,
    val carrinhoId: Long,
    val valorTotal: Double,
    val formaPagamento: String
)
