package com.example.assessment.adapters

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.assessment.databinding.ItemStarwarsBinding
import com.example.assessment.model.entity.Starship
import com.example.assessment.model.ui.StarWarsEntityUI

/**
 * [RecyclerView.Adapter] that can display a [StarWarsEntityUI].
 */
class StarWarsAdapter : ListAdapter<StarWarsEntityUI, StarWarsAdapter.StarWarsItemVH>(diffUtil) {

    var onItemClickedCallback: ((StarWarsEntityUI) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StarWarsItemVH {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemStarwarsBinding.inflate(layoutInflater, parent, false)
        return StarWarsItemVH(itemBinding)
    }

    override fun onBindViewHolder(holder: StarWarsItemVH, position: Int) {
        holder.bind(currentList[position])
    }

    inner class StarWarsItemVH(private val binding: ItemStarwarsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private var boundItem: StarWarsEntityUI? = null

        init {
            itemView.setOnClickListener {
                boundItem?.let { safeItem ->
                    onItemClickedCallback?.invoke(safeItem)
                }
            }
        }

        fun bind(starWarsEntity: StarWarsEntityUI) {
            boundItem = starWarsEntity
            binding.content.text = starWarsEntity.getTitle()
            binding.type.text = starWarsEntity.getType()
            if (starWarsEntity is Starship) {
                binding.statusView.background = ColorDrawable(Color.BLUE)
            }
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<StarWarsEntityUI>() {
            override fun areItemsTheSame(
                oldItem: StarWarsEntityUI,
                newItem: StarWarsEntityUI
            ): Boolean {
                return oldItem.getStarWarsEntityId() == newItem.getStarWarsEntityId()
            }

            override fun areContentsTheSame(
                oldItem: StarWarsEntityUI,
                newItem: StarWarsEntityUI
            ): Boolean {
                return oldItem.getTitle() == newItem.getTitle()
            }
        }
    }
}


