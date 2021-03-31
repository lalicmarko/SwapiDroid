package com.example.assessment.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.assessment.databinding.ItemMovieBinding
import com.example.assessment.model.ui.PersonUI

/**
 * [RecyclerView.Adapter] that can display a [PersonUI].
 */
class PeopleAdapter : ListAdapter<PersonUI, PeopleAdapter.MovieVH>(DiffUtilCallback) {

    var onItemClickedCallback: ((PersonUI) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieVH {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemMovieBinding.inflate(layoutInflater, parent, false)
        return MovieVH(itemBinding)
    }

    override fun onBindViewHolder(holder: MovieVH, position: Int) {
        holder.bind(currentList[position])
    }

    inner class MovieVH(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private var boundItem: PersonUI? = null

        init {
            itemView.setOnClickListener {
                boundItem?.let { safeItem ->
                    onItemClickedCallback?.invoke(safeItem)
                }
            }
        }

        fun bind(person: PersonUI) {
            boundItem = person
            binding.content.text = person.getTitle()
            binding.type.text = person.getType()
        }
    }
}

object DiffUtilCallback : DiffUtil.ItemCallback<PersonUI>() {
    override fun areItemsTheSame(oldItem: PersonUI, newItem: PersonUI): Boolean {
        return oldItem.getMovieId() == newItem.getMovieId()
    }

    override fun areContentsTheSame(oldItem: PersonUI, newItem: PersonUI): Boolean {
        return oldItem.getTitle() == newItem.getTitle()
    }
}
