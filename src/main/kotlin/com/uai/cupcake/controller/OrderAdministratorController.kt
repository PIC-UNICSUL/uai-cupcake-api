package com.uai.cupcake.controller

import com.uai.cupcake.response.OrderResponse
import com.uai.cupcake.service.OrderAdministratorService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/admin/order")
class OrderAdministratorController(
    private val orderAdministratorService: OrderAdministratorService
) {
    @GetMapping
    fun get(@RequestParam("status") status: List<String>?, @RequestParam("order") order: String?,
            @RequestParam("limit") limit: Int?) : ResponseEntity<List<OrderResponse>>{
        return ResponseEntity.status(HttpStatus.OK).body(orderAdministratorService.findOrders(status, order, limit))
    }
}