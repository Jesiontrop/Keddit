package com.jesiontrop.keddit.base.utils.extensions

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import com.jesiontrop.keddit.R
import com.squareup.picasso.Picasso

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

fun ImageView.loadImg(imageUrl: String) {
    if (TextUtils.isEmpty(imageUrl)) {
        Picasso.get().load(R.mipmap.ic_launcher).into(this)
    } else {
        Picasso.get().load(imageUrl).into(this)
    }
}

