package de.adue.workoutplanner.ui.workoutplan

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.adue.workoutplanner.data.WorkoutPlan
import de.adue.workoutplanner.data.WorkoutPlanDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WorkoutPlanViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is workout plan fragment"
    }
    val text: LiveData<String> = _text

    fun insertPlan(context: Context, name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            WorkoutPlanDatabase.getInstance(context)
                .workoutDao()
                .insert(WorkoutPlan(name = name))
        }
    }
}