package com.peter.room_example.ui.loglist

import android.os.Bundle
import android.view.View
import android.widget.ListAdapter
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import com.peter.room_example.databinding.FragmentLogBinding
import com.peter.room_example.extension.initToolbar
import com.peter.room_example.ui.MainViewModel
import com.peter.room_example.ui.base.BaseFragment

class LogListFragment : BaseFragment<FragmentLogBinding>(FragmentLogBinding::inflate),LogAdapter.LogListListener {
    private val viewModel : MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar(binding.toolbar, "로그 기록", backClickVisible = true, backClick = { viewModel.liveFragmentStep.value =
            MainViewModel.FragmentStep.EDIT
        })


        binding.recyclerView.apply {
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )

            adapter = LogAdapter(this@LogListFragment)
        }
    }

    override fun onLogItemClick(position: Int) {

    }

    override fun onLogItemLongClick(position: Int) {

    }
}