package com.uai.cupcake.service

import com.uai.cupcake.request.ProductRequest
import com.uai.cupcake.response.ProductResponse

interface ProductAdministratorService {
    fun create(request: ProductRequest) : ProductResponse
}