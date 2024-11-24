package com.uai.cupcake.request

import java.util.*

data class UpdateAvailabilityStatusRequest(
    val id: UUID,
    val availabilityStatus: String
)
