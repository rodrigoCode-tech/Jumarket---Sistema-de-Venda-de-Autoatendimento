package com.desafio.jumarket.Repositories

import com.desafio.jumarket.Models.Venda
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface VendaRepository : JpaRepository<Venda,Long>