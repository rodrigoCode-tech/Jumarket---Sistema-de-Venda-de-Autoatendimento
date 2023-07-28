package com.desafio.jumarket.Repositories

import com.desafio.jumarket.Models.Cliente
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ClienteRepository :JpaRepository<Cliente,Long>