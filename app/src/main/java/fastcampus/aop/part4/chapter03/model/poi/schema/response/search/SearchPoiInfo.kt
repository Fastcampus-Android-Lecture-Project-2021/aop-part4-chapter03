package fastcampus.aop.part4.chapter03.model.poi.schema.response.search

data class SearchPoiInfo(
    val totalCount: String,
    val count: String,
    val page: String,
    val pois: Pois
)
