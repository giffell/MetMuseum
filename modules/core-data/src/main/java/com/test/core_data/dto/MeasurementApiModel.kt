package com.test.core_data.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MeasurementApiModel(
    val elementMeasurements: ElementMeasurementsApiModel,
    val elementName: String
)