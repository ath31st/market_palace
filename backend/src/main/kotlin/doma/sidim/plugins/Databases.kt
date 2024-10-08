package doma.sidim.plugins

import doma.sidim.model.*
import doma.sidim.util.PreparedData
import io.ktor.server.application.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.configureDatabases() {
    val database = Database.connect(
        url = "jdbc:sqlite:./market.db",
        user = "root",
        driver = "org.sqlite.JDBC",
        password = ""
    )

    transaction(database) {
        SchemaUtils.create(Users, Products, Orders, Carts, OrderProducts, CartProducts)
        PreparedData.insertInitialData()
    }
}
