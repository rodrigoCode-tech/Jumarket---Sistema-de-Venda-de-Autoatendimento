package com.desafio.jumarket.DTOs

data class CarrinhoDTO(
    val id: Long,
    val clienteId: Long,
    val produtos: List<ItemCarrinhoDTO>
)
