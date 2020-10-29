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

    @Query("SELECT * FROM events ORDER BY timestamp DESC, eventID DESC")
    fun pagingSource(): PagingSource<Int, FeedEvent>

    @Query("DELETE FROM events")
    suspend fun clearAll()
}