package com.uai.cupcake.repository

import com.uai.cupcake.domain.Order
import com.uai.cupcake.domain.User
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface OrderRepository : JpaRepository<Order, UUID> {

    fun findOrdersByUser(user: User): MutableList<Order>

    fun findOrdersByStatusIn(statuses: List<String>, pageable: Pageable): MutableList<Order>
}