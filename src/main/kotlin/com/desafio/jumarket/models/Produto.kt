package com.desafio.jumarket.models

import jakarta.persistence.*

@Entity
data class Produto(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long? = null,
    var nome : String,
    var unidadeDeMedida : String,
    var precoUnitario : Double,

    @ManyToOne
    var categoria: Categoria
)
