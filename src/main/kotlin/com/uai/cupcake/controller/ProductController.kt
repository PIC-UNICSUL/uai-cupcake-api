package com.uai.cupcake.controller

import com.uai.cupcake.service.ProductService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/product")
class ProductController(
    private val productService: ProductService
) {

    @GetMapping
    fun get(@RequestParam("category") categories: List<String>?, @RequestParam("order") order: String?,
            @RequestParam("limit") limit: Int?): ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.OK).body(productService.findProducts(categories, order, limit))
    }
}