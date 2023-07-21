package com.diyarbekDev.intuzaeats.ui.Fragments

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.diyarbekDev.intuzaeats.R
import com.diyarbekDev.intuzaeats.data.local.LocalStorage
import com.diyarbekDev.intuzaeats.databinding.FragmentMenuBinding
import com.diyarbekDev.intuzaeats.presenter.HomeFragmentViewModel
import com.diyarbekDev.intuzaeats.presenter.impl.HomeFragmentViewModelImpl
import com.diyarbekDev.intuzaeats.ui.Fragments.adapters.ItemFoodAdapter
import com.diyarbekDev.intuzaeats.ui.Fragments.adapters.ItemMenuAdapter
import com.diyarbekDev.intuzaeats.utils.visibilityProgressBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class MenuFragment : Fragment(R.layout.fragment_menu) {

    @Inject
    lateinit var localStorage: LocalStorage
    private val viewBinding: FragmentMenuBinding by viewBinding(FragmentMenuBinding::bind)
    private val navController by lazy(LazyThreadSafetyMode.NONE) { findNavController() }
    private val viewModel: HomeFragmentViewModel by viewModels<HomeFragmentViewModelImpl>()

    private var _adapter: ItemMenuAdapter? = null
    private var _adapter2: ItemFoodAdapter? = null
    private val adapter: ItemMenuAdapter get() = _adapter!!
    private val adapter2: ItemFoodAdapter get() = _adapter2!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        lifecycleScope.launchWhenResumed {
            viewModel.getMenu()
            viewModel.getFood()
        }
        initAdapters()
        initListeners()
        initObservers()
    }

    private fun initObservers() {
        viewModel.getMenuSuccessFlow.onEach {
            viewBinding.apply {
//                shimmerLayoutMenu.isVisible = false
//                rvMenu.isVisible = true
            }
            adapter.submitList(it)
            val temp = mutableListOf<String>()
            it.forEach { data ->
                temp.add(data.image)
            }
        }.launchIn(lifecycleScope)

        viewModel.filterFoodByCategory.onEach {
            viewBinding.apply {
//                shimmerLayoutFood.isVisible = false
//                rvFood.isVisible = true
            }
            adapter2.submitList(it)
            val temp2 = mutableListOf<String>()
            it.forEach { data ->
                temp2.add(data.image)
            }
        }.launchIn(lifecycleScope)
    }

    private fun initListeners() {
        adapter.setOnItemClickListener {
            viewModel.getFilteredFood(it)
        }
    }

    private fun initAdapters() {
        _adapter = ItemMenuAdapter()
        _adapter2 = ItemFoodAdapter()
        viewBinding.rvMenu.adapter = adapter
        viewBinding.rvFood.adapter = adapter2
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _adapter = null
        _adapter2 = null
    }

}