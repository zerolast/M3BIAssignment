package com.stupid.techie.m3biassignment.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class RetrofitAPIClient {

    companion object {
        private const val CONNECTION_TIMEOUT: Long = 30
        private const val READ_WRITE_TIMEOUT: Long = 60

        private fun getOkHttpClient(): OkHttpClient {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            return OkHttpClient.Builder()
                .readTimeout(READ_WRITE_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(READ_WRITE_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .build()
        }

        private fun getRetroFitClient(): Retrofit {

            val gson = GsonBuilder()
                .setLenient()
                .create()


            return Retrofit.Builder()
                .baseUrl("http://api.icndb.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(getOkHttpClient())
                .build()
        }

        fun getApi(): FetchDataService {
            return getRetroFitClient().create(FetchDataService::class.java)
        }
    }

}