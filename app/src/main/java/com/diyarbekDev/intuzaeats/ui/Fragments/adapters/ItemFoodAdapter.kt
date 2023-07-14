package com.diyarbekDev.intuzaeats.ui.Fragments.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.diyarbekDev.intuzaeats.R
import com.diyarbekDev.intuzaeats.data.models.FoodData
import com.diyarbekDev.intuzaeats.databinding.ItemFoodBinding
import com.diyarbekDev.intuzaeats.utils.setImageWithGlide

class ItemFoodAdapter :
    ListAdapter<FoodData, ItemFoodAdapter.ViewHolder>(MyDiffUtil) {

    private var onItemClickListener: ((Int) -> Unit)? = null

    fun setOnItemClickListener(block: (Int) -> Unit) {
        onItemClickListener = block
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ViewHolder {
        return ViewHolder(
            ItemFoodBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind()


    inner class ViewHolder(private val binding: ItemFoodBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind() {
            val d = getItem(absoluteAdapterPosition)
            binding.apply {
                tvPrice.text = d.price.toString()
                tvFoodName.text = d.name
                tvTitle.text = d.description
                if (d.image == null) {
                    imgFood.setImageResource(R.drawable.ic_launcher_background)
                } else {
                    imgFood.setImageWithGlide(binding.root.context, d.image)
                }
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClickListener?.invoke(getItem(absoluteAdapterPosition).id)
            }
        }
    }

    private object MyDiffUtil : DiffUtil.ItemCallback<FoodData>() {
        override fun areItemsTheSame(
            oldItem: FoodData, newItem: FoodData
        ): Boolean = oldItem == newItem

        override fun areContentsTheSame(
            oldItem: FoodData, newItem: FoodData
        ): Boolean = oldItem.id == newItem.id
    }
}