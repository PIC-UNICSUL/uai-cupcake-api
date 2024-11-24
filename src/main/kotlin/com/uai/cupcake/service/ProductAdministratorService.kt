package com.uai.cupcake.service

import com.uai.cupcake.request.ProductRequest
import com.uai.cupcake.request.ProductUpdateRequest
import com.uai.cupcake.request.UpdateAvailabilityStatusRequest
import com.uai.cupcake.response.ProductResponse

interface ProductAdministratorService {
    fun create(request: ProductRequest) : ProductResponse

    fun update(request: ProductUpdateRequest) : ProductResponse

    fun updateAvailabilityStatus(request: UpdateAvailabilityStatusRequest) : ProductResponse
}