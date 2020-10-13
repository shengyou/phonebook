package io.kraftsman.responses

data class Contact(
    val id: Long?,
    val name: String,
    val email: String,
    val mobile: String,
    val address: String,
    val createdAt: String,
    val updatedAt: String,
)
