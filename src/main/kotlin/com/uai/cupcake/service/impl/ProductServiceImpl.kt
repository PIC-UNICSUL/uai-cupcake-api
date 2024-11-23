package com.uai.cupcake.service.impl

import com.uai.cupcake.domain.toResponse
import com.uai.cupcake.repository.ProductRepository
import com.uai.cupcake.response.ProductResponse
import com.uai.cupcake.service.ProductService
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service


@Service
class ProductServiceImpl(
    private val productRepository: ProductRepository
) : ProductService{

    override fun findProducts(categories: List<String>?, order: String?, limit: Int?): List<ProductResponse> {

        val sortOrder = when (order?.lowercase()) {
            "desc" -> Sort.by(Sort.Direction.DESC, "name")
            "asc" -> Sort.by(Sort.Direction.ASC, "name")
            else -> Sort.by(Sort.Direction.ASC, "name")
        }
        val pageLimit = limit ?: 50
        val pageable: Pageable = PageRequest.of(0, pageLimit, sortOrder)

        val products = if (categories.isNullOrEmpty()) {
            productRepository.findAll(pageable).content
        } else {
            productRepository.findByCategoryIn(categories, pageable)
        }

        return products.map { it.toResponse() }
    }

}