package doma.sidim.model

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

@Serializable
data class User(
    val id: Long? = null,
    val firstname: String,
    val lastname: String,
    val email: String,
    val password: String,
)

object Users : Table("users") {
    val id = long("user_id").autoIncrement()
    val email = varchar("email", 500)
    val firstname = varchar("first_name", 500)
    val lastname = varchar("last_name", 500)
    val password = varchar("password", 500)

    override val primaryKey = PrimaryKey(id)
}