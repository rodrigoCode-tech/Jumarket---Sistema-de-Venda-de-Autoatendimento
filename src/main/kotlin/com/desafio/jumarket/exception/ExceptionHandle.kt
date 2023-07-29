package com.desafio.jumarket.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class ExceptionHandle : ResponseEntityExceptionHandler() {

    @ExceptionHandler
    fun handleNotFoundException(exception : DataNotFoundException) : ResponseEntity<Any>{
        return ResponseEntity(HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler
    fun handleCpfJaCadastradoException(exception : CpfJaCadastradoException) : ResponseEntity<Any>{
        return ResponseEntity.badRequest().body(exception.message)
    }
}