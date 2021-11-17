package de.adue.workoutplanner.ui.workoutplan

import android.app.Application
import androidx.lifecycle.*
import de.adue.workoutplanner.data.Exercise
import de.adue.workoutplanner.data.SplitWithExercises
import de.adue.workoutplanner.data.WorkoutPlan
import de.adue.workoutplanner.data.WorkoutPlanDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WorkoutPlanViewModel(application: Application) : AndroidViewModel(application) {

    val workoutPlans: LiveData<List<WorkoutPlan>> = WorkoutPlanDatabase.getInstance(application).workoutDao().getWorkoutPlans()
    val exercises: LiveData<List<Exercise>> = WorkoutPlanDatabase.getInstance(application).workoutDao().getExercises()
    val splitsWithExercises: LiveData<List<SplitWithExercises>> = WorkoutPlanDatabase.getInstance(application).workoutDao().getSplitsWithExercises()

    fun insertPlan(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            WorkoutPlanDatabase.getInstance(getApplication())
                .workoutDao()
                .insertWorkoutPlan(WorkoutPlan(name = name))
        }
    }

    fun insertExercise(name: String, isBodyweight: Boolean = false) {
        viewModelScope.launch(Dispatchers.IO) {
            WorkoutPlanDatabase.getInstance(getApplication())
                .workoutDao()
                .insertExercise(Exercise(name = name, isBodyweight = isBodyweight))
        }
    }

    fun insertExercise(exercise: Exercise) {
        viewModelScope.launch(Dispatchers.IO) {
            WorkoutPlanDatabase.getInstance(getApplication())
                .workoutDao()
                .insertExercise(exercise)
        }
    }

    fun addSplitsWithExercises(name: String, exercises: List<Exercise>) {
        viewModelScope.launch(Dispatchers.IO) {
            WorkoutPlanDatabase.getInstance(getApplication())
                .workoutDao()
                .insertSplitWithExercises(0, name, exercises)
        }
    }
}