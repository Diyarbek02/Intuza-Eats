package com.diyarbekDev.intuzaeats.domain.repository

import com.diyarbekDev.intuzaeats.data.models.FoodData
import com.diyarbekDev.intuzaeats.data.models.MenuData
import com.diyarbekDev.intuzaeats.data.models.ResultData
import java.util.concurrent.Flow

interface MainRepository {
    fun getMenu(): kotlinx.coroutines.flow.Flow<ResultData<List<MenuData>>>

    fun getFood(): kotlinx.coroutines.flow.Flow<ResultData<List<FoodData>>>
}