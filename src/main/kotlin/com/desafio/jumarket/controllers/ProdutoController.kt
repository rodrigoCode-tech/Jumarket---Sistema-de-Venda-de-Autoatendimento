package com.desafio.jumarket.controllers

import com.desafio.jumarket.dtos.ProdutoDTO
import com.desafio.jumarket.services.ProdutoService
import jakarta.validation.Valid
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
    fun criarProdutos(@RequestBody @Valid produtoDTO: ProdutoDTO):ResponseEntity<ProdutoDTO>{
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
        return ResponseEntity(produto, HttpStatus.OK)
    }

    @PutMapping("/{id}")
    fun atualizerProduto(@PathVariable id: Long, @RequestBody produtoDto: ProdutoDTO)
    : ResponseEntity<ProdutoDTO>{
        val produtoAtualizado = produtoService.atualizarProduto(id, produtoDto)
        return ResponseEntity.ok(produtoAtualizado)
    }

    @DeleteMapping("/{id}")
    fun deletarProduto(@PathVariable id : Long) : ResponseEntity<Unit>{
        produtoService.excluirProduto(id)
        return ResponseEntity.noContent().build()
    }

}