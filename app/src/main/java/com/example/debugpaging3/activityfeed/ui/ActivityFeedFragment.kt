package com.example.debugpaging3.activityfeed.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.debugpaging3.activityfeed.adapters.FeedAdapter
import com.example.debugpaging3.activityfeed.vm.ActivityFeedViewModel
import com.example.debugpaging3.databinding.FragmentActivityFeedBinding
import com.example.debugpaging3.ui.adapters.BaseHandler
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ActivityFeedFragment: Fragment() {

    private val viewModel by activityViewModels<ActivityFeedViewModel>()
    private lateinit var binding: FragmentActivityFeedBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentActivityFeedBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        setupRecyclerView()

        return binding.root
    }


    private fun setupRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(context ?: return)
        binding.recyclerView.setHasFixedSize(true)
        val adapter = FeedAdapter(BaseHandler(), viewLifecycleOwner)

        binding.recyclerView.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.pagingFlow.collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }
    }

}