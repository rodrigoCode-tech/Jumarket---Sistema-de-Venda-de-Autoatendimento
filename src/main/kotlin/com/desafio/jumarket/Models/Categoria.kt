package com.desafio.jumarket.Models

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import javax.annotation.processing.Generated

@Entity
data class Categoria(

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    val id : Long? = null,
    var nome : String
)
