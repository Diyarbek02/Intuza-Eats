package com.diyarbekDev.intuzaeats.data.local

import android.content.Context
import android.content.SharedPreferences
import com.diyarbekDev.intuzaeats.app.App
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalStorage @Inject constructor(
    @ApplicationContext private val context: Context
){
    companion object {
        val pref : SharedPreferences =
            App.instance.getSharedPreferences("localStorage", Context.MODE_PRIVATE)
    }



}