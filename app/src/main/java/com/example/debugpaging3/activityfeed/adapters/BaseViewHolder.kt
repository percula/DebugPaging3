package com.example.debugpaging3.activityfeed.adapters

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.debugpaging3.BR

/**
 * Originally based on https://medium.com/androiddevelopers/android-data-binding-recyclerview-db7c40d9f0e4
 */
open class BaseViewHolder<T>(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(obj: T, lifecycleOwner: LifecycleOwner? = null) {
        binding.setVariable(BR.obj, obj)
        lifecycleOwner?.let { binding.lifecycleOwner = it }
        binding.executePendingBindings()
    }

}