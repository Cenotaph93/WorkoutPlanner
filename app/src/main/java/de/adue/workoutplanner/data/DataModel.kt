package de.adue.workoutplanner.data

import androidx.room.*

/**
 * Represents a workout plan consisting of 1 to n exercises.
 */
@Entity
data class WorkoutPlan(
    @PrimaryKey(autoGenerate = true)
    val workoutId: Int = 0,
    val name: String
)

/**
 * Represents a subset of exercises in a workout plan.
 */
@Entity
data class Split(
    @PrimaryKey(autoGenerate = true)
    val splitId: Int = 0,
    val workoutPlanId: Int,
    val name: String
)

/**
 * Links splits to a workout plan in a one-to-many relationship.
 * A workout can contain multiple splits
 * but a split can only be part of one workout plan.
 */
data class WorkoutPlanWithSplits(
    @Embedded val workoutPlan: WorkoutPlan,
    @Relation(
        parentColumn = "workoutId",
        entityColumn = "workoutPlanId"
    )
    val splits: List<Split>
)

data class WorkoutPlanWithSplitsAndExercises(
    @Embedded val workoutPlan: WorkoutPlan,
    @Relation(
        entity = Split::class,
        parentColumn = "workoutId",
        entityColumn = "splitId"
    )
    val splitsWithExercises: List<SplitWithExercises>
)

/**
 * Represents a single exercise.
 */
@Entity
data class Exercise(
    @PrimaryKey(autoGenerate = true)
    val exerciseId: Int = 0,
    val name: String,
    val isBodyweight: Boolean,
)

/**
 * Cross reference between splits and exercises.
 */
@Entity(primaryKeys = ["splitId", "exerciseId"])
data class SplitExercisesCrossRef(
    val splitId: Int,
    val exerciseId: Int
)

/**
 * Links splits to exercises in a many-to-many relationship.
 * A split can contain multiple exercises and an exercise can be part of multiple splits.
 */
data class SplitWithExercises(
    @Embedded val split: Split,
    @Relation(
        parentColumn = "splitId",
        entityColumn = "exerciseId",
        associateBy = Junction(SplitExercisesCrossRef::class)
    )
    val exercises: List<Exercise>
)

/**
 * Represents a single set of an executed exercise.
 */
@Entity
data class ExecutedSet(
    @PrimaryKey(autoGenerate = true)
    val setId: Int = 0,
    val executionId: Int,
    val reps: Int,
    val weight: Double
)

/**
 * Represents a single executed exercise on a given date.
 */
@Entity
data class ExecutedExercise(
    @PrimaryKey(autoGenerate = true)
    val executedExerciseId: Int = 0,
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
    val executedSets: List<ExecutedSet>
)

data class ExecutedExerciseWithExercisesAndSets(
    @Embedded val exercise: Exercise,
    @Relation(
        entity = Split::class,
        parentColumn = "workoutId",
        entityColumn = "splitId"
    )
    val splitsWithExercises: List<SplitWithExercises>
)
