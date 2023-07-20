package com.diyarbekDev.intuzaeats.data.models

data class MenuData(
    val id: Int,
    val name: String,
    val image: String,
    var isSelected: Boolean      // 1st thing that you should add
)
