package doma.sidim.model

import doma.sidim.dto.ProductInCartDto
import doma.sidim.dto.ProductInOrderDto
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

@Serializable
data class Product(
    val id: Long? = null,
    val title: String,
    val description: String,
    val vendorInfo: String,
    val imageLink: String,
    val price: Long,
)

fun Product.toProductInOrderDto(quantity: Int) = ProductInOrderDto(
    this.id!!,
    quantity,
    this.imageLink,
    this.price,
)

fun Product.toProductInCartDto(quantity: Int): ProductInCartDto {
    val smallDescription = if (this.description.length > 100) {
        this.description.substring(0, 100) + "..."
    } else {
        this.description
    }
    return ProductInCartDto(
        this.id!!,
        this.title,
        quantity,
        smallDescription,
        this.imageLink,
        this.price,
    )
}

object Products : Table("products") {
    val id = long("id").autoIncrement()
    val title = varchar("title", 255)
    val description = text("description")
    val vendorInfo = varchar("vendor_info", 255)
    val imageLink = varchar("image_link", 500)
    val price = long("price")

    override val primaryKey = PrimaryKey(id)
}