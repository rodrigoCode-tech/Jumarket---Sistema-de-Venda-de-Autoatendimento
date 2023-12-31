package com.desafio.jumarket.services.impl

import com.desafio.jumarket.dtos.CategoriaDTO
import com.desafio.jumarket.models.Categoria
import com.desafio.jumarket.repositories.CategoriaRepository
import com.desafio.jumarket.services.CategoriaService
import com.desafio.jumarket.exception.DataNotFoundException
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
            .orElseThrow { DataNotFoundException("Categoria não encontrada") }
        return categoria.let { CategoriaDTO(it.id, it.nome) }
    }

    override fun listarCategorias(): List<CategoriaDTO> {
        val categoria = categoriaRepository.findAll()
        return categoria.map { CategoriaDTO(it.id, it.nome) }
    }

    override fun atualizarCategoria(id: Long, categoriaDTO: CategoriaDTO): CategoriaDTO? {
        val categoria = categoriaRepository.findById(id)
            .orElseThrow { DataNotFoundException("Categoria não encontrada") }

            categoria.nome = categoriaDTO.nome
            val categoriaAtualizada = categoriaRepository.save(categoria)
            return CategoriaDTO(categoriaAtualizada.id, categoriaAtualizada.nome)

    }

    override fun excluirCategoria(id: Long) {
        val categoria = categoriaRepository.findById(id)
            .orElseThrow { DataNotFoundException("Categoria não encontrada") }

        categoriaRepository.delete(categoria)
    }
}