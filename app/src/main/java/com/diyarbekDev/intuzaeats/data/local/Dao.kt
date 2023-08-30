package com.diyarbekDev.intuzaeats.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.diyarbekDev.intuzaeats.data.models.FoodData
import com.diyarbekDev.intuzaeats.data.models.FoodDataRoom

@Dao
interface Dao {

    @Query("SELECT * FROM Food")
    suspend fun getFoods(): List<FoodDataRoom>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFood(food: FoodData)
}