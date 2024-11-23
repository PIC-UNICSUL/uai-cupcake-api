package com.uai.cupcake.controller

import com.uai.cupcake.request.OrderRequest
import com.uai.cupcake.response.OrderResponse
import com.uai.cupcake.service.OrderService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/order")
class OrderController(
    private val orderService: OrderService
) {

    @PostMapping
    fun post(@RequestBody request: OrderRequest, token: JwtAuthenticationToken) : ResponseEntity<OrderResponse> {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(request, token))
    }

    @GetMapping("/me")
    fun getMe(token: JwtAuthenticationToken) : ResponseEntity<List<OrderResponse>>{
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getMe(token))
    }
}