package com.example.debugpaging3.activityfeed.db

import androidx.paging.*
import androidx.room.withTransaction
import com.example.debugpaging3.activityfeed.createDummyEvents
import com.example.debugpaging3.activityfeed.models.FeedEvent
import java.lang.Exception

@OptIn(ExperimentalPagingApi::class)
class EventsRemoteMediator(
    private val database: AppDatabase,
) : RemoteMediator<String, FeedEvent>() {

    override suspend fun load(loadType: LoadType, state: PagingState<String, FeedEvent>): MediatorResult {
        return try {
            // Disable prepending items for this demo
            if (loadType == LoadType.PREPEND) return MediatorResult.Success(endOfPaginationReached = true)

            // This is where I usually load the events from the network using a key,
            // but for for this demo I'll generate local dummy events
            val events = createDummyEvents(state.config.pageSize)

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    database.eventDao().clearAll()
                }

                // Insert new events into database, which invalidates the
                // current PagingData, allowing Paging to present the updates
                // in the DB.
                database.eventDao().insertAll(events)
            }

            MediatorResult.Success(endOfPaginationReached = false)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }

}