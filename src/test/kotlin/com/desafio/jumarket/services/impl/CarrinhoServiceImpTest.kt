package com.desafio.jumarket.services.impl

import com.desafio.jumarket.dtos.ItemCarrinhoDTO
import com.desafio.jumarket.dtos.ItemCarrinhoProdutoDTO
import com.desafio.jumarket.exception.DataNotFoundException
import com.desafio.jumarket.models.*
import com.desafio.jumarket.repositories.CarrinhoRepository
import com.desafio.jumarket.repositories.ClienteRepository
import com.desafio.jumarket.repositories.ProdutoRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.anyLong
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*

@ExtendWith(MockitoExtension::class)
class CarrinhoServiceImpTest {
    @Mock
    private lateinit var carrinhoRepository: CarrinhoRepository

    @Mock
    private lateinit var clienteRepository: ClienteRepository

    @Mock
    private lateinit var produtoRepository: ProdutoRepository

    @InjectMocks
    private lateinit var carrinhoService: CarrinhoServiceImp

    @Test
    fun `test abrirCarrrinho should open a new Carrinho`() {
        val cliente = getCliente()
        val carrinho = getCarrinho(cliente)
        `when`(clienteRepository.findById(anyLong()))
            .thenReturn(Optional.of(cliente))
        `when`(carrinhoRepository.save(any()))
            .thenReturn(carrinho)

        val result = carrinhoService.abrirCarrinho(1L)

        assertEquals(1L, result.id)
        assertEquals(1L, result.cliente.id)
        assertEquals("rodrigo", result.cliente.nome)
        assertEquals("123456789", result.cliente.cpf)
    }

    @Test
    fun `teste Adicionar Produto`() {
        val cliente = getCliente()
        val carrinho = getCarrinho(cliente)
        val produto = getProduto()

        `when`(carrinhoRepository.findById(any())).thenReturn(Optional.of(carrinho))
        `when`(produtoRepository.findById(any())).thenReturn(Optional.of(produto))
        `when`(carrinhoRepository.save(any())).thenReturn(carrinho)

        val carrinhoDTO = carrinhoService.adicionarProduto(
            1L,
            ItemCarrinhoDTO(
                id = 1L, produto = ItemCarrinhoProdutoDTO(
                    id = 1, nome = "produto"
                ), quantidade = 2
            )
        )
        val item = carrinhoDTO.produtos?.get(0)
        assertTrue(!carrinhoDTO.produtos?.isEmpty()!!)
        assertEquals("produto", item?.produto?.nome)
        assertEquals(1L, item?.produto?.id)
        assertEquals(2, item?.quantidade)

    }
    @Test
    fun `Testar Remover Produtos`(){
        val cliente = getCliente()
        val carrinho = getCarrinho(cliente)
        carrinho.itens.add(ItemCarrinho(id = 1,produto = getProduto(), quantidade = 2, carrinho = carrinho))

        `when`(carrinhoRepository.findById(any())).thenReturn(Optional.of(carrinho))
        val result = carrinhoService.removerItem(1L, 1L)

        assertTrue(result.produtos!!.isEmpty())
    }
    @Test
    fun `Deve lancar exceção quando item não encontrado`(){
        val cliente = getCliente()
        val carrinho = getCarrinho(cliente)


        `when`(carrinhoRepository.findById(any())).thenReturn(Optional.of(carrinho))
        val result = assertThrows(DataNotFoundException::class.java){
            carrinhoService.removerItem(1L, 1L)
        }

        assertEquals("Item não encontrado no carrinho",result.message)
    }

    @Test
    fun `testar Listar itens do carrinho`(){
        val cliente = getCliente()
        val carrinho = getCarrinho(cliente)
        carrinho.itens.add(ItemCarrinho(id = 1,produto = getProduto(), quantidade = 2, carrinho = carrinho))

        `when`(clienteRepository.existsById(any())).thenReturn(true)
        `when`(carrinhoRepository.findByCliente(anyLong())).thenReturn(listOf(carrinho))

        val result = carrinhoService.listarItensPorCliente(1L)

        val item = result.get(0)
        assertTrue(result.isNotEmpty())
        assertEquals(2,item.quantidade)
        assertEquals("produto",item.produto.nome)
    }
    @Test
    fun `test Excluir carrinho`(){
        val cliente = getCliente()
        val carrinho = getCarrinho(cliente)
        `when`(carrinhoRepository.findById(any())).thenReturn(Optional.of(carrinho))
        carrinhoService.excluirCarrinho(1L)
        verify(carrinhoRepository, times(1)).deleteById(any())
    }

    fun getCliente() = Cliente(id = 1L, nome = "rodrigo", cpf = "123456789")
    fun getCarrinho(cliente: Cliente) = Carrinho(id = 1L, cliente = cliente)
    fun getProduto() = Produto(
        id = 1L, nome = "produto", unidadeDeMedida = "kg", precoUnitario = 10.0,
        categoria = Categoria(1L, nome = "Produtos")
    )


}