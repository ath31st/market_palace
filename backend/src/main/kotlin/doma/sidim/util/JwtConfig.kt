package doma.sidim.util

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import doma.sidim.model.User
import io.github.cdimascio.dotenv.dotenv
import java.util.*

object JwtConfig {
    private val dotenv = dotenv()
    private val secret = dotenv["JWT_SECRET"]
    private val issuer = dotenv["JWT_ISSUER"]
    private val validityInMs = dotenv["JWT_VALIDITY_MS"].toLong()

    private val algorithm = Algorithm.HMAC512(secret)

    val verifier: JWTVerifier = JWT
        .require(algorithm)
        .withIssuer(issuer)
        .build()

    fun generateToken(user: User): String {
        return JWT.create()
            .withIssuer(issuer)
            .withClaim("email", user.email)
            .withClaim("user_id", user.id)
            .withExpiresAt(Date(System.currentTimeMillis() + validityInMs))
            .sign(algorithm)
    }
}
