package com.example.proyecto3

import Clases.Gif
import Clases.ItemGif
import android.os.AsyncTask
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.abs.clase11.utils.loadGif
import com.example.proyecto3.model.Database
import com.example.proyecto3.model.GifDao
import kotlinx.android.synthetic.main.gif_cell.view.*

class GifAdapter (private var gifList: ArrayList<ItemGif>): RecyclerView.Adapter<GifAdapter.GifAdapterViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int
    ): GifAdapter.GifAdapterViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.gif_cell,parent,false)
        return GifAdapterViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: GifAdapterViewHolder, position: Int
    ) {
        val currentItem = gifList[position]
        holder.bindContact(currentItem)
    }

    override fun getItemCount() = gifList.size


    class GifAdapterViewHolder(v: View): RecyclerView.ViewHolder(v){
        private var view: View = v
        private var gif: ItemGif? = null
        val imageView = view.findViewById<ImageView>(R.id.imageViewGif)

        fun bindContact(gif: ItemGif){

            this.gif = gif
            view.textViewTitleGif.text = gif.title
            println(gif)
            imageView.loadGif(gif.url)

            view.imageViewStar.setOnClickListener(){
                //Toast.makeText(this, "message", Toast.LENGTH_LONG).show()

                view.imageViewStar.setImageResource(R.drawable.ic_star_yellow_24dp)
                Toast.makeText(view.context,"Guardado en lista favoritos", Toast.LENGTH_LONG).show()
                var database: GifDao
                database = Room.databaseBuilder(view.context,Database::class.java,"gif").build().gifDao()
                var gifGIF = com.example.proyecto3.model.Gif(gif.title,gif.url)
                AsyncTask.execute{
                    database.insert(gifGIF)
                }
                view.imageViewStar.isClickable=false


            }
        }


    }
}
