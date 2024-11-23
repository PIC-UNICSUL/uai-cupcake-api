package com.uai.cupcake.domain

import com.uai.cupcake.request.OrderItemRequest
import com.uai.cupcake.response.OrderItemResponse
import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "cs_order_items")
class OrderItem(
    @Id
    @Column(name = "id", columnDefinition = "UUID")
    val id: UUID,

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    var order: Order,

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    val product: Product,

    @Column(name = "quantity", nullable = false)
    val quantity: Int,

    @Column(name = "price", nullable = false)
    val price: BigDecimal,

    @Column(name = "created_at", nullable = false, updatable = false)
    val createdAt: LocalDateTime = LocalDateTime.now()
)

fun OrderItemRequest.toEntity(order :Order, product: Product): OrderItem {
    return OrderItem(
        id = UUID.randomUUID(),
        order = order,
        product = product,
        price = product.price,
        quantity = this.quantity,
        createdAt = LocalDateTime.now()
    )
}

fun OrderItem.toResponse(): OrderItemResponse {
    return OrderItemResponse(
        productId = this.product.id,
        quantity = this.quantity,
        price = this.price
    )
}
