package com.example.debugpaging3.activityfeed

import com.example.debugpaging3.activityfeed.models.*
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.ZoneId
import java.util.*
import kotlin.random.Random

/**
 * Utility class to create dummy events for testing purposes
 */

var lastTime: Long = OffsetDateTime.now(ZoneId.of("UTC")).minusMinutes(Random.nextLong(1,30)).toInstant().epochSecond

private fun makeDummyEvent(time: Long): FeedEvent {
        return FeedEvent(
                title = "Title",
                sourceText = "Source",
                eventID = UUID.randomUUID().toString(),
                timestamp = time,
                caption = "Caption"
        )
}

fun createDummyEvents(qty: Int): List<FeedEvent> {
    val events = mutableListOf<FeedEvent>()
    for (i in 0 until qty) {
            events.add(makeDummyEvent(lastTime))
        lastTime = lastTime.minus(60 * Random.nextLong(1,480))
    }
    return events
}