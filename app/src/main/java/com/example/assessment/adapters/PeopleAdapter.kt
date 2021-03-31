package com.example.assessment.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.assessment.databinding.ItemMovieBinding
import com.example.assessment.model.ui.StarWarsEntityUI

/**
 * [RecyclerView.Adapter] that can display a [StarWarsEntityUI].
 */
class PeopleAdapter : ListAdapter<StarWarsEntityUI, PeopleAdapter.PersonVH>(diffUtil) {

    var onItemClickedCallback: ((StarWarsEntityUI) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonVH {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemMovieBinding.inflate(layoutInflater, parent, false)
        return PersonVH(itemBinding)
    }

    override fun onBindViewHolder(holder: PersonVH, position: Int) {
        holder.bind(currentList[position])
    }

    inner class PersonVH(private val binding: ItemMovieBinding) :
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
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<StarWarsEntityUI>() {
            override fun areItemsTheSame(oldItem: StarWarsEntityUI, newItem: StarWarsEntityUI): Boolean {
                return oldItem.getStarWarsEntityId() == newItem.getStarWarsEntityId()
            }

            override fun areContentsTheSame(oldItem: StarWarsEntityUI, newItem: StarWarsEntityUI): Boolean {
                return oldItem.getTitle() == newItem.getTitle()
            }
        }
    }
}


