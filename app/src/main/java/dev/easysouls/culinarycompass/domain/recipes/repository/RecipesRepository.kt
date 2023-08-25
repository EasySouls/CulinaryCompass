package dev.easysouls.culinarycompass.domain.recipes.repository

import dev.easysouls.culinarycompass.domain.recipes.model.Recipe
import dev.easysouls.culinarycompass.domain.recipes.model.RecipeDifficulty
import kotlinx.coroutines.flow.Flow

interface RecipesRepository {

    suspend fun insertRecipe(recipe: Recipe)
    suspend fun deleteRecipe(recipe: Recipe)
    suspend fun deleteRecipes(vararg recipes: Recipe)
    fun getAllRecipes(): Flow<List<Recipe>>
    fun getRecipesWithDifficulty(difficulty: RecipeDifficulty): Flow<List<Recipe>>
}