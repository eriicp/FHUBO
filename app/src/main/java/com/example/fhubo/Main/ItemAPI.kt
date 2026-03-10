package com.example.fhubo.Main

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ItemAPI {
    companion object {
        private var mItemAPI: filmsService? = null

        @Synchronized
        fun API(): filmsService {
            if (mItemAPI == null) {

                val gsondateformat = GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                    .create()

                mItemAPI = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create(gsondateformat))
                    .baseUrl("https://oracleitic.mooo.com/")
                    .build()
                    .create(filmsService::class.java)
            }
            return mItemAPI!!
        }
    }
}