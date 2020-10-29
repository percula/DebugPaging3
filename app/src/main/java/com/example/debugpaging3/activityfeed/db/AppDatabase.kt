package com.example.debugpaging3.activityfeed.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.debugpaging3.activityfeed.models.FeedEvent

@Database(entities = [FeedEvent::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun eventDao(): EventDao
}