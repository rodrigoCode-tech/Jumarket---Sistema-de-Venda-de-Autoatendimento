package com.desafio.jumarket.Services.Impl

import com.desafio.jumarket.DTOs.ClienteDTO
import com.desafio.jumarket.Models.Cliente
import com.desafio.jumarket.Repositories.ClienteRepository
import com.desafio.jumarket.Services.ClienteService
import org.springframework.stereotype.Service

@Service
class ClienteServiceImpl(private val clienteRepository: ClienteRepository): ClienteService {

    override fun criarCliente(clienteDTO: ClienteDTO): ClienteDTO {
        val clienteExistente = clienteRepository.findByCpf(clienteDTO.cpf)
        if (clienteExistente != null) {
            throw RuntimeException("Cliente com CPF ${clienteDTO.cpf} já existe")
        }
        val cliente = Cliente(
            nome = clienteDTO.nome,
            cpf = clienteDTO.cpf
        )
        val novoCliente = clienteRepository.save(cliente)

        return ClienteDTO(
            id = novoCliente.id,
            nome = novoCliente.nome,
            cpf = novoCliente.cpf
        )

    }

    override fun buscarClientePorId(id: Long): ClienteDTO? {
        val cliente = clienteRepository.findById(id).
            orElseThrow{RuntimeException("CLiente não encontrado")}
        return cliente?.let {
            ClienteDTO(
                id = it.id,
                nome = it.nome,
                cpf = it.cpf
            )
        }
    }

    override fun listarClientes(): List<ClienteDTO> {
        val clientes = clienteRepository.findAll()
        return clientes.map {
            ClienteDTO(
                id = it.id,
                nome = it.nome,
                cpf = it.cpf
            )
        }
    }

    override fun atualizarCliente(id: Long, clienteDTO: ClienteDTO): ClienteDTO? {
        val cliente = clienteRepository.findById(id).
        orElseThrow{RuntimeException("CLiente não encontrado")}

        cliente.apply {
            nome = clienteDTO.nome
            cpf = clienteDTO.cpf
        }

        val clienteAtualizado = clienteRepository.save(cliente)

        return ClienteDTO(
            id = clienteAtualizado.id,
            nome = clienteAtualizado.nome,
            cpf = clienteAtualizado.cpf
        )
    }

    override fun excluirCliente(id: Long, clienteDTO: ClienteDTO){
        val cliente = clienteRepository.findById(id)
            .orElseThrow { RuntimeException("CLiente não encontrado") }

        clienteRepository.delete(cliente)
    }
}