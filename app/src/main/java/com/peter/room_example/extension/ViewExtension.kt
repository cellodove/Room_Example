package com.peter.room_example.extension

import android.view.View
import com.peter.room_example.databinding.LayoutToolbarBinding

fun initToolbar(toolbar: LayoutToolbarBinding, title : String, backClickVisible : Boolean , backClick:(() -> Unit)? = null){
    toolbar.apply {
        this.title.text = title
        this.backButton.visibility = if (backClickVisible) View.VISIBLE else View.GONE
        this.backButton.setOnClickListener {
            backClick?.invoke()
        }
    }
}