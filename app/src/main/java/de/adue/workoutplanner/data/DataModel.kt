package de.adue.workoutplanner.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

/**
 * Represents a workout plan consisting of 1 to n exercises.
 */
@Entity
data class WorkoutPlan(
    @PrimaryKey val workoutId: Int,
    val name: String
)

/**
 * Represents a single exercise that can be part of a workout plan.
 */
@Entity
data class Exercise(
    @PrimaryKey val exerciseId: Int,
    val name: String,
    val isBodyweight: Boolean,
)

/**
 * Links exercises to a workout plan in a many-to-many relationship.
 * A workout can contain multiple exercises
 * and an exercise can be part of multiple workout plans (i.e. warm-up exercises)
 */
@Entity(primaryKeys = ["workoutPlanId", "exerciseId"])
data class WorkoutPlanExerciseCrossRef(
    val workoutPlanId: Int,
    val exerciseId: Int
)

/**
 * Represents a subset of exercises in a workout plan.
 */
@Entity
data class Split(
    @PrimaryKey val splitId: Int,
    val name: String
)

/**
 * Links Splits to Exercises, which have a many-to-many relationship.
 * An exercise can be part of multiple Splits (i.e. Cardio at the end of every split)
 * and a split can contain multiple exercises.
 */
@Entity(primaryKeys = ["splitId", "exerciseId"])
data class ExerciseSplitCrossRef(
    val splitId: Int,
    val exerciseId: Int
)

/**
 * Represents a single set of an executed exercise.
 */
@Entity
data class Set(
    @PrimaryKey val setId: Int,
    val executionId: Int,
    val reps: Int,
    val weight: Float
)

/**
 * Represents a single executed exercise on a given date.
 */
@Entity
data class ExecutedExercise(
    @PrimaryKey val executedExerciseId: Int,
    val exerciseId: Int,
    val date: Long
)

/**
 * Links executed exercises to sets in a one-to-many relationship.
 * A set can only belong to one executed exercise, an executed exercise can contain multiple sets.
 */
data class ExecutedExerciseWithSets(
    @Embedded val executedExercise: ExecutedExercise,
    @Relation(
        parentColumn = "executedExerciseId",
        entityColumn = "executionId"
    )
    val playlists: List<Set>
)
