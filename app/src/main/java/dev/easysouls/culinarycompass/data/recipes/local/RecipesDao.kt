package dev.easysouls.culinarycompass.data.recipes.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.easysouls.culinarycompass.domain.recipes.model.RecipeDifficulty
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipeEntity: RecipeEntity)
    @Delete
    suspend fun deleteRecipe(recipeEntity: RecipeEntity)
    @Delete
    suspend fun deleteRecipes(vararg recipeEntities: RecipeEntity)
    @Query("SELECT * FROM recipeentity")
    fun getAllRecipes(): Flow<List<RecipeEntity>>

    @Query("SELECT * FROM recipeentity WHERE difficulty = :difficulty")
    fun getRecipesWithDifficulty(difficulty: RecipeDifficulty): Flow<List<RecipeEntity>>
}