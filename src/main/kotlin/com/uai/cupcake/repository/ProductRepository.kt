package com.uai.cupcake.repository

import com.uai.cupcake.domain.AvailabilityStatus
import com.uai.cupcake.domain.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ProductRepository : JpaRepository<Product, UUID>{
    fun findByAvailabilityStatus(availabilityStatus: AvailabilityStatus = AvailabilityStatus.AVAILABLE): List<Product>

    fun findByCategoryInAndAvailabilityStatus(categories: List<String>, availabilityStatus: AvailabilityStatus = AvailabilityStatus.AVAILABLE): List<Product>
}
