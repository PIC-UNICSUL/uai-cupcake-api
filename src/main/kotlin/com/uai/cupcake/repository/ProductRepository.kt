package com.uai.cupcake.repository

import com.uai.cupcake.domain.Product
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ProductRepository : JpaRepository<Product, UUID>{
    fun findByCategoryIn(categories: List<String>, pageable: Pageable): List<Product>
}
