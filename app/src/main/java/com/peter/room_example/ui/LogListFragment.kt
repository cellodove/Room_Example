package com.peter.room_example.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.peter.room_example.databinding.FragmentLogBinding
import com.peter.room_example.extension.initToolbar
import com.peter.room_example.ui.base.BaseFragment

class LogListFragment : BaseFragment<FragmentLogBinding>(FragmentLogBinding::inflate) {
    private val viewModel : MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar(binding.toolbar, "로그 기록", backClickVisible = true, backClick = { viewModel.liveFragmentStep.value = MainViewModel.FragmentStep.EDIT })

    }
}