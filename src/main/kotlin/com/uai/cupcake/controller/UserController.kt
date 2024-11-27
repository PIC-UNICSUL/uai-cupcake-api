package com.uai.cupcake.controller

import com.uai.cupcake.request.UserRequest
import com.uai.cupcake.request.UserUpdateRequest
import com.uai.cupcake.response.UserResponse
import com.uai.cupcake.service.UserService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class UserController(
    private val userService: UserService,
) {

    @PostMapping
    fun post(@Valid @RequestBody request: UserRequest) : ResponseEntity<UserResponse> {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(request))
    }

    @PutMapping
    fun update(@Valid @RequestBody request: UserUpdateRequest, token: JwtAuthenticationToken) : ResponseEntity<UserResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(request, token))
    }

    @GetMapping
    fun get(token: JwtAuthenticationToken) : ResponseEntity<UserResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUser(token))

    }

}