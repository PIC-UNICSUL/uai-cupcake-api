package com.uai.cupcake.service.impl

import com.uai.cupcake.domain.toEntity
import com.uai.cupcake.domain.toResponse
import com.uai.cupcake.exception.BadRequestException
import com.uai.cupcake.exception.ErrorCodeEnum
import com.uai.cupcake.repository.OrderItemRepository
import com.uai.cupcake.repository.OrderRepository
import com.uai.cupcake.repository.ProductRepository
import com.uai.cupcake.repository.UserRepository
import com.uai.cupcake.request.OrderRequest
import com.uai.cupcake.response.ErrorResponse
import com.uai.cupcake.response.OrderItemResponse
import com.uai.cupcake.response.OrderResponse
import com.uai.cupcake.service.OrderService
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class OrderServiceImpl(
    private val orderRepository: OrderRepository,
    private val orderItemRepository: OrderItemRepository,
    private val productRepository: ProductRepository,
    private val userRepository: UserRepository
) : OrderService {

    @Transactional
    override fun createOrder(request: OrderRequest, token: JwtAuthenticationToken): OrderResponse {
        val user = userRepository.findById(UUID.fromString(token.name)).orElseThrow {
            throw BadRequestException(
                ErrorResponse(
                    ErrorCodeEnum.USER_NOT_FOUND,
                    "Usuário ${token.name} não encontrado.",
                    null
                )
            )
        }

        val order = request.toEntity(user)
        orderRepository.save(order)

        val orderItems = mutableListOf<OrderItemResponse>()
        var totalAmount = 0.0

        request.orderItems.forEach {
            val product = productRepository.findById(it.id).orElseThrow {
                throw BadRequestException(
                    ErrorResponse(
                        ErrorCodeEnum.PRODUCT_NOT_FOUND,
                        "Produto ${it.id} não encontrado.",
                        null
                    )
                )
            }

            val orderItem = it.toEntity(order, product)
            orderItemRepository.save(orderItem)

            orderItems.add(orderItem.toResponse())
            totalAmount += (product.price.toDouble() * it.quantity.toDouble())
        }

        return order.toResponse(totalAmount, orderItems)
    }

    override fun getMe(token: JwtAuthenticationToken): List<OrderResponse> {
        val userId = UUID.fromString(token.name)
        val user = userRepository.findById(userId)
            .orElseThrow { throw BadRequestException(
                    ErrorResponse(
                        ErrorCodeEnum.USER_NOT_FOUND,
                        "Usuário ${token.name} não encontrado.",
                        null
                    )
                )
            }

        val orders = orderRepository.findOrdersByUser(user)

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


}