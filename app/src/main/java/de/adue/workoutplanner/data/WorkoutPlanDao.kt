package de.adue.workoutplanner.data

import androidx.room.*

@Dao
abstract class WorkoutPlanDao {
    @Insert
    abstract fun insert(workoutPlan: WorkoutPlan)

    // TODO: This will require more logic to delete the relationship between workout plans, exercises and so on
    @Delete
    abstract fun delete(workoutPlan: WorkoutPlan)

    @Query("SELECT * FROM workoutplan")
    abstract fun getAll(): List<WorkoutPlan>

    @Transaction
    @Query("SELECT * FROM workoutplan")
    abstract fun getWorkoutPlansWithExercises(): List<WorkoutPlanWithSplitsAndExercises>

    @Transaction
    open fun addSplitWithExercises(workoutPlanId: Int, name: String, exercises: List<Exercise>) {
        insertSplit(workoutPlanId, name)
    }

    @Query("INSERT INTO split (workoutPlanId, name) VALUES (:workoutPlanId, :name)")
    abstract fun insertSplit(workoutPlanId: Int, name: String)
}