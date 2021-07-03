package dev.dschmidt.foodkmm.presentation.recipe_list

class FoodCategoryUtil {
    fun getAllFoodCategories(): List<FoodCategory> {
        return FoodCategory.values().asList()
    }

    fun getFoodCategory(value: String): FoodCategory? {
        val map = FoodCategory.values().associateBy(FoodCategory::value)
        return map[value]
    }
}