package com.example.debugpaging3.activityfeed.vm

import android.view.View
import androidx.annotation.LayoutRes
import androidx.lifecycle.LiveData
import com.example.debugpaging3.R
import com.example.debugpaging3.activityfeed.models.FeedEvent
import dev.percula.ktx.map
import dev.percula.ktx.mutableLiveDataOf

sealed class FeedUIItem(@LayoutRes val layoutRes: Int) {

    abstract fun isSameItem(other: FeedUIItem): Boolean

    data class FeedEventItem(override val event: FeedEvent,
                             val timestamp: LiveData<String>,
                             val title: LiveData<String>,
                             val description: LiveData<String>? = null,
                             val source: LiveData<String>? = null,
                             val sourceVisibility: LiveData<Int> = source?.map(View.VISIBLE) { if (it.isNullOrBlank()) View.GONE else View.VISIBLE  } ?: mutableLiveDataOf(View.GONE))
        : FeedUIItem(R.layout.component_feed_event_wrapper), EventHolder {

        override fun isSameItem(other: FeedUIItem): Boolean {
            return other is FeedEventItem && event.eventID == other.event.eventID
        }
    }
}

interface EventHolder {
    val event: FeedEvent
}