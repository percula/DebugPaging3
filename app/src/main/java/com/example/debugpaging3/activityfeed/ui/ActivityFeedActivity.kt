package com.example.debugpaging3.activityfeed.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.debugpaging3.R
import com.example.debugpaging3.databinding.ActivityFeedBinding

class ActivityFeedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityFeedBinding>(this, R.layout.activity_feed)
        binding.lifecycleOwner = this
    }

}