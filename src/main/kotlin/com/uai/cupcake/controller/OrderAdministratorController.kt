package com.uai.cupcake.controller

import com.uai.cupcake.request.OrderStatusRequest
import com.uai.cupcake.response.OrderResponse
import com.uai.cupcake.service.OrderAdministratorService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/admin/order")
class OrderAdministratorController(
    private val orderAdministratorService: OrderAdministratorService
) {
    @GetMapping
    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN')")
    fun get(@RequestParam("status") status: List<String>?, @RequestParam("order") order: String?,
            @RequestParam("limit") limit: Int?) : ResponseEntity<List<OrderResponse>>{
        return ResponseEntity.status(HttpStatus.OK).body(orderAdministratorService.findOrders(status, order, limit))
    }

    @PutMapping("/status")
    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN')")
    fun update(@Valid @RequestBody request: OrderStatusRequest) : ResponseEntity<OrderResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(orderAdministratorService.update(request))
    }
}