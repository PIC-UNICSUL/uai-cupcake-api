package com.uai.cupcake.controller

import com.uai.cupcake.request.ProductRequest
import com.uai.cupcake.request.ProductUpdateRequest
import com.uai.cupcake.request.UpdateAvailabilityStatusRequest
import com.uai.cupcake.response.ProductResponse
import com.uai.cupcake.service.ProductAdministratorService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/admin/product")
@PreAuthorize("hasAnyAuthority('SCOPE_ADMIN')")
class ProductAdministratorController (
    private val productAdministratorService: ProductAdministratorService
) {
    @PostMapping
    fun create(@Valid @RequestBody request: ProductRequest): ResponseEntity<ProductResponse> {
        return ResponseEntity.status(HttpStatus.CREATED).body(productAdministratorService.create(request))
    }

    @PutMapping("/availability")
    fun updateAvailabilityStatus(@Valid @RequestBody request: UpdateAvailabilityStatusRequest): ResponseEntity<ProductResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(productAdministratorService.updateAvailabilityStatus(request))
    }

    @PutMapping
    fun update(@Valid @RequestBody request: ProductUpdateRequest): ResponseEntity<ProductResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(productAdministratorService.update(request))
    }
}