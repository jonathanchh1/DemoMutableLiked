package com.emi.demomutableliked

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.setContentView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.emi.demomutableliked.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var liked: Liked
    lateinit var factory : LikeViewFactory
    lateinit var viewModel : LikeViewModel
     lateinit var mAdapter : LikingAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val layoutManager = LinearLayoutManager(this)
        binding.rvList.layoutManager = layoutManager
        liked = Liked(0)
        val listLiked = ArrayList<Liked>()
        factory = LikeViewFactory(this, liked)
        viewModel = ViewModelProviders.of(this, factory).get(LikeViewModel::class.java)
        binding.viewModels = viewModel
        mAdapter = LikingAdapter(listLiked)
        binding.rvList.adapter = mAdapter

        viewModel.likes.observe(this, Observer {
            like -> like?.let {
            liked.likes = like
        }
          })
    }
}
