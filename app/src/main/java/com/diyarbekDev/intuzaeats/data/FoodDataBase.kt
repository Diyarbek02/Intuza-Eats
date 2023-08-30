package com.diyarbekDev.intuzaeats.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.diyarbekDev.intuzaeats.data.local.Dao
import com.diyarbekDev.intuzaeats.data.models.FoodData
import com.diyarbekDev.intuzaeats.data.models.FoodDataRoom

@Database(entities = [FoodDataRoom::class], version = 1)
abstract class FoodDataBase : RoomDatabase() {

    companion object {
        private var INSTANCE: FoodDataBase? = null
        private val LOCK = Any()

        fun getInstance(context: Context): FoodDataBase {
            INSTANCE?.let {
                return it
            }
            synchronized(LOCK) {
                INSTANCE?.let {
                    return it
                }
                val db = Room.databaseBuilder(
                    context,
                    FoodDataBase::class.java,
                    "db.db"
                )
                    .createFromAsset("db.db")
                    .build()
                INSTANCE = db
                return db
            }
        }
    }
    abstract fun fooddao() : Dao
}