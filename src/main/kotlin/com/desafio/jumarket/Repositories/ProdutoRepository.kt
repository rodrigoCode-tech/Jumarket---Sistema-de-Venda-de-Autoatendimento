package com.desafio.jumarket.Repositories

import com.desafio.jumarket.Models.Produto
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProdutoRepository: JpaRepository<Produto,Long>