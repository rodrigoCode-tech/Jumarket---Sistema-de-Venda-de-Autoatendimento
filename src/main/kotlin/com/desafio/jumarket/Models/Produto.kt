package com.desafio.jumarket.Models

import jakarta.persistence.*

@Entity
data class Produto(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long = 0,
    val nome : String,
    val unidadeDeMedida : String,
    val precoUnitario : Double,

    @ManyToOne
    val categoria: Categoria
)
