package com.uai.cupcake.repository

import com.uai.cupcake.domain.Order
import com.uai.cupcake.domain.OrderItem
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface OrderItemRepository : JpaRepository<OrderItem, UUID> {
    abstract fun findOrderItemByOrder(order: Order): MutableList<OrderItem>
}