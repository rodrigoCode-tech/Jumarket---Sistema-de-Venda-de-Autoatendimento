package com.desafio.jumarket.Controllers

import com.desafio.jumarket.DTOs.ClienteDTO
import com.desafio.jumarket.Services.ClienteService
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
@RequestMapping("/api/clientes")
class ClienteController(private val clienteService: ClienteService) {

    @PostMapping
    fun criarUsuario(@RequestBody clienteDTO: ClienteDTO): ResponseEntity<ClienteDTO>{
        val novoCLiente = clienteService.criarCliente(clienteDTO)
        return ResponseEntity(novoCLiente,HttpStatus.CREATED)
    }

    @GetMapping("/{id}")
    fun buscarClientePorId(@PathVariable id: Long):ResponseEntity<ClienteDTO>{
        val cliente = clienteService.buscarClientePorId(id)
        return if (cliente != null) {
            ResponseEntity(cliente, HttpStatus.OK)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @GetMapping
    fun listarCLientes(): ResponseEntity<List<ClienteDTO>>{
        val clientes =  clienteService.listarClientes()
        return ResponseEntity(clientes,HttpStatus.OK)
    }

    @PutMapping("/{id}")
    fun atualizarCliente(@PathVariable id: Long, @RequestBody clienteDTO: ClienteDTO): ResponseEntity<ClienteDTO> {
        val clienteExistente = clienteService.buscarClientePorId(id)

        return if (clienteExistente != null) {
            val clienteAtualizado = clienteService.atualizarCliente(id, clienteDTO)
            ResponseEntity.ok(clienteAtualizado)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun excluirCliente(@PathVariable id: Long): ResponseEntity<Void> {
        val clienteExistente = clienteService.buscarClientePorId(id)

        return if (clienteExistente != null) {
            clienteService.excluirCliente(id)
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}