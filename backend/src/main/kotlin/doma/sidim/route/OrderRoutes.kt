package doma.sidim.route

import doma.sidim.dto.NewOrderDto
import doma.sidim.model.toOrderDto
import doma.sidim.service.CartService
import doma.sidim.service.OrderService
import doma.sidim.util.userId
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.orderRoutes(orderService: OrderService, cartService: CartService) {

    authenticate("auth-jwt") {

        get("/my-orders") {
            val principal = call.principal<JWTPrincipal>()!!
            val authUserId = principal.userId()

            val orders = orderService.getOrdersByUserId(authUserId)
            if (orders.isNotEmpty()) {
                call.respond(HttpStatusCode.OK, orders.map { it.toOrderDto() })
            } else {
                call.respond(HttpStatusCode.BadRequest)
            }
        }

        post("/my-orders") {
            val principal = call.principal<JWTPrincipal>()!!
            val authUserId = principal.userId()

            val dto = call.receive<NewOrderDto>()
            orderService.createOrder(dto, authUserId)
            cartService.deleteCartByUserId(authUserId).let {
                if (it) {
                    call.respond(HttpStatusCode.Created)
                } else {
                    call.respond(HttpStatusCode.BadRequest)
                }
            }
        }
    }
}
