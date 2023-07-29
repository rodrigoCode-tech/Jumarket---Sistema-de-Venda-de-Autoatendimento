package com.desafio.jumarket.Repositories

import com.desafio.jumarket.Models.Carrinho
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface CarrinhoRepository : JpaRepository<Carrinho, Long>{

    @Query("SELECT c FROM Carrinho c WHERE c.cliente.id = :clienteId")
    fun findByCliente(@Param("clienteId") clienteId: Long): List<Carrinho>
}