package com.desafio.jumarket.Services.Impl

import com.desafio.jumarket.DTOs.ProdutoDTO
import com.desafio.jumarket.Models.Produto
import com.desafio.jumarket.Repositories.CategoriaRepository
import com.desafio.jumarket.Repositories.ProdutoRepository
import com.desafio.jumarket.Services.ProdutoService
import org.springframework.stereotype.Service

@Service
class ProdutoServiceImpl(
    private val produtoRepository: ProdutoRepository,
    private val categoriaRepository: CategoriaRepository
): ProdutoService {

    override fun criarProduto(produtoDTO: ProdutoDTO): ProdutoDTO {
        val categoria = categoriaRepository.findById(produtoDTO.categoriaId!!)
            .orElseThrow { RuntimeException("Categoria não encontrada") }

        val produto = Produto(
            nome = produtoDTO.nome,
            unidadeDeMedida = produtoDTO.unidadeDeMedida,
            precoUnitario = produtoDTO.precoUnitario,
            categoria = categoria
        )
        val novoProduto = produtoRepository.save(produto)
        return ProdutoDTO(
            id = novoProduto.id,
            nome = novoProduto.nome,
            unidadeDeMedida = novoProduto.unidadeDeMedida,
            precoUnitario = novoProduto.precoUnitario,
            categoriaId = novoProduto.categoria.id
        )
    }

    override fun buscarProdutoPorId(id: Long): ProdutoDTO? {
        val produto = produtoRepository.findById(id)
            .orElseThrow { RuntimeException("Produto não encontrado")}

        return produto?.let {
            ProdutoDTO(
                id = it.id,
                nome = it.nome,
                unidadeDeMedida = it.unidadeDeMedida,
                precoUnitario = it.precoUnitario,
                categoriaId = it.categoria.id
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
                categoriaId = it.categoria.id
            )
        }
    }

    override fun atualizarProduto(id: Long, produtoDTO: ProdutoDTO): ProdutoDTO? {
        val produto = produtoRepository.findById(id)
            .orElseThrow { RuntimeException("Produto não encontrado")}

        val categoria = categoriaRepository.findById(produtoDTO.categoriaId!!)
            .orElseThrow { RuntimeException("Categoria não encontrada") }

        produto.apply {
            nome = produtoDTO.nome // Corrigindo o atributo 'nome' aqui
            unidadeDeMedida = produtoDTO.unidadeDeMedida
            precoUnitario = produtoDTO.precoUnitario
            this.categoria = categoria
        }

        val produtoAtualizado = produtoRepository.save(produto)
        return ProdutoDTO(
            id = produtoAtualizado.id,
            nome = produtoAtualizado.nome,
            unidadeDeMedida = produtoAtualizado.unidadeDeMedida,
            precoUnitario = produtoAtualizado.precoUnitario,
            categoriaId = produtoAtualizado.categoria.id
        )
    }

    override fun excluirProduto(id: Long) {
        val produto = produtoRepository.findById(id)
            .orElseThrow { RuntimeException("Produto não encontrado")}

        produtoRepository.delete(produto);
    }

}