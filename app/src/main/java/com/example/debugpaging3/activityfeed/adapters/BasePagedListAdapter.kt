package com.example.debugpaging3.activityfeed.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil

/**
 * Originally based on https://medium.com/androiddevelopers/android-data-binding-recyclerview-db7c40d9f0e4
 */
abstract class BasePagedListAdapter<T: Any>(
    private val lifecycleOwner: LifecycleOwner? = null,
    diffCallback: DiffUtil.ItemCallback<T>
)
    : PagingDataAdapter<T, BaseViewHolder<T?>>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T?> {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
                layoutInflater, viewType, parent, false)
        return BaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T?>, position: Int) {
        val obj = getItem(position)
        holder.bind(obj, lifecycleOwner)
    }

    override fun getItemViewType(position: Int): Int {
        return getLayoutIdForPosition(position)
    }

    protected abstract fun getLayoutIdForPosition(position: Int): Int

}