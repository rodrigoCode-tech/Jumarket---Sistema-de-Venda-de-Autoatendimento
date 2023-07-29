package com.desafio.jumarket.Services.Impl

import com.desafio.jumarket.DTOs.CategoriaDTO
import com.desafio.jumarket.DTOs.ProdutoDTO
import com.desafio.jumarket.Models.Categoria
import com.desafio.jumarket.Models.Produto
import com.desafio.jumarket.Repositories.CategoriaRepository
import com.desafio.jumarket.Repositories.ProdutoRepository
import com.desafio.jumarket.Services.ProdutoService
import com.desafio.jumarket.exception.DataNotFoundException
import org.springframework.stereotype.Service

@Service
class ProdutoServiceImpl(
    private val produtoRepository: ProdutoRepository,
    private val categoriaRepository: CategoriaRepository
) : ProdutoService {

    override fun criarProduto(produtoDTO: ProdutoDTO): ProdutoDTO {
        var produto = produtoDTO.let {
            Produto(
                nome = it.nome,
                unidadeDeMedida = it.unidadeDeMedida,
                precoUnitario = it.precoUnitario,
                categoria = it.categoria.run { Categoria(id, nome) }

            )
        }
        produto = produtoRepository.save(produto)
        return produto.let {
            ProdutoDTO(
                id = it.id,
                nome = it.nome,
                unidadeDeMedida = it.unidadeDeMedida,
                precoUnitario = it.precoUnitario,
                categoria = it.categoria.run { CategoriaDTO(id, nome) }
            )
        }

    }

    override fun buscarProdutoPorId(id: Long): ProdutoDTO? {
        val produto = produtoRepository.findById(id)
            .orElseThrow { DataNotFoundException("Produto não encontrado") }

        return produto?.let {
            ProdutoDTO(
                id = it.id,
                nome = it.nome,
                unidadeDeMedida = it.unidadeDeMedida,
                precoUnitario = it.precoUnitario,
                categoria = it.categoria.run { CategoriaDTO(id, nome) }
            )
        }

    }

    override fun listarProdutos(): List<ProdutoDTO> {
        val produtos = produtoRepository.findAll()
        return produtos.map {
            ProdutoDTO(
                id = it.id,
                nome = it.nome,
                unidadeDeMedida = it.unidadeDeMedida,
                precoUnitario = it.precoUnitario,
                categoria = it.categoria.let { CategoriaDTO(it.id, it.nome) }
            )
        }
    }

    override fun atualizarProduto(id: Long, produtoDTO: ProdutoDTO): ProdutoDTO? {
        var produto = produtoRepository.findById(id)
            .orElseThrow { RuntimeException("Produto não encontrado") }

        produto.apply {
            nome = produtoDTO.nome
            unidadeDeMedida = produtoDTO.unidadeDeMedida
            precoUnitario = produtoDTO.precoUnitario
            categoria = produtoDTO.categoria.let { Categoria(it.id, it.nome) }
        }

        produto = produtoRepository.save(produto)
        return produto.run {
            ProdutoDTO(
                id = id,
                nome = nome,
                unidadeDeMedida = unidadeDeMedida,
                precoUnitario = precoUnitario,
                categoria = categoria.let { CategoriaDTO(it.id, it.nome) }
            )
        }
    }

    override fun excluirProduto(id: Long) {
        val produto = produtoRepository.findById(id)
            .orElseThrow { DataNotFoundException("Produto não encontrado") }

        produtoRepository.delete(produto)
    }

}