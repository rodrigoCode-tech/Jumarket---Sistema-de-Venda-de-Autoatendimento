package com.desafio.jumarket.repositories

import com.desafio.jumarket.models.Venda
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface VendaRepository : JpaRepository<Venda,Long>