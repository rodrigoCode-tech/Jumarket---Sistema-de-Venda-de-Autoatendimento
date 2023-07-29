package com.desafio.jumarket.DTOs

import com.desafio.jumarket.Models.Produto

data class CarrinhoDTO(
    val id: Long?,
    val clienteId: Long?,
    val produtos: List<ItemCarrinhoDTO>
)
