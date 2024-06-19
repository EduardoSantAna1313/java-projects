package br.com.edu.app.test.containers.domain

enum class ApiStatus {
    PENDING, CREATED;

    companion object {
        fun fromString(status: String): ApiStatus {
            return entries.first { it.toString() == status }
        }
    }
}