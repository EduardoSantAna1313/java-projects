package br.com.edu.app.test.containers.infraestructure.rest

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.lang.Exception

@ControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(value = [Exception::class])
    fun handler(error: Exception): ResponseEntity<Any> {
        return ResponseEntity.internalServerError().body(error.message)
    }
}