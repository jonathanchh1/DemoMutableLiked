package com.emi.demomutableliked


import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.databinding.BindingAdapter

object BindingMethod {



    @BindingAdapter("bind:popularityIcon")
    @JvmStatic
    fun popularityIcon(view: ImageView, popularity : Popularity){
        val color = getAssociatedColor(view.context, popularity)
        ImageViewCompat.setImageTintList(view, ColorStateList.valueOf(color))
        view.setImageDrawable(Drawables(view.context, popularity))
    }


    @BindingAdapter("bind:progressTint")
    @JvmStatic fun tintPopularity(view : ProgressBar, popularity: Popularity){
        val color = getAssociatedColor(view.context, popularity)
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            view.progressTintList = ColorStateList.valueOf(color)
        }
    }

    @BindingAdapter(value = ["bind:progressScaled", "android:max"], requireAll = true)
    @JvmStatic fun setProgress(progressBar: ProgressBar, like : Int, max : Int){
        progressBar.progress = (like * max / 5).coerceAtMost(max)
    }

    @BindingAdapter("bind:hideIfZero")
    @JvmStatic
    fun hideIfZero(view : View, number : Int){
        view.visibility = if(number == 0) View.GONE else View.VISIBLE
    }

    private fun getAssociatedColor(context : Context, popularity: Popularity) :Int{
        return when(popularity){

            Popularity.Normal -> context.theme.obtainStyledAttributes(intArrayOf(android.R.attr.colorForeground)).getColor(0, 0x00000)
            Popularity.Star ->{
                ContextCompat.getColor(context, R.color.star)
            }
            Popularity.Popular ->{
                ContextCompat.getColor(context, R.color.popular)
            }
        }
    }

    private fun Drawables(context: Context, popularity: Popularity) : Drawable?{
        return when(popularity){
            Popularity.Normal -> {
                ContextCompat.getDrawable(context, R.drawable.ic_thumb_up_black_24dp)
            }

            Popularity.Popular ->{
                ContextCompat.getDrawable(context, R.drawable.ic_whatshot_black_24dp)
            }

            Popularity.Star ->{
                ContextCompat.getDrawable(context, R.drawable.ic_whatshot_black_24dp)
            }
        }
    }

}