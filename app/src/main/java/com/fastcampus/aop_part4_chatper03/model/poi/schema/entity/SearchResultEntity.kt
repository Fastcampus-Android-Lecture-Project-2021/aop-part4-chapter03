package com.fastcampus.aop_part4_chatper03.model.poi.schema.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SearchResultEntity(
    val fullAdress: String,
    val buildingName: String,
    val locationLatLng: LocationLatLngEntity
): Parcelable
