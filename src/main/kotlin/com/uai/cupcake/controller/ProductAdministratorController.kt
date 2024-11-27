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
class ProductAdministratorController (
    private val productAdministratorService: ProductAdministratorService
) {
    @PostMapping
    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN')")
    fun create(@Valid @RequestBody request: ProductRequest): ResponseEntity<ProductResponse> {
        return ResponseEntity.status(HttpStatus.CREATED).body(productAdministratorService.create(request))
    }

    @PutMapping("/availability")
    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN')")
    fun updateAvailabilityStatus(@Valid @RequestBody request: UpdateAvailabilityStatusRequest): ResponseEntity<ProductResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(productAdministratorService.updateAvailabilityStatus(request))
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN')")
    fun update(@Valid @RequestBody request: ProductUpdateRequest): ResponseEntity<ProductResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(productAdministratorService.update(request))
    }
}