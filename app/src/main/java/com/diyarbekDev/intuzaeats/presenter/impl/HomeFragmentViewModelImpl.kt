package com.diyarbekDev.intuzaeats.presenter.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diyarbekDev.intuzaeats.data.models.FoodData
import com.diyarbekDev.intuzaeats.data.models.MenuData
import com.diyarbekDev.intuzaeats.data.models.ResultData
import com.diyarbekDev.intuzaeats.domain.repository.MainRepository
import com.diyarbekDev.intuzaeats.presenter.HomeFragmentViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModelImpl @Inject constructor(
    private val repository: MainRepository,
) : HomeFragmentViewModel, ViewModel() {

    override val getFoodSuccessFlow = MutableSharedFlow<List<FoodData>>()

    override val getMenuSuccessFlow = MutableSharedFlow<List<MenuData>>()


    override suspend fun getMenu() {
        repository.getMenu().onEach {
            when(it) {
                is ResultData.Success -> {
                    getMenuSuccessFlow.emit(it.data)
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
                    getFoodSuccessFlow.emit(it.data)
                }
                is ResultData.Error -> {

                }
                is ResultData.Message -> {

                }
            }
        }.launchIn(viewModelScope)
    }
}