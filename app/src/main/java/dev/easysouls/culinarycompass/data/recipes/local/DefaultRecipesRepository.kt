package dev.easysouls.culinarycompass.data.recipes.local

import dev.easysouls.culinarycompass.data.recipes.toRecipe
import dev.easysouls.culinarycompass.data.recipes.toRecipeEntity
import dev.easysouls.culinarycompass.domain.recipes.model.Recipe
import dev.easysouls.culinarycompass.domain.recipes.model.RecipeDifficulty
import dev.easysouls.culinarycompass.domain.recipes.repository.RecipesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DefaultRecipesRepository(
    private val dao: RecipesDao
) : RecipesRepository {
    override suspend fun insertRecipe(recipe: Recipe) {
        dao.insertRecipe(recipe.toRecipeEntity())
    }

    override suspend fun deleteRecipe(recipe: Recipe) {
        dao.deleteRecipe(recipe.toRecipeEntity())
    }

    override suspend fun deleteRecipes(vararg recipes: Recipe) {
        dao.deleteRecipes(*recipes.map {
            it.toRecipeEntity()
        }.toTypedArray())
    }

    override fun getAllRecipes(): Flow<List<Recipe>> {
        return dao.getAllRecipes().map { list ->
            list.map { it.toRecipe() }
        }
    }

    override fun getRecipesWithDifficulty(difficulty: RecipeDifficulty): Flow<List<Recipe>> {
        return dao.getRecipesWithDifficulty(difficulty).map { list ->
            list.map { it.toRecipe() }
        }
    }

}