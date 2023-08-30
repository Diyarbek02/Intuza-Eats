package com.diyarbekDev.intuzaeats.data.remote

import com.diyarbekDev.intuzaeats.data.models.FoodData
import com.diyarbekDev.intuzaeats.data.models.MenuData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface EatsApi {
    @Headers("Content-Type:application/json")
    @GET("/api/v1/menu/")
    suspend fun getMenu(@Header("Authorization") token: String): Response<List<MenuData>>

    @Headers("Content-Type:application/json")
    @GET("/api/v1/foods/")
    suspend fun getFood(@Header("Authorization") token: String): Response<List<FoodData>>
}