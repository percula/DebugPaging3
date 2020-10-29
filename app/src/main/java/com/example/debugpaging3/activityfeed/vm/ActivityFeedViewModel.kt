package com.example.debugpaging3.activityfeed.vm

import android.app.Application
import androidx.lifecycle.*
import androidx.paging.*
import com.example.debugpaging3.BaseApplication
import com.example.debugpaging3.activityfeed.db.EventsRemoteMediator
import com.example.debugpaging3.activityfeed.models.FeedEvent
import com.example.debugpaging3.util.createPastDateNoDayString
import com.example.debugpaging3.util.createPastTimeString
import dev.percula.ktx.mutableLiveDataOf
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.threeten.bp.ZoneId

class ActivityFeedViewModel(application: Application) : AndroidViewModel(application) {

    private val pager = Pager(
            config = PagingConfig(
                    pageSize = 10,
                    initialLoadSize = 10,
                    enablePlaceholders = false
            ),
            remoteMediator = EventsRemoteMediator(BaseApplication.app.db)
    ) {
        BaseApplication.app.db.eventDao().pagingSource()
    }

    val pagingFlow: Flow<PagingData<FeedUIItem>> = pager.flow.map {
        it.map { event -> createFeedEventItem(event) }
    }.cachedIn(viewModelScope)

    private fun createFeedEventItem(item: FeedEvent): FeedUIItem {
        return FeedUIItem.FeedEventItem(
            timestamp = mutableLiveDataOf(
                item.time?.atZoneSameInstant(ZoneId.systemDefault())
                    ?.createPastTimeString() + " " + item.time?.atZoneSameInstant(
                    ZoneId.systemDefault()
                )?.createPastDateNoDayString()
            ),
            title = mutableLiveDataOf(item.title),
            description = mutableLiveDataOf(item.caption),
            source = mutableLiveDataOf(item.sourceText),
            event = item
        )
    }

}