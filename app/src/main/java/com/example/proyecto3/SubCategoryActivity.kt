package com.example.proyecto3

import Clases.Category
import Clases.ItemGif
import Clases.SearchGif
import Clases.SubCategory
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyecto3.MainActivity.Companion.SUBC
import com.example.proyecto3.configuration.API_KEY
import com.example.proyecto3.netwoking.GifApi
import com.example.proyecto3.netwoking.GifServices
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_sub_category.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SubCategoryActivity : AppCompatActivity(), SubOnItemClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub_category)
        var category: Category = intent.getParcelableExtra(SUBC)
        recycler_view_sub_categories.adapter = GifSubCategoryAdapter(category.subcategories,this)
        recycler_view_sub_categories.layoutManager = LinearLayoutManager(this)
    }

    override fun SubOnClick(subcategory: SubCategory) {
        val request = GifServices.buildService(GifApi::class.java)
        Search(request,subcategory.name)
    }

    fun Search(request:GifApi,subcategory: String){
        val call = request.searchGifs(API_KEY,subcategory,10)
        call.enqueue(object : Callback<SearchGif> {
            override fun onResponse(call: Call<SearchGif>, response: Response<SearchGif>) {
                if (response.isSuccessful) {
                    val searchG = ArrayList<ItemGif>()
                    response.body()!!.data.forEach() {
                        searchG.add(
                            ItemGif(
                                it.title as String,
                                it.images?.getValue("original")?.url as String
                            )
                        )
                    }
                    ViewGif(searchG)
                }
            }

            override fun onFailure(call: Call<SearchGif>, t: Throwable) {
                //Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun ViewGif(gif: ArrayList<ItemGif>){
        val intent = Intent(this, GifActivity::class.java)
        intent.putParcelableArrayListExtra(MainActivity.GIF,gif)
        startActivity(intent)
    }
}
