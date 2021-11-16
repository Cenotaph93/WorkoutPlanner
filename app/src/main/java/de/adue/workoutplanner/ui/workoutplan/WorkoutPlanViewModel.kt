package de.adue.workoutplanner.ui.workoutplan

import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import de.adue.workoutplanner.data.WorkoutPlan
import de.adue.workoutplanner.data.WorkoutPlanDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WorkoutPlanViewModel(application: Application) : AndroidViewModel(application) {

    val workoutPlans: LiveData<List<WorkoutPlan>> = WorkoutPlanDatabase.getInstance(application).workoutDao().getAll()

    fun insertPlan(context: Context, name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            WorkoutPlanDatabase.getInstance(context)
                .workoutDao()
                .insert(WorkoutPlan(name = name))
        }
    }
}