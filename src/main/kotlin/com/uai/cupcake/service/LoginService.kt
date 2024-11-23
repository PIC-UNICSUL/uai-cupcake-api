package com.uai.cupcake.service

import com.uai.cupcake.request.LoginRequest
import com.uai.cupcake.response.LoginResponse

interface LoginService {
    fun login(request: LoginRequest) : LoginResponse
}