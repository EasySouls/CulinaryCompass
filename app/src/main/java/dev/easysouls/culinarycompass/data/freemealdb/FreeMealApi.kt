package dev.easysouls.culinarycompass.data.freemealdb

import dev.easysouls.culinarycompass.domain.freemealdb.model.FreeMeal
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FreeMealApi {

    @GET("/api/json/v1/1/search.php?f=a")
    suspend fun getMealsBasedOnFirstLetter(@Query("f") key: String = "a"): Response<List<FreeMeal>>
}