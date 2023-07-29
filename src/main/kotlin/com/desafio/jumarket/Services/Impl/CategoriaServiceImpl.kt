package com.desafio.jumarket.Services.Impl

import com.desafio.jumarket.DTOs.CategoriaDTO
import com.desafio.jumarket.Models.Categoria
import com.desafio.jumarket.Repositories.CategoriaRepository
import com.desafio.jumarket.Services.CategoriaService
import org.springframework.stereotype.Service

@Service
class CategoriaServiceImpl(private val categoriaRepository: CategoriaRepository): CategoriaService {

    override fun criarCategoria(categoriaDTO: CategoriaDTO): CategoriaDTO {
        val categoria = Categoria(nome = categoriaDTO.nome)
        val novaCategoria = categoriaRepository.save(categoria)
        return CategoriaDTO(novaCategoria.id, novaCategoria.nome)
    }

    override fun buscarCategoriaPorId(id: Long): CategoriaDTO? {
        val categoria = categoriaRepository.findById(id)
            .orElseThrow { RuntimeException("Categoria não encontrada") }
        return categoria.let { CategoriaDTO(it.id, it.nome) }
    }

    override fun listarCategorias(): List<CategoriaDTO> {
        val categoria = categoriaRepository.findAll()
        return categoria.map { CategoriaDTO(it.id, it.nome) }
    }

    override fun atualizarCategoria(id: Long, categoriaDTO: CategoriaDTO): CategoriaDTO? {
        val categoria = categoriaRepository.findById(id)
            .orElseThrow { RuntimeException("Categoria não encontrada") }

        if (categoria != null) {
            categoria.nome = categoriaDTO.nome
            val categoriaAtualizada = categoriaRepository.save(categoria)
            return CategoriaDTO(categoriaAtualizada.id, categoriaAtualizada.nome)
        }
        return null
    }

    override fun excluirCategoria(id: Long,categoriaDTO: CategoriaDTO) {
        val categoria = categoriaRepository.findById(id)
            .orElseThrow { RuntimeException("Categoria não encontrada") }

        categoriaRepository.delete(categoria)
    }
}