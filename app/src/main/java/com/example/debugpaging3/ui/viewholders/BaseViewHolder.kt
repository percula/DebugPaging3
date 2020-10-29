package com.example.debugpaging3.ui.viewholders

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.debugpaging3.BR
import com.example.debugpaging3.ui.adapters.BaseHandler

/**
 * Originally based on https://medium.com/androiddevelopers/android-data-binding-recyclerview-db7c40d9f0e4
 */
open class BaseViewHolder<T>(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(obj: T, handler: BaseHandler<T>? = null, lifecycleOwner: LifecycleOwner? = null) {
        binding.setVariable(BR.obj, obj)
        handler?.let {  itemHandler ->
            binding.setVariable(BR.handler, itemHandler)
        }
        lifecycleOwner?.let { binding.lifecycleOwner = it }
        binding.executePendingBindings()
    }

}