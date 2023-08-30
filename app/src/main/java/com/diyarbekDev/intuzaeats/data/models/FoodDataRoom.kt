package com.diyarbekDev.intuzaeats.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Food")
data class FoodDataRoom(
    @PrimaryKey val id: Int,
    val name: String,
    val description: String,
    val price: Int,
    val image: String,
    val category: Int
)