package com.desafio.jumarket.Controllers

import com.desafio.jumarket.dtos.VendaDTO
import com.desafio.jumarket.enums.FormaDePagamento
import com.desafio.jumarket.services.VendaService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/vendas")
class VendaController (private val vendaService: VendaService){
    @PostMapping("/finalizar/{carrinhoId}/forma-pagamento/{formaPagamento}")
    fun finalizarVenda(
        @PathVariable carrinhoId: Long,
        @PathVariable formaPagamento: FormaDePagamento
    ): ResponseEntity<VendaDTO> {
        val venda = vendaService.finalizarVenda(formaPagamento, carrinhoId)
        return ResponseEntity.ok(venda)
    }

    @GetMapping
    fun listarVendas(): ResponseEntity<List<VendaDTO>> {
        val vendas = vendaService.listarVendas()
        return ResponseEntity.ok(vendas)
    }
}