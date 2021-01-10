package com.fastcampus.aop_part4_chatper03

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import com.fastcampus.aop_part4_chatper03.model.poi.schema.entity.LocationLatLngEntity
import com.fastcampus.aop_part4_chatper03.model.poi.schema.entity.SearchResultEntity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val adapter by lazy { SearchRecyclerAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        initData()
    }

    private fun initViews() {
        emptyResultTextView.isVisible = false
        recyclerView.adapter = adapter
    }

    private fun initData() {
        val mockDataList = (1..10).map {
            SearchResultEntity(
                fullAdress = "${it}번지",
                buildingName = "${it}빌딩",
                locationLatLng = LocationLatLngEntity(0f, it.toFloat())
            )
        }
        adapter.setSearchResultList(mockDataList)
    }
}
