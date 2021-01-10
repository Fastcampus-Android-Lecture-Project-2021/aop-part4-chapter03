package com.fastcampus.aop_part4_chatper03.model.poi.schema.entity

data class SearchResultEntity(
    val fullAdress: String,
    val buildingName: String,
    val locationLatLng: LocationLatLngEntity
)
