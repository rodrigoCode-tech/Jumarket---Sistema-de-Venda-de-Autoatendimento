package com.desafio.jumarket.services.impl

import com.desafio.jumarket.dtos.CategoriaDTO
import com.desafio.jumarket.dtos.ProdutoDTO
import com.desafio.jumarket.exception.DataNotFoundException
import com.desafio.jumarket.models.Categoria
import com.desafio.jumarket.models.Produto
import com.desafio.jumarket.repositories.CategoriaRepository
import com.desafio.jumarket.repositories.ProdutoRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*

@ExtendWith(MockitoExtension::class)
class ProdutoServiceImplTest {
    @Mock
    private lateinit var produtoRepository: ProdutoRepository

    @Mock
    private lateinit var categoriaRepository: CategoriaRepository

    private lateinit var produtoService: ProdutoServiceImpl

    @BeforeEach
    fun setup() {
        MockitoAnnotations.openMocks(this)
        produtoService = ProdutoServiceImpl(produtoRepository, categoriaRepository)
    }

    @Test
    fun `test criarProduto should create a new Produto and return ProdutoDTO`() {
        
        val categoriaDTO = CategoriaDTO(id = 1L, nome = "Categoria Teste")
        val produtoDTO = ProdutoDTO(
            id = null,
            nome = "Novo Produto",
            unidadeDeMedida = "un",
            precoUnitario = 10.0,
            categoria = categoriaDTO
        )
        val categoria = Categoria(id = categoriaDTO.id, nome = categoriaDTO.nome)
        val produto = Produto(
            id = null,
            nome = produtoDTO.nome,
            unidadeDeMedida = produtoDTO.unidadeDeMedida,
            precoUnitario = produtoDTO.precoUnitario,
            categoria = categoria
        )

        `when`(produtoRepository.save(produto)).thenReturn(produto.copy(id = 1L))

        
        val result = produtoService.criarProduto(produtoDTO)

        
        assertEquals(1L, result.id)
        assertEquals(produtoDTO.nome, result.nome)
        assertEquals(produtoDTO.unidadeDeMedida, result.unidadeDeMedida)
        assertEquals(produtoDTO.precoUnitario, result.precoUnitario)
        assertEquals(categoriaDTO.id, result.categoria.id)
        assertEquals(categoriaDTO.nome, result.categoria.nome)
    }

    @Test
    fun `test buscarProdutoPorId should return ProdutoDTO when Produto is found`() {

        val categoria = Categoria(id = 1L, nome = "Categoria Teste")
        val produto = Produto(
            id = 1L,
            nome = "Produto Teste",
            unidadeDeMedida = "un",
            precoUnitario = 20.0,
            categoria = categoria
        )

        `when`(produtoRepository.findById(1L)).thenReturn(Optional.of(produto))


        val result = produtoService.buscarProdutoPorId(1L)


        assertEquals(produto.id, result?.id)
        assertEquals(produto.nome, result?.nome)
        assertEquals(produto.unidadeDeMedida, result?.unidadeDeMedida)
        assertEquals(produto.precoUnitario, result?.precoUnitario)
        assertEquals(categoria.id, result?.categoria?.id)
        assertEquals(categoria.nome, result?.categoria?.nome)
    }

    @Test
    fun `test buscarProdutoPorId should throw DataNotFoundException when Produto is not found`() {

        `when`(produtoRepository.findById(1L)).thenReturn(Optional.empty())


        assertThrows(DataNotFoundException::class.java) {
            produtoService.buscarProdutoPorId(1L)
        }
    }

    @Test
    fun `test listarProdutos should return a list of ProdutoDTOs`() {

        val categoria = Categoria(id = 1L, nome = "Categoria Teste")
        val produtos = listOf(
            Produto(id = 1L, nome = "Produto 1", unidadeDeMedida = "un", precoUnitario = 10.0, categoria = categoria),
            Produto(id = 2L, nome = "Produto 2", unidadeDeMedida = "un", precoUnitario = 20.0, categoria = categoria)
        )

        `when`(produtoRepository.findAll()).thenReturn(produtos)


        val result = produtoService.listarProdutos()


        assertEquals(produtos.size, result.size)
        assertEquals(produtos[0].id, result[0].id)
        assertEquals(produtos[0].nome, result[0].nome)
        assertEquals(produtos[0].unidadeDeMedida, result[0].unidadeDeMedida)
        assertEquals(produtos[0].precoUnitario, result[0].precoUnitario)
        assertEquals(categoria.id, result[0].categoria.id)
        assertEquals(categoria.nome, result[0].categoria.nome)

        assertEquals(produtos[1].id, result[1].id)
        assertEquals(produtos[1].nome, result[1].nome)
        assertEquals(produtos[1].unidadeDeMedida, result[1].unidadeDeMedida)
        assertEquals(produtos[1].precoUnitario, result[1].precoUnitario)
        assertEquals(categoria.id, result[1].categoria.id)
        assertEquals(categoria.nome, result[1].categoria.nome)
    }

    @Test
    fun `test atualizarProduto should update Produto and return updated ProdutoDTO`() {

        val categoriaDTO = CategoriaDTO(id = 1L, nome = "Categoria Teste")
        val produtoDTO = ProdutoDTO(
            id = null,
            nome = "Novo Produto",
            unidadeDeMedida = "un",
            precoUnitario = 10.0,
            categoria = categoriaDTO
        )
        val produto = Produto(
            id = 1L,
            nome = "Produto Antigo",
            unidadeDeMedida = "un",
            precoUnitario = 20.0,
            categoria = Categoria(id = 2L, nome = "Categoria Antiga")
        )

        `when`(produtoRepository.findById(1L)).thenReturn(Optional.of(produto))

        `when`(produtoRepository.save(any(Produto::class.java))).thenAnswer { invocation ->
            invocation.getArgument<Produto>(0).copy(nome = produtoDTO.nome, precoUnitario = produtoDTO.precoUnitario)
        }


        val result = produtoService.atualizarProduto(1L, produtoDTO)

        assertEquals(1L, result?.id)
        assertEquals(produtoDTO.nome, result?.nome)
        assertEquals(produtoDTO.unidadeDeMedida, result?.unidadeDeMedida)
        assertEquals(produtoDTO.precoUnitario, result?.precoUnitario)
        assertEquals(categoriaDTO.id, result?.categoria?.id)
        assertEquals(categoriaDTO.nome, result?.categoria?.nome)
    }


    @Test
    fun `test atualizarProduto should throw DataNotFoundException when Produto is not found`() {
        val produtoDTO = ProdutoDTO(
            id = null,
            nome = "Novo Produto",
            unidadeDeMedida = "un",
            precoUnitario = 10.0,
            categoria = CategoriaDTO(id = 1L, nome = "Categoria Teste")
        )

        `when`(produtoRepository.findById(1L)).thenReturn(Optional.empty())

        assertThrows(DataNotFoundException::class.java) {
            produtoService.atualizarProduto(1L, produtoDTO)
        }
    }

    @Test
    fun `test excluirProduto should delete Produto`() {

        val produto = Produto(
            id = 1L,
            nome = "Produto Teste",
            unidadeDeMedida = "un",
            precoUnitario = 20.0,
            categoria = Categoria(id = 1L, nome = "Categoria Teste")
        )

        `when`(produtoRepository.findById(1L)).thenReturn(Optional.of(produto))

        produtoService.excluirProduto(1L)

        verify(produtoRepository, times(1)).delete(produto)
    }

    @Test
    fun `test excluirProduto should throw DataNotFoundException when Produto is not found`() {

        `when`(produtoRepository.findById(1L)).thenReturn(Optional.empty())


        assertThrows(DataNotFoundException::class.java) {
            produtoService.excluirProduto(1L)
        }
    }

}