package com.kadance.taxi.net

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.kadance.taxi.BuildConfig
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

/**
 * Created by Kenza on 12.04.2018.
 */
interface GoogleServicerApi {




    @GET("json")
    fun getLocationByAddress(@Query("address") address: String, @Query("key") key: String = apiKey): Observable<LocationResult>


    companion object Factory {

        val apiKey: String = "AIzaSyCfdPVb-nngx2tNpWgAPGOVECzRgkXIhgM"

        fun create( ): GoogleServicerApi {
            val logging = HttpLoggingInterceptor()

            if(BuildConfig.DEBUG){
                logging.setLevel(HttpLoggingInterceptor.Level.HEADERS)
                val httpClient = OkHttpClient.Builder()
                httpClient.addInterceptor(logging)
            }


            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://maps.googleapis.com/maps/api/geocode/")
                    .build()

            return retrofit.create(GoogleServicerApi::class.java);
        }
    }

}