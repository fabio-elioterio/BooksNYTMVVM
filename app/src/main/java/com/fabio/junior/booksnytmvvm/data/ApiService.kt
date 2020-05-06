package com.fabio.junior.booksnytmvvm.data

import com.fabio.junior.booksnytmvvm.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {

    private fun initRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val service: Services = initRetrofit().create(Services::class.java)
}