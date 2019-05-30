package com.emi.demomutableliked

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class LikeViewFactory constructor(private var context: Context, private var like : Liked) : ViewModelProvider.Factory{

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(LikeViewModel::class.java))
            return LikeViewModel(context, like) as T

        throw IllegalArgumentException("its not the modelclass")
    }
}