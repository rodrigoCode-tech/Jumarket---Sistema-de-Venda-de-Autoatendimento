package com.desafio.jumarket.models

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity
data class ItemCarrinho(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @ManyToOne
    @JoinColumn(name = "produto_id", referencedColumnName = "id", nullable = false)
    val produto: Produto,
    val quantidade: Int,
    @ManyToOne
    @JoinColumn(name ="carrinho_id",referencedColumnName = "id", nullable = false)
    val carrinho : Carrinho
)
