package com.desafio.jumarket.controllers

import com.desafio.jumarket.dtos.CategoriaDTO
import com.desafio.jumarket.services.CategoriaService
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
@RequestMapping("/categorias")
class CategoriaController(private val categoriaService: CategoriaService) {

    @PostMapping
    fun criarCategoria(@RequestBody @Valid categoriaDTO: CategoriaDTO): ResponseEntity<CategoriaDTO> {
        val novaCategoria = categoriaService.criarCategoria(categoriaDTO)
        return ResponseEntity(novaCategoria, HttpStatus.CREATED)
    }

    @GetMapping("/{id}")
    fun buscarCategoriaPorId(@PathVariable id: Long): ResponseEntity<CategoriaDTO> {
        val categoria = categoriaService.buscarCategoriaPorId(id)

        return ResponseEntity(categoria, HttpStatus.OK)

    }

    @GetMapping
    fun listarCategorias(): ResponseEntity<List<CategoriaDTO>> {
        val categorias = categoriaService.listarCategorias()
        return ResponseEntity(categorias, HttpStatus.OK)
    }

    @PutMapping("/{id}")
    fun atualizarCategoria(
        @PathVariable id: Long,
        @RequestBody @Valid categoriaDTO: CategoriaDTO
    ): ResponseEntity<CategoriaDTO> {
        val categoriaAtualizada = categoriaService.atualizarCategoria(id, categoriaDTO)
        return ResponseEntity(categoriaAtualizada, HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun excluirCategoria(@PathVariable id: Long): ResponseEntity<Unit> {
        categoriaService.excluirCategoria(id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }


}