package doma.sidim.util

import at.favre.lib.crypto.bcrypt.BCrypt
import doma.sidim.model.Users
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

object PreparedData {
    fun insertInitialData() {
        transaction {
            if (Users.select { Users.email eq "petr@petrov.com" }.empty()) {
                Users.insert {
                    it[firstname] = "Petr"
                    it[lastname] = "Petrov"
                    it[email] = "petr@petrov.com"
                    it[password] = BCrypt.withDefaults().hashToString(12, "123".toCharArray())
                }
            }
        }
    }
}
