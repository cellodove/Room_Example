package com.peter.room_example.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.peter.room_example.R

class MainActivity : AppCompatActivity() {
    val viewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModelObserver()
    }




    fun viewModelObserver(){
        viewModel.liveFragmentStep.observe(this){ step ->
            if (!isDestroyed){
                supportFragmentManager.beginTransaction().run {
                    when(step){
                        MainViewModel.FragmentStep.EDIT -> {}
                        MainViewModel.FragmentStep.LOG -> {}
                    }
                }
            }
        }
    }
}