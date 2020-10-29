package com.example.debugpaging3.activityfeed.adapters

import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import com.example.debugpaging3.R
import com.example.debugpaging3.activityfeed.vm.FeedUIItem
import com.example.debugpaging3.ui.adapters.BaseHandler
import com.example.debugpaging3.ui.adapters.BasePagedListAdapter

class FeedAdapter(handler: BaseHandler<FeedUIItem?>, lifecycleOwner: LifecycleOwner) : BasePagedListAdapter<FeedUIItem>(handler, lifecycleOwner, FeedUIItemComparator) {

    fun getItemAtPosition(position: Int): FeedUIItem? {
        return super.getItem(position)
    }

    override fun getLayoutIdForPosition(position: Int): Int {
        return getItem(position)?.layoutRes ?: R.layout.component_feed_event
    }

}

object FeedUIItemComparator : DiffUtil.ItemCallback<FeedUIItem>() {

    override fun areItemsTheSame(oldItem: FeedUIItem, newItem: FeedUIItem): Boolean {
        return oldItem.isSameItem(newItem)
    }

    override fun areContentsTheSame(oldItem: FeedUIItem, newItem: FeedUIItem): Boolean {
        return true
    }

}
