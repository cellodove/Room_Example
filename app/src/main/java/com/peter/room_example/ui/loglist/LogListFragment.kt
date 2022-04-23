package com.peter.room_example.ui.loglist

import android.os.Bundle
import android.view.View
import android.widget.Toast
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
        viewModelObserver()
        initToolbar(binding.toolbar, "로그 기록", backClickVisible = true, backClick = {
            requireActivity().onBackPressed()
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


    override fun onLogItemClick(log: String) {
        Toast.makeText(requireContext(), log,Toast.LENGTH_SHORT).show()
    }

    override fun onLogItemLongClick(position: Int) {
        viewModel.deleteLog(position)
    }

    private fun viewModelObserver(){
        viewModel.getLog()
        viewModel.getLogData.observe(viewLifecycleOwner){
            (binding.recyclerView.adapter as LogAdapter).submitList(it)
        }
    }
}