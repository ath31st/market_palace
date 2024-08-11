package doma.sidim

import doma.sidim.plugins.*
import doma.sidim.repository.CartRepository
import doma.sidim.repository.OrderRepository
import doma.sidim.repository.ProductRepository
import doma.sidim.repository.UserRepository
import doma.sidim.service.CartService
import doma.sidim.service.OrderService
import doma.sidim.service.ProductService
import doma.sidim.service.UserService
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    System.setProperty("io.ktor.development", "true")

    embeddedServer(
        Netty,
        port = 8018,
        host = "0.0.0.0",
        watchPaths = listOf("classes", "resources"),
        module = Application::module
    )
        .start(wait = true)
}

fun Application.module() {
    val userRepository = UserRepository()
    val productRepository = ProductRepository()

    val userService = UserService(userRepository)
    val productService = ProductService(productRepository)
    val orderService = OrderService(OrderRepository(productRepository))
    val cartService = CartService(CartRepository(productRepository))

    configureSecurity(userService)
    configureHTTP()
    configureMonitoring()
    configureSerialization()
    configureDatabases()
    configureRouting(userService, productService, cartService, orderService)
}
