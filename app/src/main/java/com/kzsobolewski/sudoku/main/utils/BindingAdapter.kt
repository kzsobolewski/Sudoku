package com.kzsobolewski.sudoku.main.utils

import android.view.View
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter

@BindingAdapter("isVisible")
fun setViewVisibility(view: View, isVisible: Boolean) {
    view.isVisible = isVisible
}
