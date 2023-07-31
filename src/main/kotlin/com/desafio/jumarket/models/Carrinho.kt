package com.desafio.jumarket.models

import jakarta.persistence.*

@Entity
data class Carrinho(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @OneToOne
    val cliente: Cliente,

    @OneToMany(mappedBy = "carrinho", cascade = [CascadeType.ALL], orphanRemoval = true)
    val itens: MutableList<ItemCarrinho> = mutableListOf()

)
