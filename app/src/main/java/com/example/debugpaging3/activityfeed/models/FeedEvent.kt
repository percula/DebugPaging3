package com.example.debugpaging3.activityfeed.models

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.debugpaging3.util.createPastDateNoDayString
import com.example.debugpaging3.util.createPastTimeString
import dev.percula.ktx.mutableLiveDataOf
import org.threeten.bp.Instant
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.ZoneId
import org.threeten.bp.ZoneOffset

@Entity(tableName = "events")
data class FeedEvent(
    @PrimaryKey val eventID: String,
    val timestamp: Long,
    val title: String? = null,
    val sourceText: String? = null,
    val caption: String? = null) {

    @Ignore @Transient val time: OffsetDateTime? = timestamp.let { OffsetDateTime.ofInstant(Instant.ofEpochSecond(it), ZoneOffset.UTC) }

    @Ignore
    @Transient
    val timestampText =
        time?.atZoneSameInstant(ZoneId.systemDefault())
            ?.createPastTimeString() + " " + time?.atZoneSameInstant(ZoneId.systemDefault())
            ?.createPastDateNoDayString()
}