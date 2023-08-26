package dev.easysouls.culinarycompass.domain.recipes.model

import com.google.type.DateTime
import java.time.LocalDateTime

data class Recipe(
    val mealId: Int,
    val name: String,
    val ingredients: List<String>,
    val measures: List<String>,
    val difficulty: RecipeDifficulty,
    val cookingTime: String,
    val dateModified: LocalDateTime,
    val imageUrl: String?
)
