package de.adue.workoutplanner.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
abstract class WorkoutPlanDao {
    @Insert
    abstract fun insert(workoutPlan: WorkoutPlan)

    // TODO: This will require more logic to delete the relationship between workout plans, exercises and so on
    @Delete
    abstract fun delete(workoutPlan: WorkoutPlan)

    @Query("SELECT * FROM workoutplan")
    abstract fun getAll(): LiveData<List<WorkoutPlan>>

    @Transaction
    @Query("SELECT * FROM workoutplan")
    abstract fun getWorkoutPlansWithExercises(): List<WorkoutPlanWithSplitsAndExercises>

    @Transaction
    open fun addSplitWithExercises(workoutPlanId: Int, name: String, exercises: List<Exercise>) {
        insertSplit(workoutPlanId, name)
        exercises.forEach {
            insertSplitExercises(0, it.exerciseId) // TODO get splitId from previously inserted split
        }
    }

    @Query("INSERT INTO split (workoutPlanId, name) VALUES (:workoutPlanId, :name)")
    abstract fun insertSplit(workoutPlanId: Int, name: String)

    @Insert
    abstract fun insertExercise()

    @Query("INSERT INTO SplitExercisesCrossRef (splitId, exerciseId) VALUES (:splitId, :exerciseId)")
    abstract fun insertSplitExercises(splitId: Int, exerciseId: Int)
}