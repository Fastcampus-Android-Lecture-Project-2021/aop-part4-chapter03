package com.fastcampus.aop_part4_chatper03

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fastcampus.aop_part4_chatper03.model.poi.schema.entity.SearchResultEntity
import kotlinx.android.synthetic.main.viewholder_search_result_item.view.*

class SearchRecyclerAdapter : RecyclerView.Adapter<SearchRecyclerAdapter.SearchResultItemViewHolder>() {

    private var searchResultList: List<SearchResultEntity> = listOf()

    class SearchResultItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindData(data: SearchResultEntity) = with(itemView) {
            textTextView.text = "${data.fullAdress}"
            subtextTextView.text = "${data.buildingName}"
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return SearchResultItemViewHolder(view)
    }

    override fun getItemViewType(position: Int): Int = R.layout.viewholder_search_result_item

    override fun onBindViewHolder(holder: SearchResultItemViewHolder, position: Int) {
        holder.bindData(searchResultList[position])
    }

    override fun getItemCount(): Int = searchResultList.size

    fun setSearchResultList(searchResultList: List<SearchResultEntity>) {
        this.searchResultList = searchResultList
        notifyDataSetChanged()
    }


}
