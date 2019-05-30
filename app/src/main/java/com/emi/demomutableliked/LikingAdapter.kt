package com.emi.demomutableliked

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.emi.demomutableliked.databinding.MutableLikeItemBinding

class LikingAdapter constructor(private var liked : List<Liked>) : RecyclerView.Adapter<LikingAdapter.LikedViewHolder>(){


    override fun onBindViewHolder(holder: LikedViewHolder, position: Int) {
        holder.bind(liked.get(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LikedViewHolder {
        val binding : MutableLikeItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.mutable_like_item,
            parent, false)

        return LikedViewHolder(binding)

    }

    override fun getItemCount(): Int {
        return liked.size
    }

    inner class LikedViewHolder(val binding : MutableLikeItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(item : Liked){
            if(binding.viewModel == null){
                binding.viewModel = LikeViewModel(itemView.context, item)
            }else{
                binding.viewModel!!.like = item
            }
        }
    }
}