package com.example.proyecto3.netwoking

import Clases.*
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.*

interface GifApi {
    @GET("search")
    fun searchGifs(@Query("api_key") api_key: String?, @Query("q") query: String?,@Query("limit") limit: Int?): Call<SearchGif>
    @GET("random")
    fun randGifs(@Query("api_key") api_key: String?): Call<RandomGif>
    @GET("categories")
    fun categories(@Query("api_key") api_key: String?): Call<SearchCategories>
}