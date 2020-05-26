package com.example.proyecto3

import Clases.Category
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.category_cell.view.*

class GifCategoryAdapter (private var categoryList: ArrayList<Category>,val itemOnClickListener: OnItemClickListener):RecyclerView.Adapter<GifCategoryAdapter.GifCategoryAdapterViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int
    ): GifCategoryAdapter.GifCategoryAdapterViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.category_cell,parent,false)
        return GifCategoryAdapterViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: GifCategoryAdapterViewHolder, position: Int
    ) {
        val currentItem = categoryList[position]
        holder.bindContact(currentItem,itemOnClickListener)
    }

    override fun getItemCount() = categoryList.size


    class GifCategoryAdapterViewHolder(v: View): RecyclerView.ViewHolder(v){
        private var view: View = v
        private var category: Category? = null


        fun bindContact(category : Category, clickListener: OnItemClickListener){
            this.category = category
            view.textViewCategory.text = category.name

            view.setOnClickListener(){
                clickListener.OnClick(category)
            }
        }
    }
}

interface OnItemClickListener{
    fun OnClick(category: Category)
}