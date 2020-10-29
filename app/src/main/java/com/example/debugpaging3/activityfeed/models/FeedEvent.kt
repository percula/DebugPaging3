package com.example.debugpaging3.activityfeed.models

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import org.threeten.bp.Instant
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.ZoneOffset

@Entity(tableName = "events")
data class FeedEvent(
    @PrimaryKey val eventID: String,
    val title: String? = null,
    val sourceText: String? = null,
    val timestamp: Long? = null,
    val caption: String? = null) {

    @Ignore @Transient val time: OffsetDateTime? = timestamp?.let { OffsetDateTime.ofInstant(Instant.ofEpochSecond(it), ZoneOffset.UTC) }

}