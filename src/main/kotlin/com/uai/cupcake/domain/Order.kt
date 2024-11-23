package com.uai.cupcake.domain

import com.uai.cupcake.request.OrderRequest
import com.uai.cupcake.response.OrderItemResponse
import com.uai.cupcake.response.OrderResponse
import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "cs_order")
data class Order(
    @Id
    @Column(name = "id", columnDefinition = "UUID")
    val id: UUID,

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    var user: User,

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    var status: OrderStatus,

    @Column(name = "created_at", nullable = false, updatable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "updated_at")
    var updatedAt: LocalDateTime = LocalDateTime.now(),
)

fun OrderRequest.toEntity(user: User): Order {
    return Order(
        id = UUID.randomUUID(),
        user = user,
        status = OrderStatus.PENDING,
        createdAt = LocalDateTime.now(),
        updatedAt = LocalDateTime.now()
    )
}

fun Order.toResponse(total: Double, orderItems: List<OrderItemResponse>): OrderResponse{
    return OrderResponse(
        id = this.id,
        user = this.user.toResponse(),
        status = this.status.name,
        total = total,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt,
        orderItems = orderItems
    )
}

enum class OrderStatus {
    PENDING,
    CONFIRMED,
    DELIVERED,
    CANCELED
}
