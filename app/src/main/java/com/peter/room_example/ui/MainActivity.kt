package com.peter.room_example.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.peter.room_example.R

class MainActivity : AppCompatActivity() {
    private val viewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModelObserver()
    }


    private fun viewModelObserver(){
        viewModel.liveFragmentStep.observe(this){ step ->
            if (!isDestroyed){
                supportFragmentManager.beginTransaction().run {
                    when(step){
                        MainViewModel.FragmentStep.EDIT -> replace(R.id.fragment_container_view,EditFragment(),"edit_fragment")
                        MainViewModel.FragmentStep.LOG -> replace(R.id.fragment_container_view,LogListFragment(),"log_list_fragment")
                    }
                    commitNowAllowingStateLoss()
                }
            }
        }
    }
}