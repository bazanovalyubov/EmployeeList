package com.example.employeelist.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.employeelist.R

@BindingAdapter("load")

fun loadAvatar(view:ImageView, url:String?) {
    Glide.with(view).load(url).placeholder(R.drawable.default_avatar).into(view)
}