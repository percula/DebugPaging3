package com.example.debugpaging3.activityfeed.db

import androidx.paging.PagingSource
import androidx.room.InvalidationTracker
import androidx.room.RoomDatabase
import com.example.debugpaging3.activityfeed.models.FeedEvent
import dev.percula.ktx.onIO

class EventPagingSource(val db: RoomDatabase, val eventDao: EventDao): PagingSource<String, FeedEvent>() {

    companion object {
        const val TABLE = "events"
    }

    private val observer = object : InvalidationTracker.Observer(TABLE) {
        override fun onInvalidated(tables: Set<String>) {
            if (!invalid) {
                invalidate()
                db.invalidationTracker.removeObserver(this)
            }
        }
    }

    init {
        db.invalidationTracker.addObserver(observer)
    }

    override suspend fun load(params: LoadParams<String>): LoadResult<String, FeedEvent> {
        onIO {
            // 1. Get timestamp of initial key (or current time if no key provided)
            val timestamp = params.key?.let {
                val event = eventDao.loadEventTimeStamp(it)
                event?.timestamp
            } ?: (System.currentTimeMillis() / 1000)

            // 2. Get list starting at that timestamp with limit
            eventDao.loadEvents(timestamp, params.loadSize + 1)
        }.let { events ->
            val nextKey = events.getOrNull(params.loadSize)?.eventID
            return LoadResult.Page(
                    data = events.take(params.loadSize),
                    prevKey = null, // TODO
                    nextKey = nextKey
            )
        }
    }

}