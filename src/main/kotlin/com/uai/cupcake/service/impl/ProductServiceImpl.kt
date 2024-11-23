package com.uai.cupcake.service.impl

import com.uai.cupcake.domain.toResponse
import com.uai.cupcake.repository.ProductRepository
import com.uai.cupcake.response.ProductResponse
import com.uai.cupcake.service.ProductService
import org.springframework.stereotype.Service


@Service
class ProductServiceImpl(
    private val productRepository: ProductRepository
) : ProductService{

    override fun findProducts(categories: List<String>?): List<ProductResponse> {
        val products = if (categories.isNullOrEmpty()) {
            productRepository.findByAvailabilityStatus()
        } else {
            productRepository.findByCategoryInAndAvailabilityStatus(categories)
        }

        return products.map { it.toResponse() }
    }

}