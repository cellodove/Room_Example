package com.peter.room_example.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.peter.room_example.databinding.FragmentEditBinding
import com.peter.room_example.extension.initToolbar
import com.peter.room_example.ui.base.BaseFragment

class EditFragment : BaseFragment<FragmentEditBinding>(FragmentEditBinding::inflate) {
    private val viewModel : MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar(binding.toolbar,"로그 입력", backClickVisible = false)
        binding.showLogButton.setOnClickListener {
            viewModel.liveFragmentStep.value = MainViewModel.FragmentStep.LOG
        }


        binding.saveButton.setOnClickListener {
            viewModel.saveLog(binding.editText.text.toString())
        }

        binding.deleteButton.setOnClickListener {
            viewModel.deleteLog()
        }
    }
}