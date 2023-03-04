package com.test.core_network.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MeasurementApiModel(
    val elementMeasurements: ElementMeasurementsApiModel,
    val elementName: String
)