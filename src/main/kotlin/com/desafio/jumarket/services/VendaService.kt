package com.desafio.jumarket.services

import com.desafio.jumarket.enums.FormaDePagamento
import com.desafio.jumarket.models.Venda

interface VendaService {
    fun finalizarVenda(formaDePagamento: FormaDePagamento, usuarioId: Long): Venda
    fun listarVendas(): List<Venda>
}