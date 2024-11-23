package com.uai.cupcake.service

import com.uai.cupcake.response.ProductResponse

interface ProductService {

    fun findProducts(categories: List<String>?) : List<ProductResponse>

}