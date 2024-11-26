package com.uai.cupcake.service.impl

import com.uai.cupcake.domain.AvailabilityStatus
import com.uai.cupcake.domain.toEntity
import com.uai.cupcake.domain.toResponse
import com.uai.cupcake.exception.BadRequestException
import com.uai.cupcake.exception.ErrorCodeEnum
import com.uai.cupcake.repository.ProductRepository
import com.uai.cupcake.request.ProductRequest
import com.uai.cupcake.request.ProductUpdateRequest
import com.uai.cupcake.request.UpdateAvailabilityStatusRequest
import com.uai.cupcake.response.ErrorResponse
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

    override fun update(request: ProductUpdateRequest): ProductResponse {
        val product = productRepository.findById(request.id)
            .orElseThrow { throw BadRequestException(
                ErrorResponse(
                    ErrorCodeEnum.PRODUCT_NOT_FOUND,
                    "Produto ${request.id} não encontrado.",
                    null
                )
            ) }
        product.name = request.name
        product.price = request.price
        product.category = request.category
        product.description = request.description
        product.photos = request.photos
        product.updatedAt = LocalDateTime.now()
        productRepository.saveAndFlush(product)
        return product.toResponse()
    }

    override fun updateAvailabilityStatus(request: UpdateAvailabilityStatusRequest): ProductResponse {
        val product = productRepository.findById(request.id)
            .orElseThrow { throw BadRequestException(
                ErrorResponse(
                    ErrorCodeEnum.PRODUCT_NOT_FOUND,
                    "Produto ${request.id} não encontrado.",
                    null
                )
            ) }
        product.availabilityStatus = AvailabilityStatus.valueOf(request.availabilityStatus)
        product.updatedAt = LocalDateTime.now()
        productRepository.saveAndFlush(product)
        return product.toResponse()
    }
}