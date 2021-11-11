package de.adue.workoutplanner.ui.workoutplan

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WorkoutPlanViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is workout plan fragment"
    }
    val text: LiveData<String> = _text
}