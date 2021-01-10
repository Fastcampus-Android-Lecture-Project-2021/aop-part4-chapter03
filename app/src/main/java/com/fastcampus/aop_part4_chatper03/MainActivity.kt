package com.fastcampus.aop_part4_chatper03

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.fastcampus.aop_part4_chatper03.model.poi.schema.Poi
import com.fastcampus.aop_part4_chatper03.model.poi.schema.Pois
import com.fastcampus.aop_part4_chatper03.model.poi.schema.entity.LocationLatLngEntity
import com.fastcampus.aop_part4_chatper03.model.poi.schema.entity.SearchResultEntity
import com.fastcampus.aop_part4_chatper03.utillity.RetrofitUtil
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import java.lang.Exception
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {

    private lateinit var job: Job

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private val adapter by lazy { SearchRecyclerAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        job = Job()
        initViews()
        initData()
    }

    private fun initViews() {
        emptyResultTextView.isVisible = false
        recyclerView.adapter = adapter
        searchButton.setOnClickListener {
            searchKeyword(searchBarInputView.text.toString())
        }
    }

    private fun initData() {
        adapter.notifyDataSetChanged()
    }

    private fun setData(pois: Pois) {
        val data = pois.poi.map {
            SearchResultEntity(
                fullAdress = makeMainAdress(it),
                buildingName = it.name ?: "",
                locationLatLng = LocationLatLngEntity(it.noorLat, it.noorLon)
            )
        }
        adapter.setSearchResultList(data)
    }

    private fun searchKeyword(keywordString: String) {
        launch(coroutineContext) {
            try {
                withContext(Dispatchers.IO) {
                    val response = RetrofitUtil.apiService.getSearchLocation(
                        keyword = keywordString
                    )
                    if (response.isSuccessful) {
                        val body = response.body()
                        withContext(Dispatchers.Main) {
                            Log.e("list", body.toString())
                            body?.let { searchResponseSchema ->
                                setData(searchResponseSchema.searchPoiInfo.pois)
                            }
                        }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this@MainActivity, "검색하는 과정에서 에러가 발생했습니다. : ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun makeMainAdress(poi: Poi): String =
        if (poi.secondNo?.trim().isNullOrEmpty()) {
            (poi.upperAddrName?.trim() ?: "") + " " +
                (poi.middleAddrName?.trim() ?: "") + " " +
                (poi.lowerAddrName?.trim() ?: "") + " " +
                (poi.detailAddrName?.trim() ?: "") + " " +
                poi.firstNo?.trim()
        } else {
            (poi.upperAddrName?.trim() ?: "") + " " +
                (poi.middleAddrName?.trim() ?: "") + " " +
                (poi.lowerAddrName?.trim() ?: "") + " " +
                (poi.detailAddrName?.trim() ?: "") + " " +
                (poi.firstNo?.trim() ?: "") + " " +
                poi.secondNo?.trim()
        }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}
