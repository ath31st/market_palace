package doma.sidim.model

import org.jetbrains.exposed.sql.Table

data class Product(
    val id: Long? = null,
    val title: String,
    val description: String,
    val vendorInfo: String,
    val imageLink: String,
    val price: Long,
)

object Products : Table("products") {
    val id = long("id").autoIncrement()
    val title = varchar("title", 255)
    val description = text("description")
    val vendorInfo = varchar("vendor_info", 255)
    val imageLink = varchar("image_link", 500)
    val price = long("price")

    override val primaryKey = PrimaryKey(id)
}