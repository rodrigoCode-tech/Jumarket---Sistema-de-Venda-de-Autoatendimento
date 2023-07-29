package com.desafio.jumarket.repositories

import com.desafio.jumarket.models.Produto
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProdutoRepository: JpaRepository<Produto,Long>