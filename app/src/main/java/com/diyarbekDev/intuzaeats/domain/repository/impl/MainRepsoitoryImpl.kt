package com.diyarbekDev.intuzaeats.domain.repository.impl

import com.diyarbekDev.intuzaeats.data.local.LocalStorage
import com.diyarbekDev.intuzaeats.data.models.FoodData
import com.diyarbekDev.intuzaeats.data.models.ResultData
import com.diyarbekDev.intuzaeats.data.remote.EatsApi
import com.diyarbekDev.intuzaeats.domain.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepsoitoryImpl @Inject constructor(
    private val api: EatsApi,
    private val localStorage: LocalStorage
) : MainRepository {

    override fun getMenu() = flow {
        val request = api.getMenu("Token a77c6884ea0f2e1524640a0ca8ff920fa8442657")
        if (request.isSuccessful) {
            emit(ResultData.Success(request.body()!!))
        }else {
            emit(ResultData.Message(request.message()))
        }
    }.catch {
        emit(ResultData.Error(it))
    }.flowOn(Dispatchers.IO)



    override fun getFood() = flow {
        val request = api.getFood("Token a77c6884ea0f2e1524640a0ca8ff920fa8442657")
        if (request.isSuccessful) {
            emit(ResultData.Success(request.body()!!))
        }else {
            emit(ResultData.Message(request.message()))
        }
    }.catch {
        emit(ResultData.Error(it))
    }.flowOn(Dispatchers.IO)
}