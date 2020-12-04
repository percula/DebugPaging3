package com.example.debugpaging3.activityfeed.vm

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.debugpaging3.BaseApplication
import com.example.debugpaging3.activityfeed.db.EventPagingSource
import com.example.debugpaging3.activityfeed.db.EventsRemoteMediator
import com.example.debugpaging3.activityfeed.models.FeedEvent
import kotlinx.coroutines.flow.Flow

class ActivityFeedViewModel(application: Application) : AndroidViewModel(application) {

    private val pager = Pager(
            config = PagingConfig(
                    pageSize = 10,
                    initialLoadSize = 10,
                    enablePlaceholders = false
            ),
            remoteMediator = EventsRemoteMediator(BaseApplication.app.db)
    ) {
        EventPagingSource(BaseApplication.app.db, BaseApplication.app.db.eventDao())
    }

    val pagingFlow: Flow<PagingData<FeedEvent>> = pager.flow.cachedIn(viewModelScope)

}