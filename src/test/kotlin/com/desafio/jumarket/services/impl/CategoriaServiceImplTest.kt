package com.desafio.jumarket.services.impl

import com.desafio.jumarket.dtos.CategoriaDTO
import com.desafio.jumarket.exception.DataNotFoundException
import com.desafio.jumarket.models.Categoria
import com.desafio.jumarket.repositories.CategoriaRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class CategoriaServiceImplTest {

    @Mock
    private lateinit var categoriaRepository: CategoriaRepository

    @InjectMocks
    private lateinit var categoriaService: CategoriaServiceImpl

    private lateinit var categoriaDTO: CategoriaDTO

    @BeforeEach
    fun setup() {
        categoriaDTO = CategoriaDTO(id = 1L, nome = "Categoria de Teste")
    }

    @Test
    fun `test criarCategoria should create a new Categoria`() {
        val categoria = Categoria(nome = categoriaDTO.nome)
        `when`(categoriaRepository.save(categoria)).thenReturn(categoria.copy(id = 1L))

        val result = categoriaService.criarCategoria(categoriaDTO)


        assertEquals(1L, result.id)
        assertEquals(categoriaDTO.nome, result.nome)
    }

    @Test
    fun `test buscarCategoriaPorId should return CategoriaDTO when Categoria is found`() {

        val categoria = Categoria(id = 1L, nome = "Categoria de Teste")
        `when`(categoriaRepository.findById(1L)).thenReturn(java.util.Optional.of(categoria))


        val result = categoriaService.buscarCategoriaPorId(1L)


        assertEquals(categoria.id, result?.id)
        assertEquals(categoria.nome, result?.nome)
    }

    @Test
    fun `test listarCategorias should return a list of CategoriaDTO`() {

        val categoria1 = Categoria(id = 1L, nome = "Categoria 1")
        val categoria2 = Categoria(id = 2L, nome = "Categoria 2")
        `when`(categoriaRepository.findAll()).thenReturn(listOf(categoria1, categoria2))


        val result = categoriaService.listarCategorias()


        assertEquals(2, result.size)
        assertEquals(categoria1.id, result[0].id)
        assertEquals(categoria1.nome, result[0].nome)
        assertEquals(categoria2.id, result[1].id)
        assertEquals(categoria2.nome, result[1].nome)
    }

    @Test
    fun `test atualizarCategoria should update the Categoria and return updated CategoriaDTO`() {

        val categoria = Categoria(id = 1L, nome = "Categoria Original")
        `when`(categoriaRepository.findById(1L)).thenReturn(java.util.Optional.of(categoria))
        `when`(categoriaRepository.save(categoria)).thenReturn(categoria.copy(nome = "Categoria Atualizada"))


        val categoriaAtualizada =
            categoriaService.atualizarCategoria(1L, CategoriaDTO(id = 1L, nome = "Categoria Atualizada"))


        assertEquals(1L, categoriaAtualizada?.id)
        assertEquals("Categoria Atualizada", categoriaAtualizada?.nome)
    }

    @Test
    fun `test atualizarCategoria should throw DataNotFoundException when Categoria is not found`() {

        `when`(categoriaRepository.findById(1L)).thenReturn(java.util.Optional.empty())

        assertThrows<DataNotFoundException> {
            categoriaService.atualizarCategoria(
                1L,
                CategoriaDTO(id = 1L, nome = "Categoria Atualizada")
            )
        }
    }


    @Test
    fun `test excluirCategoria should delete the Categoria`() {

        val categoria = Categoria(id = 1L, nome = "Categoria de Teste")
        `when`(categoriaRepository.findById(1L)).thenReturn(java.util.Optional.of(categoria))


        categoriaService.excluirCategoria(1L)


        verify(categoriaRepository, times(1)).delete(categoria)
    }

    @Test
    fun `test excluirCategoria should throw DataNotFoundException when Categoria is not found`() {

        `when`(categoriaRepository.findById(1L)).thenReturn(java.util.Optional.empty())

        assertThrows<DataNotFoundException> { categoriaService.excluirCategoria(1L) }
    }
}