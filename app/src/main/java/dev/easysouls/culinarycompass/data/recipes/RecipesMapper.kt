package dev.easysouls.culinarycompass.data.recipes

import dev.easysouls.culinarycompass.data.recipes.local.RecipeEntity
import dev.easysouls.culinarycompass.domain.recipes.model.Recipe
import dev.easysouls.culinarycompass.domain.recipes.model.RecipeDifficulty
import java.time.LocalDateTime

fun Recipe.toRecipeEntity(): RecipeEntity {
    return RecipeEntity(
        mealId = mealId,
        name = name,
        ingredients = ingredients,
        measures = measures,
        difficulty = difficulty.ordinal,
        cookingTime = cookingTime,
        dateModified = dateModified.toString(),
        imageUrl = imageUrl
    )
}

fun RecipeEntity.toRecipe(): Recipe {
    return Recipe(
        mealId = mealId,
        name = name,
        ingredients = ingredients,
        measures = measures,
        difficulty = RecipeDifficulty.entries[difficulty],
        cookingTime = cookingTime,
        dateModified = LocalDateTime.parse(dateModified),
        imageUrl = imageUrl
    )
}