package com.diyarbekDev.intuzaeats.ui.Fragments.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.diyarbekDev.intuzaeats.R
import com.diyarbekDev.intuzaeats.data.models.FoodData
import com.diyarbekDev.intuzaeats.databinding.ItemFoodBinding
import com.diyarbekDev.intuzaeats.databinding.ItemSelectedFoodBinding
import com.diyarbekDev.intuzaeats.utils.setImageWithGlide
import java.text.FieldPosition

class ItemOrderedFoodAdapter : RecyclerView.Adapter<ItemOrderedFoodAdapter.ViewHolder>() {

    var models: MutableList<FoodData> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ItemOrderedFoodAdapter.ViewHolder {
        return ViewHolder(
            ItemSelectedFoodBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount() = models.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.populateModel(models[position], position)
    }


    inner class ViewHolder(private val binding: ItemSelectedFoodBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun populateModel(model: FoodData, position: Int) {
            binding.apply {
                tvFoodName.text = model.name
                tvPrice.text = model.price.toString()
                if (model.image == null) {
                    imageFood.setImageResource(R.drawable.ic_launcher_background)
                } else {
                    imageFood.setImageWithGlide(binding.root.context, model.image)
                }
            }
        }
    }

}