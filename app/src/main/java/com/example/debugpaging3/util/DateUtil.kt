package com.example.debugpaging3.util

import org.threeten.bp.*
import org.threeten.bp.format.DateTimeFormatter


/**
 * Creates a human readable date from a date that occurs in the past. Doesn't add the day of the
 * week.
 */
fun ZonedDateTime.createPastDateNoDayString(localZoneId: ZoneId = ZoneId.systemDefault()): String {
    val now = OffsetDateTime.now(localZoneId)
    val localZoneDateTime = (takeIf { it.zone == localZoneId } ?: withZoneSameInstant(localZoneId))
    val dayOfYear = localZoneDateTime?.dayOfYear ?: return ""
    val daysAgo = now.dayOfYear - dayOfYear
    return when {
        daysAgo == 0 -> "Today" // "Today"
        daysAgo == 1 -> "Yesterday" // "Yesterday"
        daysAgo < 365 -> localZoneDateTime.format(DateTimeFormatter.ofPattern("MMMM d")) // "May 5"
        else -> localZoneDateTime.format(DateTimeFormatter.ofPattern("MMMM d, yyyy")) // "May 5, 2020"
    }
}

/**
 * Creates a human readable timestamp from a date that has occurred in the past.
 */
fun ZonedDateTime.createPastTimeString(localZoneId: ZoneId = ZoneId.systemDefault()): String { // Check if no timestamp was sent in
    val now = OffsetDateTime.now(localZoneId).toZonedDateTime()
    val localZoneDateTime = (takeIf { it.zone == localZoneId } ?: withZoneSameInstant(localZoneId))
    val fromLastHour = localZoneDateTime.isAfter(now.minusHours(1))
    return if (fromLastHour) {
        // eg "55 min ago"
        val minutesAgo = Duration.between(localZoneDateTime, now).toMinutes()
        "$minutesAgo min ago"
    } else {
        // eg "12:00 PM"
        localZoneDateTime.format(DateTimeFormatter.ofPattern("h:mm a"))
    }
}