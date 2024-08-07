package doma.sidim.plugins

import doma.sidim.service.UserService
import doma.sidim.util.JwtConfig
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*

fun Application.configureSecurity(userService: UserService) {
    authentication {
        jwt("auth-jwt") {
            realm = "Ktor Server"
            verifier(JwtConfig.verifier)
            validate { credential ->
                val email = credential.payload.getClaim("email").asString()
                if (email != null && userService.getUserByEmail(email) != null) {
                    JWTPrincipal(credential.payload)
                } else {
                    null
                }
            }
            challenge { _, _ ->
                call.respond(
                    HttpStatusCode.Unauthorized,
                    mapOf("message" to "Token is not valid or has expired")
                )
            }
        }
    }
}
