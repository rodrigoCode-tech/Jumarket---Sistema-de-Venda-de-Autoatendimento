package com.desafio.jumarket.controllers

import com.desafio.jumarket.services.VendaService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/vendas")
class VendaController (private val vendaService: VendaService){

}