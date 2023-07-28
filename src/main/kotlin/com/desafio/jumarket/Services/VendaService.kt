package com.desafio.jumarket.Services

import com.desafio.jumarket.DTOs.ProdutoDTO
import com.desafio.jumarket.DTOs.VendaDTO
import com.desafio.jumarket.Models.Venda

interface VendaService {
    fun criarVenda(vendaDTO: VendaDTO): Venda
    fun buscarVendaPorId(id: Long): Venda?
    fun listarVendas(): List<Venda>
    fun adicionarProduto(vendaId: Long, produtoDTO: ProdutoDTO, quantidade: Int): Venda
}