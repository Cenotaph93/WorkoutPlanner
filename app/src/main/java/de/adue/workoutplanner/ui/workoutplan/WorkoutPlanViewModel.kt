package de.adue.workoutplanner.ui.workoutplan

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import de.adue.workoutplanner.data.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WorkoutPlanViewModel(application: Application) : AndroidViewModel(application) {

    val workoutPlans: LiveData<List<WorkoutPlan>> =
        WorkoutPlanDatabase.getInstance(application).workoutDao().getWorkoutPlans()
    val exercises: LiveData<List<Exercise>> =
        WorkoutPlanDatabase.getInstance(application).workoutDao().getExercises()
    val executedExercises: LiveData<List<ExecutedExercise>> =
        WorkoutPlanDatabase.getInstance(application).workoutDao().getExecutedExercises()
    val splitsWithExercises: LiveData<List<SplitWithExercises>> =
        WorkoutPlanDatabase.getInstance(application).workoutDao().getSplitsWithExercises()
    val executedExerciseWithSets: LiveData<List<ExecutedExerciseWithSets>> =
        WorkoutPlanDatabase.getInstance(application).workoutDao().getExecutedExercisesWithSets()

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

    fun addExecutedExercise(exerciseId: Int, date: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            WorkoutPlanDatabase.getInstance(getApplication())
                .workoutDao()
                .insertExecutedExercise(ExecutedExercise(exerciseId = exerciseId, date = date))
        }
    }

    fun addSet(executedExerciseId: Int, reps: Int, weight: Double) {
        viewModelScope.launch(Dispatchers.IO) {
            WorkoutPlanDatabase.getInstance(getApplication())
                .workoutDao()
                .insertSet(
                    ExecutedSet(
                        executionId = executedExerciseId,
                        reps = reps,
                        weight = weight
                    )
                )
        }
    }
}