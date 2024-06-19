package br.com.edu.app.test.containers.domain

import java.time.LocalDateTime
import java.util.UUID

data class Api (
    val id: UUID,
    val name: String,
    val description: String,
    val status: ApiStatus,
    val created: LocalDateTime,
    val createdBy: String,
    val updated: LocalDateTime,
    val updatedBy: String
) {

    constructor(name: String, description: String, status: ApiStatus,
                createdBy: String): this(UUID.randomUUID(), name, description, status, LocalDateTime.now(), createdBy, LocalDateTime.now(), createdBy)

}
