package doma.sidim.route

import doma.sidim.dto.UserLoginDto
import doma.sidim.dto.UserRegisterDto
import doma.sidim.service.UserService
import doma.sidim.util.JwtConfig
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.userRoutes(userService: UserService) {
    post("/signup") {
        val userDto = call.receive<UserRegisterDto>()
        userService.getUserByEmail(userDto.email)?.let {
            call.respond(HttpStatusCode.BadRequest, "Email is already in use")
        } ?: run {
            val id = userService.createUser(userDto)
            call.respond(HttpStatusCode.Created, id)
        }
    }

    post("/login") {
        val loginDto = call.receive<UserLoginDto>()
        userService.authenticate(loginDto.email, loginDto.password)?.let { user ->
            val token = JwtConfig.generateToken(user)
            call.respond(mapOf("token" to token))
        } ?: run {
            call.respond(HttpStatusCode.Unauthorized, mapOf("message" to "Invalid credentials"))
        }
    }
}
