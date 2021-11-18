package de.adue.workoutplanner.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [WorkoutPlan::class, Split::class, Exercise::class,
        SplitExercisesCrossRef::class, ExecutedSet::class, ExecutedExercise::class], version = 1
)
abstract class WorkoutPlanDatabase : RoomDatabase() {
    abstract fun workoutDao(): WorkoutPlanDao

    companion object {
        @Volatile
        private var instance: WorkoutPlanDatabase? = null

        fun getInstance(context: Context): WorkoutPlanDatabase {
            return instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context,
                    WorkoutPlanDatabase::class.java, "workoutplan-db"
                ).build().also { instance = it }
            }
        }
    }
}
