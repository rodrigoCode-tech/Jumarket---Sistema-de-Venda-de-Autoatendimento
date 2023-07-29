package com.desafio.jumarket.dtos

data class CarrinhoDTO(
    val id: Long?,
    val clienteId: Long?,
    val produtos: List<ItemCarrinhoDTO>
)
