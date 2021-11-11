package de.adue.workoutplanner.ui.workoutplan.ui

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class WorkoutPlansAdapter(
    private val context: Context,
    private val itemClickListener: ItemSelectedListener
) :
    RecyclerView.Adapter<WorkoutPlansViewHolder>() {

    var workoutPlans: List<Any> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutPlansViewHolder {
        // TODO
        return WorkoutPlansViewHolder(parent.rootView)
    }

    override fun onBindViewHolder(holder: WorkoutPlansViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return workoutPlans.size
    }
}

class WorkoutPlansViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

}