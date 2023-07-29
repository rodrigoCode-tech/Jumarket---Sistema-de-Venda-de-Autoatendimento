package com.desafio.jumarket.services.impl

import com.desafio.jumarket.dtos.ClienteDTO
import com.desafio.jumarket.models.Cliente
import com.desafio.jumarket.repositories.ClienteRepository
import com.desafio.jumarket.services.ClienteService
import com.desafio.jumarket.exception.CpfJaCadastradoException
import com.desafio.jumarket.exception.DataNotFoundException
import org.springframework.stereotype.Service

@Service
class ClienteServiceImpl(private val clienteRepository: ClienteRepository): ClienteService {

    override fun criarCliente(clienteDTO: ClienteDTO): ClienteDTO {
        clienteRepository.findFirstByCpf(clienteDTO.cpf)
            .ifPresent{throw CpfJaCadastradoException("Cliente com CPF ${clienteDTO.cpf} já existe")}
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
            orElseThrow{DataNotFoundException("Cliente não encontrado")}
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
        orElseThrow{DataNotFoundException("Cliente não encontrado")}

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

    override fun excluirCliente(id: Long){
        val cliente = clienteRepository.findById(id)
            .orElseThrow { DataNotFoundException("CLiente não encontrado") }

        clienteRepository.delete(cliente)
    }
}