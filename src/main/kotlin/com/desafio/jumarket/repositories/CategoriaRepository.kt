package com.desafio.jumarket.repositories

import com.desafio.jumarket.models.Categoria
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CategoriaRepository: JpaRepository <Categoria, Long>