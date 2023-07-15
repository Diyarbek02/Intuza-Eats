package com.diyarbekDev.intuzaeats.ui.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.diyarbekDev.intuzaeats.R
import com.diyarbekDev.intuzaeats.data.local.LocalStorage
import com.diyarbekDev.intuzaeats.databinding.FragmentHomeBinding
import com.diyarbekDev.intuzaeats.presenter.HomeFragmentViewModel
import com.diyarbekDev.intuzaeats.presenter.impl.HomeFragmentViewModelImpl
import com.diyarbekDev.intuzaeats.ui.Fragments.adapters.ItemFoodAdapter
import com.diyarbekDev.intuzaeats.ui.Fragments.adapters.ItemMenuAdapter
import com.diyarbekDev.intuzaeats.utils.visibilityProgressBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.ldralighieri.corbind.view.clicks
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    @Inject
    lateinit var localStorage: LocalStorage
    private val viewBinding: FragmentHomeBinding by viewBinding(FragmentHomeBinding::bind)
    private val navController by lazy(LazyThreadSafetyMode.NONE) { findNavController() }
    private val viewModel: HomeFragmentViewModel by viewModels<HomeFragmentViewModelImpl>()

    private var _adapter: ItemFoodAdapter? = null
    private val adapter: ItemFoodAdapter get() = _adapter!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        lifecycleScope.launchWhenResumed {
            viewModel.getFood()
        }
        initListeners()
        initObservers()
        initAdapters()
    }

    private fun initObservers() {
        viewModel.getFoodSuccessFlow.onEach {
            viewBinding.shimmerLayout.isVisible = false
            viewBinding.rvFood.isVisible = true
            adapter.submitList(it)
            val temp = mutableListOf<String>()
            it.forEach { data->
                temp.add(data.image)
            }
        }.launchIn(lifecycleScope)
    }

    private fun initListeners() {
//        viewBinding.btnViewMenu.clicks().debounce(200).onEach {
//            visibilityProgressBar.emit(true)
//            navController.navigate(HomeFragmentDirections.actionHomeFragmentToMenuFragment())
//        }.launchIn(lifecycleScope)
    }

    private fun initAdapters() {
        _adapter = ItemFoodAdapter()
        viewBinding.rvFood.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _adapter = null
    }

}