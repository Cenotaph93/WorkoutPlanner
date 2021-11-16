package de.adue.workoutplanner.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
abstract class WorkoutPlanDao {
    @Insert
    abstract fun insertWorkoutPlan(workoutPlan: WorkoutPlan)

    // TODO: This will require more logic to delete the relationship between workout plans, exercises and so on
    @Delete
    abstract fun delete(workoutPlan: WorkoutPlan)

    @Query("SELECT * FROM workoutplan")
    abstract fun getWorkoutPlans(): LiveData<List<WorkoutPlan>>

    @Query("SELECT * FROM exercise")
    abstract fun getExercises(): LiveData<List<Exercise>>

    @Transaction
    @Query("SELECT * FROM workoutplan")
    abstract fun getWorkoutPlansWithExercises(): List<WorkoutPlanWithSplitsAndExercises>

    @Transaction
    open fun insertSplitWithExercises(workoutPlanId: Int, name: String, exercises: List<Exercise>) {
        insertSplit(workoutPlanId, name)
        val splitId = getLatestSplitId()
        exercises.forEach {
            insertSplitExercises(splitId, it.exerciseId)
        }
    }

    @Query("INSERT INTO split (workoutPlanId, name) VALUES (:workoutPlanId, :name)")
    abstract fun insertSplit(workoutPlanId: Int, name: String)

    @Query("SELECT MAX(splitId) from split")
    abstract fun getLatestSplitId(): Int

    @Insert
    abstract fun insertExercise(exercise: Exercise)

    @Query("INSERT INTO SplitExercisesCrossRef (splitId, exerciseId) VALUES (:splitId, :exerciseId)")
    abstract fun insertSplitExercises(splitId: Int, exerciseId: Int)
}