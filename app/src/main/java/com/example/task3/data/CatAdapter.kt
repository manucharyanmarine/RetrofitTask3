package com.example.task3.data

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.task3.databinding.ItemRvBinding
import com.example.task3.entity.Cat


class CatAdapter :
    ListAdapter<Cat, CatAdapter.CatViewHolder>(CatDiffutil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        return CatViewHolder(
            ItemRvBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CatViewHolder(val binding: ItemRvBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(cat: Cat) {
            with(binding) {
                breed.text = cat.breed
                origin.text = cat.origin
                pattern.text = cat.pattern
            }
        }
    }
}

class CatDiffutil : DiffUtil.ItemCallback<Cat>() {
    override fun areItemsTheSame(oldItem: Cat, newItem: Cat): Boolean = oldItem == newItem

    override fun areContentsTheSame(oldItem: Cat, newItem: Cat): Boolean = oldItem.id == newItem.id
}