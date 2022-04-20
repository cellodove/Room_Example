package com.peter.room_example.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    enum class FragmentStep {
        EDIT, LOG
    }

    val liveFragmentStep = MutableLiveData(FragmentStep.EDIT)
}