package com.example.proyecto3

import Clases.SubCategory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.category_cell.view.*

class GifSubCategoryAdapter (private var subcategoryList: List<SubCategory>, val itemOnClickListener: SubOnItemClickListener):
    RecyclerView.Adapter<GifSubCategoryAdapter.GifSubCategoryAdapterViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int
    ): GifSubCategoryAdapter.GifSubCategoryAdapterViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.category_cell,parent,false)
        return GifSubCategoryAdapterViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: GifSubCategoryAdapter.GifSubCategoryAdapterViewHolder, position: Int
    ) {
        val currentItem = subcategoryList[position]
        holder.bindContact(currentItem,itemOnClickListener)
    }

    override fun getItemCount() = subcategoryList.size


    class GifSubCategoryAdapterViewHolder(v: View): RecyclerView.ViewHolder(v){
        private var view: View = v
        private var subcategory: SubCategory? = null


        fun bindContact(subcategory : SubCategory, clickListener: SubOnItemClickListener){
            this.subcategory = subcategory
            view.textViewCategory.text = subcategory.name

            view.setOnClickListener(){
                clickListener.SubOnClick(subcategory)
            }
        }
    }
}

interface SubOnItemClickListener {
    fun SubOnClick(subcategory: SubCategory)
}