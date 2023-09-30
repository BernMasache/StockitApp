package com.example.stockitapp.network

import android.util.Log
import com.example.stockitapp.constants.ApiConstants
import com.example.stockitapp.model.CollectionModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiService {
//    @Headers("Content-Type: application/json","x-apikey: 63722be4c890f30a8fd1f370","cache-control: no-cache")
    @GET(ApiConstants.COLLECTIONS_END_POINT)
    suspend fun getCollections():List<CollectionModel>
    companion object{
        var apiService:ApiService? = null
        fun getInstance(): ApiService{
            if (apiService==null){
                apiService= Retrofit.Builder()
                    .baseUrl(ApiConstants.BASIC_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create()
            }

            return apiService!!
        }
    }
}