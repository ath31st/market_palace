package doma.sidim.dto

import kotlinx.serialization.Serializable

@Serializable
data class UserRegisterDto(
    val firstname: String,
    val lastname: String,
    val email: String,
    val password: String,
)
