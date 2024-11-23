package com.uai.cupcake.service.impl

import com.uai.cupcake.config.SecurityConstants
import com.uai.cupcake.domain.User
import com.uai.cupcake.exception.BusinessException
import com.uai.cupcake.repository.UserRepository
import com.uai.cupcake.request.LoginRequest
import com.uai.cupcake.response.LoginResponse
import com.uai.cupcake.service.LoginService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.oauth2.jwt.JwtClaimsSet
import org.springframework.security.oauth2.jwt.JwtEncoder
import org.springframework.security.oauth2.jwt.JwtEncoderParameters
import org.springframework.stereotype.Service
import java.time.Instant
import java.util.*


@Service
class LoginServiceImpl(
    private val userRepository: UserRepository,
    private val jwtEncoder: JwtEncoder,
    private val passwordEncoder: BCryptPasswordEncoder = BCryptPasswordEncoder(),
) : LoginService {

    override fun login(request: LoginRequest): LoginResponse {
        val user = findUserByMail(request.mail)
        validatePassword(request.password, user.password)

        val token = generateJwtToken(user.id)

        return LoginResponse(
            accessToken = token,
            expiresIn = SecurityConstants.TOKEN_EXPIRATION_TIME
        )
    }

    private fun findUserByMail(mail: String): User {
        return userRepository.findFirstByMail(mail)
            ?: throw BusinessException("Usuário não encontrado", "NOT_FOUND")
    }

    private fun validatePassword(rawPassword: String, encodedPassword: String) {
        if (!passwordEncoder.matches(rawPassword, encodedPassword)) {
            throw BusinessException("Senha incorreta", "NOT_FOUND")
        }
    }

    private fun generateJwtToken(userId: UUID): String {
        val now = Instant.now()
        val claims = JwtClaimsSet.builder()
            .issuer("uai-cupcakes-api")
            .subject(userId.toString())
            .issuedAt(now)
            .expiresAt(now.plusSeconds(SecurityConstants.TOKEN_EXPIRATION_TIME))
            .build()

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).tokenValue
    }
}