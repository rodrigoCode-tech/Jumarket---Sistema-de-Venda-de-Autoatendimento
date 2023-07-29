package com.desafio.jumarket.controllers

import com.desafio.jumarket.dtos.CarrinhoDTO
import com.desafio.jumarket.dtos.ItemCarrinhoDTO
import com.desafio.jumarket.services.CarrinhoService
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
@RequestMapping("/api/carrinho")
class CarrinhoController (private val carrinhoService: CarrinhoService){

    @GetMapping
    fun listarItensPorCliente(@PathVariable clienteId: Long): ResponseEntity<List<ItemCarrinhoDTO>>{
        val itensCarrinho = carrinhoService.listarItensPorCliente(clienteId)
        return ResponseEntity.ok(itensCarrinho)
    }
    @PostMapping("/clientes/{clienteId}/carrinho/{carrinhoId}/produtos")
    fun adicionarProduto(
        @PathVariable clienteId: Long,
        @PathVariable carrinhoId: Long,
        @RequestBody itemCarrinhoDTO: ItemCarrinhoDTO
    ): ResponseEntity<CarrinhoDTO> {
        val carrinho = carrinhoService.adicionarProduto(clienteId, carrinhoId, itemCarrinhoDTO)
        return ResponseEntity.ok(carrinho)
    }

    @DeleteMapping("/carrinho/{carrinhoId}/produto/{produtoId}")
    fun removerProduto(
        @PathVariable carrinhoId: Long,
        @PathVariable produtoId: Long
    ): ResponseEntity<CarrinhoDTO> {
        val carrinho = carrinhoService.removerProduto(carrinhoId, produtoId)
        return ResponseEntity.ok(carrinho)
    }

    @DeleteMapping("/{carrinhoId}")
    fun excluirCarrinho(@PathVariable carrinhoId: Long): ResponseEntity<Unit> {
        carrinhoService.excluirCarrinho(carrinhoId)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

}