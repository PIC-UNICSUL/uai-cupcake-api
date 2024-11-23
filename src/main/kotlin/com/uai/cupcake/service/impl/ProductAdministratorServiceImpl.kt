package com.uai.cupcake.service.impl

import com.uai.cupcake.domain.toEntity
import com.uai.cupcake.domain.toResponse
import com.uai.cupcake.repository.ProductRepository
import com.uai.cupcake.request.ProductRequest
import com.uai.cupcake.response.ProductResponse
import com.uai.cupcake.service.ProductAdministratorService
import org.springframework.stereotype.Service

@Service
class ProductAdministratorServiceImpl (
    private val productRepository: ProductRepository
) : ProductAdministratorService {
    override fun create(request: ProductRequest): ProductResponse {
        val product = request.toEntity()
        val savedProduct = productRepository.save(product)
        return savedProduct.toResponse()
    }
}