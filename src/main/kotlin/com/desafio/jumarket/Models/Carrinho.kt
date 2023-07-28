package com.desafio.jumarket.Models

import jakarta.persistence.*

@Entity
data class Carrinho(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long = 0,

    @OneToOne
    val cliente: Cliente,

    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
    val produtos : MutableList<ItemCarrinho> = mutableListOf()

)
