package dev.easysouls.culinarycompass.domain.freemealdb.model

import coil.request.Tags

data class FreeMeal(
    val dateModified: String?,
    val idMeal: String?,
    val area: String?,
    val category: String?,
    val creativeCommonsConfirmed: String?,
    val drinkAlternate: String?,
    val imageSource: String?,
    val ingredients: List<String>,
    val meal: String,
    val mealThumb: String,
    val measures: List<String>,
    val source: String,
    val tags: String
)
