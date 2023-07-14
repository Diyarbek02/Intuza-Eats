package com.diyarbekDev.intuzaeats.ui.Fragments.adapters

import android.view.LayoutInflater
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.diyarbekDev.intuzaeats.R
import com.diyarbekDev.intuzaeats.data.models.MenuData
import com.diyarbekDev.intuzaeats.databinding.FragmentHomeBinding
import com.diyarbekDev.intuzaeats.databinding.ItemMenuBinding
import com.diyarbekDev.intuzaeats.utils.setImageWithGlide

class ItemMenuAdapter :
    androidx.recyclerview.widget.ListAdapter<MenuData, ItemMenuAdapter.ViewHolder>(MyDiffUtil) {

    private var onItemClickListener: ((Int) -> Unit)? = null

    fun setOnItemClickListener(block: (Int) -> Unit) {
        onItemClickListener = block
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ViewHolder {
        return ViewHolder(
            ItemMenuBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind()


    inner class ViewHolder(private val binding: ItemMenuBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind() {
            val d = getItem(absoluteAdapterPosition)
            binding.apply {

                name.text = d.name
                if (d.image == null) {
                    imageMenu.setImageResource(R.drawable.ic_launcher_background)
                } else {
                    imageMenu.setImageWithGlide(binding.root.context, d.image)
                }
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClickListener?.invoke(getItem(absoluteAdapterPosition).id)
            }
        }
    }

    private object MyDiffUtil : DiffUtil.ItemCallback<MenuData>() {
        override fun areItemsTheSame(
            oldItem: MenuData, newItem: MenuData
        ): Boolean = oldItem == newItem

        override fun areContentsTheSame(
            oldItem: MenuData, newItem: MenuData
        ): Boolean = oldItem.id == newItem.id
    }
}