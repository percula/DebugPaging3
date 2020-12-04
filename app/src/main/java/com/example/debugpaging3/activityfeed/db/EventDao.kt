package com.example.debugpaging3.activityfeed.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.debugpaging3.activityfeed.models.FeedEvent

@Dao
interface EventDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(events: List<FeedEvent>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(event: FeedEvent)

    @Query("SELECT * FROM events")
    fun pagingSource(): PagingSource<Int, FeedEvent>

    @Query("SELECT * FROM events where timestamp <= :timestamp ORDER BY timestamp DESC, eventID DESC LIMIT :count")
    fun loadEvents(timestamp: Long, count: Int): List<FeedEvent>

    @Query("SELECT * FROM events where eventID = :key LIMIT 1")
    fun loadEventTimeStamp(key: String): FeedEvent?

    @Query("DELETE FROM events")
    suspend fun clearAll()
}