package doma.sidim.util

import io.ktor.server.auth.jwt.*

fun JWTPrincipal.userId(): Long {
    return this.payload.getClaim("user_id").asLong()
}