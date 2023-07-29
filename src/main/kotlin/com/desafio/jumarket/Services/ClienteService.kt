package com.desafio.jumarket.Services

import com.desafio.jumarket.DTOs.ClienteDTO

interface ClienteService {
    fun criarCliente(clienteDTO: ClienteDTO): ClienteDTO
    fun buscarClientePorId(id: Long): ClienteDTO?
    fun listarClientes(): List<ClienteDTO>
    fun atualizarCliente(id: Long, clienteDTO: ClienteDTO): ClienteDTO?
    fun excluirCliente(id: Long)
}