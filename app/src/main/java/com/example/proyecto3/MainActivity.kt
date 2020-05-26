package com.example.proyecto3

import Clases.*
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyecto3.configuration.API_KEY
import com.example.proyecto3.netwoking.GifApi
import com.example.proyecto3.netwoking.GifServices
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity(),OnItemClickListener{

    private var categoryList = ArrayList<Category>()

    companion object {
        var SUBC = "SUBC"
        var GIF = "GIF"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Toast.makeText(this,"Leer comentarios MainActivityAlFinal", Toast.LENGTH_LONG).show()

        val request = GifServices.buildService(GifApi::class.java)

        AddCategories(request)

        recycler_view_categories.adapter = GifCategoryAdapter(categoryList,this)
        recycler_view_categories.layoutManager = LinearLayoutManager(this)
        recycler_view_categories.adapter?.notifyDataSetChanged()

        imageViewSearch.setOnClickListener {
            Search(request)
        }
        buttonVerFavs.setOnClickListener(){
            verFavoritos()
        }
        imageViewReload.setOnClickListener(){
            reload()
        }
    }

    fun Search(request:GifApi){
        imageViewSearch.setOnClickListener {
            //Busqueda
            if(searchBar.text.toString() != ""){
                val call = request.searchGifs(API_KEY,searchBar.text.toString(),10)
                call.enqueue(object : Callback<SearchGif>{
                    override fun onResponse(call: Call<SearchGif>, response: Response<SearchGif>) {
                        if (response.isSuccessful) {
                            if(response.body() != null){
                                val searchG = ArrayList<ItemGif>()
                                response.body()!!.data.forEach(){
                                    searchG.add(
                                        ItemGif(
                                            it.title as String,
                                            it.images?.getValue("original")?.url as String
                                        )
                                    )
                                }
                                ViewGif(searchG)
                            }
                            else{
                                Toast.makeText(this@MainActivity, "${response.errorBody()}", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                    }

                    override fun onFailure(call: Call<SearchGif>, t: Throwable) {
                        Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
                    }
                })
            }
            //Random
            else{
                val call = request.randGifs(API_KEY)
                call.enqueue(object : Callback<RandomGif> {
                    override fun onResponse(call: Call<RandomGif>, response: Response<RandomGif>) {
                        if (response.isSuccessful) {
                            if(response.body() != null){
                                var gifs = ArrayList<ItemGif>()
                                gifs.add(
                                    ItemGif(
                                        response.body()!!.data.title as String,
                                        response.body()!!.data.images?.getValue("original")?.url as String
                                    )
                                )
                                ViewGif(gifs)
                            }
                            else{
                                Toast.makeText(this@MainActivity, "${response.errorBody()}", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }

                    override fun onFailure(call: Call<RandomGif>, t: Throwable) {
                        Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
                    }
                })
            }
        }
    }

    fun AddCategories(request:GifApi){
        val call = request.categories(API_KEY)
        call.enqueue(object : Callback<SearchCategories> {
            override fun onResponse(call: Call<SearchCategories>, response: Response<SearchCategories>) {
                if (response.isSuccessful) {
                    if(response.body() != null){
                        val listCategories = response.body()!!.data
                        listCategories.forEach{
                            categoryList.add(it)
                        }
                    }
                    else{
                        Toast.makeText(this@MainActivity, "${response.errorBody()}", Toast.LENGTH_SHORT).show()
                    }

                }
            }

            override fun onFailure(call: Call<SearchCategories>, t: Throwable) {
                Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun OnClick(category: Category) {
        val intent = Intent(this, SubCategoryActivity::class.java)
        intent.putExtra(SUBC,category)
        startActivity(intent)
    }

    fun ViewGif(gif: ArrayList<ItemGif>){
        val intent = Intent(this, GifActivity::class.java)
        intent.putParcelableArrayListExtra(GIF,gif)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        recycler_view_categories.adapter?.notifyDataSetChanged()
    }

    fun verFavoritos(){
        val intent = Intent(this, FavoritosActivity::class.java)
        //intent.putParcelableArrayListExtra(GIF,gif)
        startActivity(intent)
    }

    fun reload(){
        val intent = Intent(this, MainActivity::class.java)
        //intent.putParcelableArrayListExtra(GIF,gif)
        startActivity(intent)
    }
}
/*
  LABORATORIO 10

  para que se carguen las categorias hay q hacer alguna accion, ya sea ir a favs y volver o apretar en el campo
  del edit text, no arregle pq pasabe eso, creo que debe ser algo de Thread

  Guardar Favoritos: Logro guardarlos en el database con id=titulo del gif y su url
Eliminar Favoritos: Uso delete pero ese recyclerview no muestra nada, creo que es por el Thread tambien
Persistencia de la aplicación: se mantienen los gifs favoritos


el gifadapter2 lo deje en networking sin querer me acabo de percatar, 
 */
