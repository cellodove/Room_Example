package com.peter.room_example.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.peter.room_example.R
import com.peter.room_example.ui.loglist.LogListFragment

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
                        MainViewModel.FragmentStep.LOG -> replace(R.id.fragment_container_view, LogListFragment(),"log_list_fragment")
                    }
                    addToBackStack(null)
                    commit()
                }
            }
        }
    }

    override fun onBackPressed() {
        val fm = supportFragmentManager
        if (fm.backStackEntryCount == 1) {
            finishAffinity()
            return
        }
        super.onBackPressed()
    }
}