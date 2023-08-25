package dev.easysouls.culinarycompass.data.recipes.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.type.DateTime
import java.text.DateFormat
import java.util.Date

@Entity()
data class RecipeEntity(
    @PrimaryKey(autoGenerate = true)
    val mealId: Int,
    @ColumnInfo(name = "name", defaultValue = "Best food ever")
    val name: String,
    @ColumnInfo(name = "ingredients")
    val ingredients: List<String>,
    @ColumnInfo(name = "measures")
    val measures: List<String>,
    @ColumnInfo(name = "difficulty", defaultValue = "4")
    val difficulty: Int,
    @ColumnInfo(name = "cooking_time", defaultValue = "420 minutes")
    val cookingTime: String,
    @ColumnInfo(name = "date_modified", defaultValue = "NULL")
    val dateModified: DateTime?,
    @ColumnInfo(name = "image_url", defaultValue = "NULL")
    val imageUrl: String?
)
