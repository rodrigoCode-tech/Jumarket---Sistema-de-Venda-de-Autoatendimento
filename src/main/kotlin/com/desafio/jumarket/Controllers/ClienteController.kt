package com.desafio.jumarket.Controllers

import com.desafio.jumarket.DTOs.ClienteDTO
import com.desafio.jumarket.Services.ClienteService
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
@RequestMapping("/clientes")
class ClienteController(private val clienteService: ClienteService) {

    @PostMapping
    fun criarUsuario(@RequestBody @Valid clienteDTO: ClienteDTO): ResponseEntity<ClienteDTO>{
        val novoCLiente = clienteService.criarCliente(clienteDTO)
        return ResponseEntity(novoCLiente,HttpStatus.CREATED)
    }

    @GetMapping("/{id}")
    fun buscarClientePorId(@PathVariable id: Long):ResponseEntity<ClienteDTO>{
        val cliente = clienteService.buscarClientePorId(id)
        return ResponseEntity(cliente, HttpStatus.OK)
    }

    @GetMapping
    fun listarCLientes(): ResponseEntity<List<ClienteDTO>>{
        val clientes =  clienteService.listarClientes()
        return ResponseEntity(clientes,HttpStatus.OK)
    }

    @PutMapping("/{id}")
    fun atualizarCliente(@PathVariable id: Long, @RequestBody @Valid clienteDTO: ClienteDTO): ResponseEntity<ClienteDTO> {
        val clienteAtualizado = clienteService.atualizarCliente(id, clienteDTO)
        return ResponseEntity.ok(clienteAtualizado)
    }

    @DeleteMapping("/{id}")
    fun excluirCliente(@PathVariable id: Long): ResponseEntity<Void> {
        clienteService.excluirCliente(id)
        return ResponseEntity.noContent().build()
    }
}