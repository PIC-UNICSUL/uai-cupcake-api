package com.uai.cupcake.service.impl

import com.uai.cupcake.domain.OrderStatus
import com.uai.cupcake.domain.toResponse
import com.uai.cupcake.exception.BusinessException
import com.uai.cupcake.repository.OrderItemRepository
import com.uai.cupcake.repository.OrderRepository
import com.uai.cupcake.request.OrderStatusRequest
import com.uai.cupcake.response.OrderResponse
import com.uai.cupcake.service.OrderAdministratorService
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class OrderOrderServiceImplServiceImpl(
    private val orderRepository: OrderRepository,
    private val orderItemRepository: OrderItemRepository,
) : OrderAdministratorService {

    override fun findOrders(status: List<String>?, order: String?, limit: Int?): List<OrderResponse> {

        val sortOrder = when (order?.lowercase()) {
            "desc" -> Sort.by(Sort.Direction.DESC, "createdAt")
            "asc" -> Sort.by(Sort.Direction.ASC, "createdAt")
            else -> Sort.by(Sort.Direction.DESC, "createdAt")
        }
        val pageLimit = limit ?: 50
        val pageable: Pageable = PageRequest.of(0, pageLimit, sortOrder)


        val orders = if (status.isNullOrEmpty()) {
            orderRepository.findAll(pageable).content
        } else {
            orderRepository.findOrdersByStatusIn(status, pageable)
        }

        return orders.map { order ->
            val orderItems = orderItemRepository.findOrderItemByOrder(order)

            val totalAmount = orderItems.sumOf { orderItem ->
                val product = orderItem.product
                product.price.toDouble() * orderItem.quantity.toDouble()
            }

            val orderItemResponses = orderItems.map { it.toResponse() }
            order.toResponse(totalAmount, orderItemResponses)
        }
    }

    override fun update(request: OrderStatusRequest): OrderResponse {
        val order = orderRepository.findById(request.id)
            .orElseThrow { BusinessException("Order not found with ID: ${request.id}", "BAD_REQUEST") }

        order.status = OrderStatus.valueOf(request.status)
        order.additionalInfo = request.additionalInfo
        order.updatedAt = LocalDateTime.now()

        orderRepository.saveAndFlush(order)

        return order.let {
            val orderItems = orderItemRepository.findOrderItemByOrder(it)

            val totalAmount = orderItems.sumOf { orderItem ->
                val product = orderItem.product
                product.price.toDouble() * orderItem.quantity.toDouble()
            }

            val orderItemResponses = orderItems.map { it.toResponse() }
            it.toResponse(totalAmount, orderItemResponses)
        }

    }

}