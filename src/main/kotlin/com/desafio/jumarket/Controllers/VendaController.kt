package com.desafio.jumarket.Controllers

import com.desafio.jumarket.DTOs.VendaDTO
import com.desafio.jumarket.Enum.FormaDePagamento
import com.desafio.jumarket.Models.Venda
import com.desafio.jumarket.Services.VendaService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/vendas")
class VendaController (private val vendaService: VendaService){

}