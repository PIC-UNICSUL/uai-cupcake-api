package com.uai.cupcake.service

import com.uai.cupcake.request.OrderStatusRequest
import com.uai.cupcake.response.OrderResponse


interface OrderAdministratorService {
    fun findOrders(status: List<String>?, order: String?, limit: Int?) : List<OrderResponse>

    fun update(request: OrderStatusRequest) : OrderResponse
}