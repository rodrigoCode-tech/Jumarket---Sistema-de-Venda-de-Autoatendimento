package com.desafio.jumarket.controllers

import com.desafio.jumarket.dtos.CarrinhoDTO
import com.desafio.jumarket.dtos.ItemCarrinhoDTO
import com.desafio.jumarket.services.CarrinhoService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/carrinhos")
class CarrinhoController (private val carrinhoService: CarrinhoService){

    @PostMapping("/abrir/cliente/{clienteId}")
    fun abrirCarrinho(
        @PathVariable clienteId: Long
    ): ResponseEntity<CarrinhoDTO> {
        val carrinho = carrinhoService.abrirCarrinho(clienteId)
        return ResponseEntity.ok(carrinho)
    }
    @GetMapping("/cliente/{clienteId}")
    fun listarItensPorCliente(@PathVariable clienteId: Long): ResponseEntity<List<ItemCarrinhoDTO>>{
        val itensCarrinho = carrinhoService.listarItensPorCliente(clienteId)
        return ResponseEntity.ok(itensCarrinho)
    }
    @PostMapping("/{carrinhoId}/produtos")
    fun adicionarProduto(
        @PathVariable carrinhoId: Long,
        @RequestBody @Valid itemCarrinhoDTO: ItemCarrinhoDTO
    ): ResponseEntity<CarrinhoDTO> {
        val carrinho = carrinhoService.adicionarProduto(carrinhoId, itemCarrinhoDTO)
        return ResponseEntity.ok(carrinho)
    }

    @DeleteMapping("/{carrinhoId}/item/{itemId}")
    fun removerItem(
        @PathVariable carrinhoId: Long,
        @PathVariable itemId: Long
    ): ResponseEntity<CarrinhoDTO> {
        val carrinho = carrinhoService.removerItem(carrinhoId, itemId)
        return ResponseEntity.ok(carrinho)
    }

    @DeleteMapping("/{carrinhoId}")
    fun excluirCarrinho(@PathVariable carrinhoId: Long): ResponseEntity<Unit> {
        carrinhoService.excluirCarrinho(carrinhoId)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

}