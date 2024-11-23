package com.uai.cupcake.repository

import com.uai.cupcake.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository  : JpaRepository<User, UUID> {
    fun findFirstByMail(mail: String): User?
}