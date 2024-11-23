package com.uai.cupcake.domain

import com.uai.cupcake.request.ProductRequest
import com.uai.cupcake.response.ProductResponse
import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "cs_product")
class Product(
    @Id
    @Column(columnDefinition = "UUID", nullable = false)
    var id: UUID = UUID.randomUUID(),

    @Column(nullable = false)
    var name: String,

    var description: String?,

    var category: String,

    @Column(nullable = false)
    var price: BigDecimal,

    @Column(name = "availability_status", nullable = false)
    @Enumerated(EnumType.STRING)
    var availabilityStatus: AvailabilityStatus,

    var photos: String?,

    @Column(name = "created_at", nullable = false, updatable = false)
    var createdAt: LocalDateTime,

    @Column(name = "updated_at", nullable = false)
    var updatedAt: LocalDateTime
)

fun Product.toResponse(): ProductResponse {
    return ProductResponse(
        id = this.id,
        name = this.name,
        description = this.description,
        category = this.category,
        price = this.price,
        photos = this.photos,
        availabilityStatus = availabilityStatus.name
    )
}

fun ProductRequest.toEntity(): Product {
    return Product(
        id = UUID.randomUUID(),
        name = this.name,
        description = this.description,
        category = this.category,
        price = this.price,
        photos = this.photos,
        availabilityStatus = AvailabilityStatus.AVAILABLE,
        createdAt = LocalDateTime.now(),
        updatedAt = LocalDateTime.now()
    )
}

enum class AvailabilityStatus {
    AVAILABLE,
    UNAVAILABLE
}
