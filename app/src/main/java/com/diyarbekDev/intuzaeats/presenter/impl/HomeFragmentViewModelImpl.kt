package com.diyarbekDev.intuzaeats.presenter.impl

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diyarbekDev.intuzaeats.data.models.FoodData
import com.diyarbekDev.intuzaeats.data.models.MenuData
import com.diyarbekDev.intuzaeats.data.models.ResultData
import com.diyarbekDev.intuzaeats.domain.repository.MainRepository
import com.diyarbekDev.intuzaeats.presenter.HomeFragmentViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModelImpl @Inject constructor(
    private val repository: MainRepository,
) : HomeFragmentViewModel, ViewModel() {

    override val getFoodSuccessFlow = MutableStateFlow<List<FoodData>>(emptyList())

    override val getMenuSuccessFlow = MutableStateFlow<List<MenuData>>(emptyList())

    override val filterFoodByCategory = MutableStateFlow<List<FoodData>>(emptyList())


    override suspend fun getMenu() {
        repository.getMenu().onEach {
            when(it) {
                is ResultData.Success -> {
                    getMenuSuccessFlow.value = it.data
                }
                is ResultData.Error -> {

                }
                is ResultData.Message -> {

                }
            }
        }.launchIn(viewModelScope)
    }

    override suspend fun getFood() {
        repository.getFood().onEach {
            when(it) {
                is ResultData.Success -> {
                    getFoodSuccessFlow.value = it.data
                    filterFoodByCategory.value = it.data
                }
                is ResultData.Error -> {

                }
                is ResultData.Message -> {

                }
            }
        }.launchIn(viewModelScope)
    }

    override fun getFilteredFood(categoryId: Int) {
        viewModelScope.launch {
            filterFoodByCategory.emit(getFoodSuccessFlow.value.filter { it.category ==  categoryId})
        }
    }
}