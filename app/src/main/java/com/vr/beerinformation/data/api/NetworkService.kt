package com.vr.beerinformation.data.api

import com.vr.beerinformation.data.model.Beer
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.net.URI.create

interface NetworkService {

    @GET("/v2/beers?page=1&per_page=80")
    suspend fun getAllInfoBeer(): Response<List<Beer>>

    companion object {

        var networkService: NetworkService? = null

        var BASE_URL = "https://api.punkapi.com"

        fun getInstance() : NetworkService {
            if (networkService == null){
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                networkService = retrofit.create(NetworkService::class.java)
            }
            return networkService!!
        }
    }
}