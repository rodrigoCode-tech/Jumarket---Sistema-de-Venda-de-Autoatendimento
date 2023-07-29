package com.desafio.jumarket.models

import com.desafio.jumarket.enums.FormaDePagamento
import jakarta.persistence.*

@Entity
data class Venda(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "carrinho_id", nullable = false)
    val carrinho: Carrinho,

    val valorTotal : Double,
    @Enumerated(EnumType.STRING)
    val formaDePagamento : FormaDePagamento

)
