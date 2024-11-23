package com.uai.cupcake.domain

import com.uai.cupcake.request.UserRequest
import com.uai.cupcake.response.UserResponse
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "cs_user")
data class User(
    @Id
    @Column(name = "id", columnDefinition = "UUID")
    val id: UUID,

    @Column(name = "full_name", nullable = false)
    var fullName: String,

    @Column(name = "phone_number", nullable = false)
    var phoneNumber: String,

    @Column(name = "mail", nullable = false)
    val mail: String,

    @Column(name = "password", nullable = false)
    val password: String,

    @Column(name = "role", nullable = false)
    val role: String,

    @Column(name = "created_at", nullable = false, updatable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "updated_at", nullable = false)
    var updatedAt: LocalDateTime = LocalDateTime.now()
)

fun User.toResponse(): UserResponse {
    return UserResponse(
        id = this.id,
        name = this.fullName,
        mail = this.mail,
        phone = this.phoneNumber,
        role = this.role
    )
}

fun UserRequest.toEntity(): User {
    val passwordEncoder = BCryptPasswordEncoder()
    val encryptedPassword = passwordEncoder.encode(this.password)

    return User(
        id = UUID.randomUUID(),
        fullName = this.name,
        phoneNumber = this.phone,
        mail = this.mail,
        password = encryptedPassword,
        role = "CUSTOMER",
        createdAt = LocalDateTime.now(),
        updatedAt = LocalDateTime.now()
    )
}
