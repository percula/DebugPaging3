package com.example.debugpaging3.activityfeed.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.debugpaging3.R
import com.example.debugpaging3.databinding.ActivityFeedBinding

class ActivityFeedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityFeedBinding>(this, R.layout.activity_feed)
        binding.lifecycleOwner = this

        findNavController(R.id.nav_host_fragment).setGraph(R.navigation.activity_feed_nav_graph, intent.extras)
    }

}