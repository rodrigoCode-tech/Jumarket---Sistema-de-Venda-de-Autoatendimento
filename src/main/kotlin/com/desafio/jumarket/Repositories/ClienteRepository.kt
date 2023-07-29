package com.desafio.jumarket.Repositories

import com.desafio.jumarket.Models.Cliente
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface ClienteRepository :JpaRepository<Cliente,Long> {
    fun findFirstByCpf(cpf: String): Optional<Cliente>
}