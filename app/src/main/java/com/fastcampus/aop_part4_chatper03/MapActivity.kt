package com.fastcampus.aop_part4_chatper03

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fastcampus.aop_part4_chatper03.model.poi.schema.entity.SearchResultEntity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class MapActivity : AppCompatActivity(), OnMapReadyCallback {

    companion object {
        const val SEARCH_RESULT_EXTRA_KEY = "SearchResult"
    }

    private lateinit var map: GoogleMap
    private lateinit var searchResult: SearchResultEntity

    private var currentSelectMarker: Marker? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        if (::searchResult.isInitialized.not()) {
            intent?.let {
                searchResult = intent.getParcelableExtra(SEARCH_RESULT_EXTRA_KEY) ?: throw Exception("데이터가 존재하지 않습니다.")
                setupGoogleMap()
            }
        }
    }

    private fun setupGoogleMap() {
        val mapFragment = supportFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(map: GoogleMap) {
        this.map = map
        currentSelectMarker = setupMarker(searchResult)

        currentSelectMarker?.showInfoWindow()
    }

    private fun setupMarker(searchResult: SearchResultEntity): Marker {
        val positionLatLng = LatLng(
            searchResult.locationLatLng.latitude.toDouble(),
            searchResult.locationLatLng.longitude.toDouble()
        )
        val markerOption = MarkerOptions().apply {
            position(positionLatLng)
            title(searchResult.buildingName)
            snippet(searchResult.fullAdress)
        }
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(positionLatLng, 17f))

        return map.addMarker(markerOption)
    }


}
