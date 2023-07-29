package com.desafio.jumarket.models

import com.desafio.jumarket.enums.FormaDePagamento
import jakarta.persistence.*

@Entity
data class Venda(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long? = null,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    val cliente: Cliente,
    val valorTotal : Double,
    @Enumerated(EnumType.STRING)
    val formaDePagamento : FormaDePagamento,
    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
    val itens : MutableList<ItemVenda> = mutableListOf()

)
