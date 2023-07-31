package com.desafio.jumarket.services.impl

import com.desafio.jumarket.dtos.*
import com.desafio.jumarket.enums.FormaDePagamento
import com.desafio.jumarket.models.*
import com.desafio.jumarket.repositories.CarrinhoRepository
import com.desafio.jumarket.repositories.VendaRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.any
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*

@ExtendWith(MockitoExtension::class)
class VendaServiceImplTest {
    @InjectMocks
    private lateinit var vendaService: VendaServiceImpl

    @Mock
    private lateinit var vendaRepository: VendaRepository

    @Mock
    private lateinit var carrinhoRepository: CarrinhoRepository

    @Test
    fun testFinalizarVenda() {
        val formaDePagamento = FormaDePagamento.PIX
        val carrinhoId = 1L
        val cliente = Cliente(id = 1L, nome = "João da Silva", cpf = "123.456.789-00")
        val produto = Produto(
            id = 1L,
            nome = "Produto de Teste",
            unidadeDeMedida = "UN",
            precoUnitario = 10.0,
            categoria = Categoria(id = 1L, nome = "Categoria Teste")
        )
        val itemVendaDTO =
            ItemVendaDTO(produto = ItemVendaProdutoDTO(id = 1L, nome = "Produto de Teste"), quantidade = 2)
        val itemCarrinho = ItemCarrinho(
            produto = Produto(
                id = itemVendaDTO.produto.id,
                nome = itemVendaDTO.produto.nome,
                categoria = Categoria(id = 1L, nome = "Categoria Teste"),
                unidadeDeMedida = "kg",
                precoUnitario = 10.0
            ), quantidade = itemVendaDTO.quantidade
        )
        val carrinho = Carrinho(cliente = cliente, itens = mutableListOf(itemCarrinho))
        val venda = Venda(
            cliente = cliente,
            itens = mutableListOf(ItemVenda(produto = produto, quantidade = itemVendaDTO.quantidade)),
            valorTotal = 20.0,
            formaDePagamento = FormaDePagamento.PIX
        )

        `when`(carrinhoRepository.findById(carrinhoId)).thenReturn(Optional.of(carrinho))
        `when`(vendaRepository.save(any(Venda::class.java))).thenReturn(venda)

       
        val result = vendaService.finalizarVenda(formaDePagamento, carrinhoId)

   
        assertNotNull(result)
        assertEquals(venda.valorTotal, result.valorTotal)
        assertEquals(formaDePagamento.name, result.formaPagamento)
    }


    @Test
    fun testListarVendas() {
 
        val cliente = Cliente(id = 1L, nome = "João da Silva", cpf = "123.456.789-00")
        val produto = Produto(
            id = 1L,
            nome = "Produto de Teste",
            unidadeDeMedida = "UN",
            precoUnitario = 10.0,
            categoria = Categoria(id = 1L, nome = "Categoria Teste")
        )
        val itemVenda = ItemVendaDTO(produto = ItemVendaProdutoDTO(id = 1L, nome = "Produto de Teste"), quantidade = 2)
        val venda = Venda(
            id = 1L,
            cliente = cliente,
            itens = mutableListOf(ItemVenda(produto = produto, quantidade = itemVenda.quantidade)),
            valorTotal = 20.0,
            formaDePagamento = FormaDePagamento.DINHEIRO
        )

        `when`(vendaRepository.findAll()).thenReturn(listOf(venda))

 
        val result = vendaService.listarVendas()
        
        assertNotNull(result)
        assertEquals(1, result.size)
        assertEquals(venda.valorTotal, result[0].valorTotal)
        assertEquals(venda.formaDePagamento.name, result[0].formaPagamento)
    }

}
