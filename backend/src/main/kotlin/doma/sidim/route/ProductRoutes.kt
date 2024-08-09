package doma.sidim.route

import doma.sidim.service.ProductService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.SortOrder

fun Route.productRoutes(productService: ProductService) {
    get("/products/{id}") {
        val productId = call.extractId()
        productService.getProduct(productId)?.let {
            call.respond(HttpStatusCode.OK, it)
        } ?: call.respond(HttpStatusCode.NotFound)
    }

    get("/products") {
        val page = call.parameters["page"]?.toIntOrNull() ?: 1
        val size = call.parameters["size"]?.toIntOrNull() ?: 10
        val search = call.parameters["search"]
        val sortBy = call.parameters["sortBy"]
        val sortOrder = when (call.parameters["sortOrder"]) {
            "desc" -> SortOrder.DESC
            else -> SortOrder.ASC
        }

        if (page < 1 || size < 1) {
            call.respond(
                HttpStatusCode.BadRequest,
                "Page and size parameters must be positive integers."
            )
            return@get
        }

        val productsPage = productService.getProducts(page, size, search, sortBy, sortOrder)
        call.respond(HttpStatusCode.OK, productsPage)
    }
}

private fun ApplicationCall.extractId(): Long {
    return this.parameters["id"]?.toLong() ?: throw IllegalArgumentException("Invalid format ID")
}