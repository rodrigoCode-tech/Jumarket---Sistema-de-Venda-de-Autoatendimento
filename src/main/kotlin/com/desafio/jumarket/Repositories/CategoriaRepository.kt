package com.desafio.jumarket.Repositories

import com.desafio.jumarket.Models.Categoria
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CategoriaRepository: JpaRepository <Categoria, Long>