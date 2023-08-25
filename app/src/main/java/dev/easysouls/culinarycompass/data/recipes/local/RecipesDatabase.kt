package dev.easysouls.culinarycompass.data.recipes.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    version = 1,
    entities = [RecipeEntity::class]
)
@TypeConverters(Converters::class)
abstract class RecipesDatabase: RoomDatabase() {

    abstract val dao: RecipesDao
}