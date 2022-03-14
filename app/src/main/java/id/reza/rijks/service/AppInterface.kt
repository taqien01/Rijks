package id.reza.rijks.service

import id.reza.rijks.BuildConfig
import id.reza.rijks.model.Data
import retrofit2.http.GET
import retrofit2.http.Query

interface AppInterface {

    @GET("collection?key="+BuildConfig.API_KEY)
    suspend fun getData(): Data

}