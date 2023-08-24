package dev.easysouls.culinarycompass.data.freemealdb.remote

import dev.easysouls.culinarycompass.data.beers.remote.BeerApi.Companion.BASE_URL
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FreeMealApi {

    @GET("1/search.php?f=a")
    suspend fun getMealsBasedOnFirstLetter(@Query("f") key: String = "a"): Response<List<FreeMealDto>>

    companion object {
        const val BASE_URL = "https://www.themealdb.com/api/json/v1/"
    }
}