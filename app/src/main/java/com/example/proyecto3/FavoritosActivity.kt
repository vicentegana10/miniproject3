package com.example.proyecto3

import Clases.ItemGif
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Database
import androidx.room.Room
import com.example.proyecto3.MainActivity.Companion.GIF
import com.example.proyecto3.model.Gif
import com.example.proyecto3.model.GifDao
import com.example.proyecto3.netwoking.GifAdapter2
import kotlinx.android.synthetic.main.activity_favoritos.*

class FavoritosActivity : AppCompatActivity() {

    lateinit var database: GifDao

    var listGifFavs: ArrayList<ItemGif> = ArrayList()
    var listGifFavs2: ArrayList<Gif> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favoritos)
        database = Room.databaseBuilder(applicationContext, com.example.proyecto3.model.Database::class.java,"gif").build().gifDao()
        var listGifFavs: ArrayList<ItemGif> = ArrayList() // cargar de Room
        AsyncTask.execute() {
            //adapter.setData(database.getAllGifs())
            var listGifFavs2  = database.getAllGifs()
            var sa=2
        }

        recycler_view_gifs_fav.adapter = GifAdapter2(listGifFavs2)
        recycler_view_gifs_fav.layoutManager = LinearLayoutManager(this)

        //si los carga pero nose porque no los muestra, me imagino que es por el AsyncTask
    }


}
