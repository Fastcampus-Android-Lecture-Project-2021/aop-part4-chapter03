package com.fastcampus.aop_part4_chatper03.model.poi.schema.response.search

data class SearchPoiInfo(
    val totalCount: String,
    val count: String,
    val page: String,
    val pois: Pois
)
