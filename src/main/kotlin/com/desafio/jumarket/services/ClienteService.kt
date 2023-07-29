package com.desafio.jumarket.services

import com.desafio.jumarket.dtos.ClienteDTO

interface ClienteService {
    fun criarCliente(clienteDTO: ClienteDTO): ClienteDTO
    fun buscarClientePorId(id: Long): ClienteDTO?
    fun listarClientes(): List<ClienteDTO>
    fun atualizarCliente(id: Long, clienteDTO: ClienteDTO): ClienteDTO?
    fun excluirCliente(id: Long)
}