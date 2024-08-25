package doma.sidim.route

import doma.sidim.dto.NewCartDto
import doma.sidim.dto.CartChange
import doma.sidim.dto.CartDto
import doma.sidim.model.toCartDto
import doma.sidim.service.CartService
import doma.sidim.util.userId
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.cartRoutes(cartService: CartService) {

    authenticate("auth-jwt") {

        get("/cart") {
            val principal = call.principal<JWTPrincipal>()!!
            val authUserId = principal.userId()

            cartService.getCart(authUserId)?.let {
                call.respond(HttpStatusCode.OK, it.toCartDto())
            } ?: run {
                call.respond(HttpStatusCode.OK, CartDto(null, emptyList()))
            }
        }

        post("/cart") {
            val dto = call.receive<NewCartDto>()
            val principal = call.principal<JWTPrincipal>()!!
            val authUserId = principal.userId()

            cartService.existsCartByUserId(authUserId).let {
                if (it) {
                    cartService.deleteCartByUserId(authUserId)
                }
            }
            cartService.createCart(dto, authUserId)
            call.respond(HttpStatusCode.Created)
        }

        put("/cart") {
            val cartChange = call.receive<CartChange>()
            val principal = call.principal<JWTPrincipal>()!!
            val authUserId = principal.userId()

            cartService.updateProductInCart(authUserId, cartChange.productId, cartChange.quantity)
                .let {
                    if (it) {
                        call.respond(HttpStatusCode.OK)
                    } else {
                        call.respond(HttpStatusCode.BadRequest)
                    }
                }
        }

    }
}
