package com.example.debugpaging3.activityfeed.adapters

import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import com.example.debugpaging3.R
import com.example.debugpaging3.activityfeed.models.FeedEvent

class FeedAdapter(lifecycleOwner: LifecycleOwner) : BasePagedListAdapter<FeedEvent>(lifecycleOwner, FeedUIItemComparator) {

    override fun getLayoutIdForPosition(position: Int): Int {
        return R.layout.component_feed_event
    }

}

object FeedUIItemComparator : DiffUtil.ItemCallback<FeedEvent>() {

    override fun areItemsTheSame(oldItem: FeedEvent, newItem: FeedEvent): Boolean {
        return oldItem.eventID.equals(newItem.eventID)
    }

    override fun areContentsTheSame(oldItem: FeedEvent, newItem: FeedEvent): Boolean {
        return true
    }

}
