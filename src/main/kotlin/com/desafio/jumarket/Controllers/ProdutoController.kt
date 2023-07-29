package com.desafio.jumarket.Controllers

import com.desafio.jumarket.DTOs.ProdutoDTO
import com.desafio.jumarket.Services.ProdutoService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/produtos")
class ProdutoController (private val produtoService: ProdutoService){

    @PostMapping
    fun criarProdutos(@RequestBody produtoDTO: ProdutoDTO):ResponseEntity<ProdutoDTO>{
        val produto = produtoService.criarProduto(produtoDTO)
        return ResponseEntity(produto,HttpStatus.CREATED)
    }

    @GetMapping
    fun listarProdutos(): ResponseEntity<List<ProdutoDTO>>{
        val produtos = produtoService.listarProdutos()
        return ResponseEntity(produtos, HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun buscarProdutoPorId(@PathVariable id: Long): ResponseEntity<ProdutoDTO>{
        val produto = produtoService.buscarProdutoPorId(id)
        return if (produto != null) {
            ResponseEntity(produto, HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PutMapping("/{id}")
    fun atualizerProduto(@PathVariable id: Long, @RequestBody produtoDto: ProdutoDTO)
    : ResponseEntity<ProdutoDTO>{
        val produtoExistente = produtoService.buscarProdutoPorId(id)

        return if (produtoExistente != null) {
            val produtoAtualizado = produtoService.atualizarProduto(id, produtoDto)
            ResponseEntity.ok(produtoAtualizado)
        } else {
            ResponseEntity.notFound().build()
        }

    }

    @DeleteMapping("/{id}")
    fun deletarProduto(@PathVariable id : Long) : ResponseEntity<Unit>{
        val produtoExistente = produtoService.buscarProdutoPorId(id)

        return if (produtoExistente != null) {
            produtoService.excluirProduto(id)
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }

}