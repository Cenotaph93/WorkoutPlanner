package de.adue.workoutplanner.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction

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

    @Query(
        "SELECT * FROM Split " +
                "JOIN SplitExercisesCrossRef ON Split.splitId = SplitExercisesCrossRef.splitId " +
                "JOIN Exercise ON Exercise.exerciseId = SplitExercisesCrossRef.exerciseId " +
                "GROUP BY Split.splitId"
    )
    abstract fun getSplitsWithExercises(): LiveData<List<SplitWithExercises>>

    @Insert
    abstract fun insertExecutedExercise(executedExercise: ExecutedExercise)

    @Query("SELECT * FROM ExecutedExercise")
    abstract fun getExecutedExercises(): LiveData<List<ExecutedExercise>>

    @Insert
    abstract fun insertSet(executedSet: ExecutedSet)

    @Query(
        "SELECT * FROM ExecutedExercise JOIN ExecutedSet " +
                "ON ExecutedExercise.executedExerciseId = ExecutedSet.executionId " +
                "GROUP BY ExecutedExercise.executedExerciseId"
    )
    abstract fun getExecutedExercisesWithSets(): LiveData<List<ExecutedExerciseWithSets>>
}