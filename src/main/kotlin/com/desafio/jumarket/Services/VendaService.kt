package com.desafio.jumarket.Services

import com.desafio.jumarket.DTOs.ProdutoDTO
import com.desafio.jumarket.DTOs.VendaDTO
import com.desafio.jumarket.Enum.FormaDePagamento
import com.desafio.jumarket.Models.Venda

interface VendaService {
    fun finalizarVenda(formaDePagamento: FormaDePagamento, usuarioId: Long): Venda
    fun listarVendas(): List<Venda>
}