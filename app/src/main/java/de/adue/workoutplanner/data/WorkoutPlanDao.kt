package de.adue.workoutplanner.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
abstract class WorkoutPlanDao {
    @Insert
    abstract fun insertWorkoutPlan(workoutPlan: WorkoutPlan)

    @Query("SELECT * FROM workoutplan")
    abstract fun getWorkoutPlans(): LiveData<List<WorkoutPlan>>

    @Query("SELECT * FROM exercise")
    abstract fun getExercises(): LiveData<List<Exercise>>

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

    @Query("SELECT * FROM Split "+
            "JOIN SplitExercisesCrossRef ON Split.splitId = SplitExercisesCrossRef.splitId "+
            "JOIN Exercise ON SplitExercisesCrossRef.exerciseId = Exercise.exerciseId"
    )
    abstract fun getSplitsWithExercises(): LiveData<List<SplitWithExercises>>

}