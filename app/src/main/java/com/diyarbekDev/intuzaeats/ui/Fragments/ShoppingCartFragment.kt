package com.diyarbekDev.intuzaeats.ui.Fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.diyarbekDev.intuzaeats.R
import com.diyarbekDev.intuzaeats.data.local.LocalStorage
import com.diyarbekDev.intuzaeats.databinding.FragmentShoppingCartBinding
import com.diyarbekDev.intuzaeats.ui.Fragments.adapters.ItemOrderedFoodAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ShoppingCartFragment: Fragment(R.layout.fragment_shopping_cart) {

    @Inject
    lateinit var localStorage: LocalStorage
    private val viewBinding: FragmentShoppingCartBinding by viewBinding(FragmentShoppingCartBinding::bind)

    private var _adapter: ItemOrderedFoodAdapter? = null
    private val adapter: ItemOrderedFoodAdapter get() = _adapter!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewBinding.rvFoods.adapter = adapter
        adapter.models = Basket.mutableFoods

    }




    override fun onDestroyView() {
        super.onDestroyView()
        _adapter = null
    }


}