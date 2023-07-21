package com.diyarbekDev.intuzaeats.presenter

import com.diyarbekDev.intuzaeats.data.models.FoodData
import com.diyarbekDev.intuzaeats.data.models.MenuData
import kotlinx.coroutines.flow.Flow

interface HomeFragmentViewModel {
    val getFoodSuccessFlow: Flow<List<FoodData>>
    suspend fun getFood()

    val getMenuSuccessFlow: Flow<List<MenuData>>
    suspend fun getMenu()

    val filterFoodByCategory: Flow<List<FoodData>>
    fun getFilteredFood(categoryId: Int)
}