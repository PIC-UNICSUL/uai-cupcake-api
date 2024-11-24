package com.uai.cupcake.service.impl

import com.uai.cupcake.domain.AvailabilityStatus
import com.uai.cupcake.domain.toEntity
import com.uai.cupcake.domain.toResponse
import com.uai.cupcake.exception.BusinessException
import com.uai.cupcake.repository.ProductRepository
import com.uai.cupcake.request.ProductRequest
import com.uai.cupcake.request.UpdateAvailabilityStatusRequest
import com.uai.cupcake.response.ProductResponse
import com.uai.cupcake.service.ProductAdministratorService
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ProductAdministratorServiceImpl (
    private val productRepository: ProductRepository
) : ProductAdministratorService {
    override fun create(request: ProductRequest): ProductResponse {
        val product = request.toEntity()
        val savedProduct = productRepository.save(product)
        return savedProduct.toResponse()
    }

    override fun updateAvailabilityStatus(request: UpdateAvailabilityStatusRequest): ProductResponse {
        val product = productRepository.findById(request.id)
            .orElseThrow { BusinessException("Product not found with ID: ${request.id}", "BAD_REQUEST") }
        product.availabilityStatus = AvailabilityStatus.valueOf(request.availabilityStatus)
        product.updatedAt = LocalDateTime.now()
        productRepository.save(product)
        return product.toResponse()
    }
}