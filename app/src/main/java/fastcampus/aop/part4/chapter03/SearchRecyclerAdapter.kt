package fastcampus.aop.part4.chapter03

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fastcampus.aop.part4.chapter03.model.poi.schema.entity.SearchResultEntity
import kotlinx.android.synthetic.main.viewholder_search_result_item.view.*

class SearchRecyclerAdapter : RecyclerView.Adapter<SearchRecyclerAdapter.SearchResultItemViewHolder>() {

    private var searchResultList: List<SearchResultEntity> = listOf()
    lateinit var searchResultClickListener: (SearchResultEntity) -> Unit

    class SearchResultItemViewHolder(itemView: View, val searchResultClickListener: (SearchResultEntity) -> Unit) : RecyclerView.ViewHolder(itemView) {

        fun bindData(data: SearchResultEntity) = with(itemView) {
            textTextView.text = "${data.fullAdress}"
            subtextTextView.text = "${data.name}"
            setOnClickListener {
                searchResultClickListener(data)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return SearchResultItemViewHolder(view, searchResultClickListener)
    }

    override fun getItemViewType(position: Int): Int = R.layout.viewholder_search_result_item

    override fun onBindViewHolder(holder: SearchResultItemViewHolder, position: Int) {
        holder.bindData(searchResultList[position])
    }

    override fun getItemCount(): Int = searchResultList.size

    fun setSearchResultList(searchResultList: List<SearchResultEntity>, searchResultClickListener: (SearchResultEntity) -> Unit) {
        this.searchResultList = searchResultList
        this.searchResultClickListener = searchResultClickListener
        notifyDataSetChanged()
    }


}
