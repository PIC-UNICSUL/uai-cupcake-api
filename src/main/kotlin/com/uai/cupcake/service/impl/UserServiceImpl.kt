package com.uai.cupcake.service.impl

import com.uai.cupcake.domain.toEntity
import com.uai.cupcake.domain.toResponse
import com.uai.cupcake.exception.BadRequestException
import com.uai.cupcake.exception.ErrorCodeEnum
import com.uai.cupcake.repository.UserRepository
import com.uai.cupcake.request.UserRequest
import com.uai.cupcake.request.UserUpdateRequest
import com.uai.cupcake.response.ErrorResponse
import com.uai.cupcake.response.UserResponse
import com.uai.cupcake.service.UserService
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class UserServiceImpl(
    private val userRepository: UserRepository
) : UserService {
    override fun createUser(userRequest: UserRequest): UserResponse {
        userRepository.findFirstByMail(userRequest.mail)?.let {
            throw BadRequestException(
                ErrorResponse(
                    ErrorCodeEnum.USER_ALREADY_REGISTERED,
                    "Usuário com e-mail ${it.mail} já está cadastrado. Caso não consiga realizar o login, entre em contato com o suporte para assistência.",
                    null
                )
            )        }
        val newUser = userRequest.toEntity()
        val savedUser = userRepository.save(newUser)
        return savedUser.toResponse()
    }

    override fun updateUser(userRequest: UserUpdateRequest, token: JwtAuthenticationToken): UserResponse {
        var user = userRepository.findById(UUID.fromString(token.name)).get()
        user.fullName = userRequest.name
        user.phoneNumber = userRequest.phone
        user.updatedAt = LocalDateTime.now()
        val savedUser = userRepository.saveAndFlush(user)
        return savedUser.toResponse()
    }

    override fun getUser(token: JwtAuthenticationToken): UserResponse {
        val user = userRepository.findById(UUID.fromString(token.name)).get()
        return user.toResponse()
    }
}