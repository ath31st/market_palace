package doma.sidim.plugins

import doma.sidim.route.cartRoutes
import doma.sidim.route.orderRoutes
import doma.sidim.route.productRoutes
import doma.sidim.route.userRoutes
import doma.sidim.service.CartService
import doma.sidim.service.OrderService
import doma.sidim.service.ProductService
import doma.sidim.service.UserService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.autohead.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting(
    userService: UserService,
    productService: ProductService,
    cartService: CartService,
    orderService: OrderService
) {
    install(AutoHeadResponse)
    install(Resources)
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.respondText(text = "500: $cause", status = HttpStatusCode.InternalServerError)
        }
    }

    routing {
        route("/api") {
            route("/v1") {
                userRoutes(userService)
                productRoutes(productService)
                cartRoutes(cartService)
                orderRoutes(orderService, cartService)
            }
        }
    }
}
