package com.uai.cupcake.service

import com.uai.cupcake.request.UserRequest
import com.uai.cupcake.request.UserUpdateRequest
import com.uai.cupcake.response.UserResponse
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken

interface UserService {

    fun createUser(userRequest: UserRequest) : UserResponse

    fun updateUser(userRequest: UserUpdateRequest, token: JwtAuthenticationToken) : UserResponse

    fun getUser(token: JwtAuthenticationToken) : UserResponse

}