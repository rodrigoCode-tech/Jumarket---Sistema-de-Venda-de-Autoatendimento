package com.desafio.jumarket.services

import com.desafio.jumarket.dtos.VendaDTO
import com.desafio.jumarket.enums.FormaDePagamento
import com.desafio.jumarket.models.Venda

interface VendaService {
    fun finalizarVenda(formaDePagamento: FormaDePagamento, carrinhoId: Long): VendaDTO
    fun listarVendas(): List<VendaDTO>
}