package com.desafio.jumarket.repositories

import com.desafio.jumarket.models.Cliente
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface ClienteRepository :JpaRepository<Cliente,Long> {
    fun findFirstByCpf(cpf: String): Optional<Cliente>
}