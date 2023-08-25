package dev.easysouls.culinarycompass.data.recipes.local

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.google.type.DateTime
import dev.easysouls.culinarycompass.domain.recipes.model.RecipeDifficulty
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.util.Date
import kotlin.jvm.Throws

@RunWith(AndroidJUnit4::class)
class RecipesDatabaseTest {
    private lateinit var dao: RecipesDao
    private lateinit var db: RecipesDatabase

    @Before
    fun setup() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            RecipesDatabase::class.java,
        ).allowMainThreadQueries().build()

        dao = db.dao
    }

    @After
    @Throws(IOException::class)
    fun teardown() {
        db.close()
    }

    @Test
    fun insertRecipe() = runTest {
        val recipe = RecipeEntity(
            mealId = 1,
            name = "Cheesecake",
            ingredients = listOf(""),
            measures = listOf(""),
            difficulty = RecipeDifficulty.Easy.ordinal,
            cookingTime = "60",
            dateModified = DateTime().year,
            imageUrl = null
        )
        dao.insertRecipe(recipe)
        val insertedRecipe = dao.getAllRecipes().first().first()
        assertThat(insertedRecipe).isEqualTo(recipe)
    }

    @Test
    fun deleteRecipes() = runTest {
        val recipe0 = RecipeEntity(
            mealId = 1,
            name = "Cheesecake",
            ingredients = listOf(""),
            measures = listOf(""),
            difficulty = RecipeDifficulty.Easy.ordinal,
            cookingTime = "60",
            dateModified = Date(),
            imageUrl = null
        )
        val recipe1 = RecipeEntity(
            mealId = 2,
            name = "Not cheesecake",
            ingredients = listOf(""),
            measures = listOf(""),
            difficulty = RecipeDifficulty.Medium.ordinal,
            cookingTime = "70",
            dateModified = Date(),
            imageUrl = null
        )

        dao.insertRecipe(recipe0)
        dao.insertRecipe(recipe1)

        dao.deleteRecipe(recipe0)
        val recipes = dao.getAllRecipes().first()
        assertThat(recipes.size).isEqualTo(1)
        assertThat(recipes.first().cookingTime).isEqualTo(recipe1.cookingTime)

        dao.deleteRecipe(recipe1)
        val recipesAfterDelete = dao.getAllRecipes().first()
        assertThat(recipesAfterDelete).isEmpty()
    }
}