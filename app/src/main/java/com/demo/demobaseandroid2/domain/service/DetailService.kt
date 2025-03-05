package com.demo.demobaseandroid2.domain.service

import com.demo.demobaseandroid2.domain.helper.RetrofitInstance
import com.demo.demobaseandroid2.domain.model.AuctionData
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("auctions_data")
    suspend fun <T> getData(): Response<T>
}

class DetailService {

    suspend fun fetchAuctionData(): List<AuctionData>? {

        val inst = RetrofitInstance.retrofit.create(ApiService::class.java)
        val response = inst.getData<List<AuctionData>>()
        if (response.isSuccessful) {
            return response.body()
        } else {
            return null
        }
    }

}