package com.uai.cupcake.request

import jakarta.validation.constraints.NotBlank
import java.util.*

data class UpdateAvailabilityStatusRequest(
    val id: UUID,
    @field:NotBlank(message = "AvailabilityStatus must not be blank")
    val availabilityStatus: String
)
