package com.example.proyecto3

import Clases.Category
import Clases.Gif
import Clases.ItemGif
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.proyecto3.MainActivity.Companion.GIF
import com.example.proyecto3.model.Database
import com.example.proyecto3.model.GifDao
import kotlinx.android.synthetic.main.activity_gif.*
import kotlinx.android.synthetic.main.activity_sub_category.*

class GifActivity : AppCompatActivity() {
    lateinit var database: GifDao


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gif)

        database = Room.databaseBuilder(this, Database::class.java,"gif").build().gifDao()


        var listGif: ArrayList<ItemGif> = intent.getParcelableArrayListExtra(GIF)
        println(listGif[0].title)
        recycler_view_gifs.adapter = GifAdapter(listGif)
        recycler_view_gifs.layoutManager = LinearLayoutManager(this)

    }
}
