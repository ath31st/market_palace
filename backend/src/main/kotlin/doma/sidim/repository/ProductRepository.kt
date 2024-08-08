package doma.sidim.repository

import doma.sidim.dto.Page
import doma.sidim.model.Product
import doma.sidim.model.Products
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

class ProductRepository {
    fun create(item: Product): Long {
        var id: Long = 0
        transaction {
            id = Products.insert {
                it[title] = item.title
                it[description] = item.description
                it[vendorInfo] = item.vendorInfo
                it[imageLink] = item.imageLink
                it[price] = item.price
            }[Products.id]
        }
        return id
    }

    fun read(id: Long): Product? {
        return transaction {
            Products.select { Products.id eq id }
                .mapNotNull {
                    it.toProduct()
                }.singleOrNull()
        }
    }

    fun update(id: Long, item: Product): Boolean {
        return transaction {
            Products.update({ Products.id eq id }) {
                it[title] = item.title
                it[description] = item.description
                it[vendorInfo] = item.vendorInfo
                it[imageLink] = item.imageLink
                it[price] = item.price
            } > 0
        }
    }

    fun delete(id: Long): Boolean {
        return transaction {
            Products.deleteWhere { Products.id eq id } > 0
        }
    }

    fun getPaged(offset: Int, limit: Int): Page<Product> {
        return transaction {
            val totalItems = Products.selectAll().count()
            val items = Products.selectAll()
                .limit(limit, offset = offset.toLong())
                .map { it.toProduct() }
            val totalPages = (totalItems / limit).toInt() + if (totalItems % limit != 0L) 1 else 0
            Page(
                items = items,
                totalItems = totalItems,
                totalPages = totalPages,
                currentPage = offset / limit + 1
            )
        }
    }

    private fun ResultRow.toProduct(): Product {
        return Product(
            id = this[Products.id],
            title = this[Products.title],
            description = this[Products.description],
            vendorInfo = this[Products.vendorInfo],
            imageLink = this[Products.imageLink],
            price = this[Products.price],
        )
    }
}
