package com.uai.cupcake.service

import com.uai.cupcake.response.OrderResponse


interface OrderAdministratorService {
    fun findOrders(status: List<String>?, order: String?, limit: Int?) : List<OrderResponse>
}