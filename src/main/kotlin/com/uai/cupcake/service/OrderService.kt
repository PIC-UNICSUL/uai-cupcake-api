package com.uai.cupcake.service

import com.uai.cupcake.request.OrderRequest
import com.uai.cupcake.response.OrderResponse
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken

interface OrderService {

    fun createOrder(request: OrderRequest, token: JwtAuthenticationToken) : OrderResponse

    fun getMe(token: JwtAuthenticationToken) : List<OrderResponse>
}