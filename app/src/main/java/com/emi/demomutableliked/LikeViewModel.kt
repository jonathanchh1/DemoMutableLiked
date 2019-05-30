package com.emi.demomutableliked

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class LikeViewModel constructor(private var context : Context, var like : Liked) : ViewModel() {


    val MutableLiked = MutableLiveData<Int>(0)
    val likes : LiveData<Int>
    get() = MutableLiked


    val popular : LiveData<Popularity> = Transformations.map(MutableLiked){
        when{
            it > 6 -> Popularity.Popular
            it > 4 -> Popularity.Star
            else -> Popularity.Normal
        }
    }

    fun onLiked(){
        MutableLiked.value = (MutableLiked.value ?: 0) + 1
    }

}

enum class Popularity{
    Star,
    Normal,
    Popular
}