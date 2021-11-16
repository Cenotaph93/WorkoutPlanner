package de.adue.workoutplanner.ui.workoutplan.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import de.adue.workoutplanner.data.Exercise
import de.adue.workoutplanner.databinding.FragmentWorkoutPlanBinding
import de.adue.workoutplanner.ui.workoutplan.WorkoutPlanViewModel

class WorkoutPlanFragment : Fragment() {

    private lateinit var workoutPlanViewModel: WorkoutPlanViewModel
    private var _binding: FragmentWorkoutPlanBinding? = null

    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        workoutPlanViewModel =
            ViewModelProvider(this).get(WorkoutPlanViewModel::class.java)

        _binding = FragmentWorkoutPlanBinding.inflate(inflater, container, false)

        /*
        _binding?.recyclerviewPlans?.apply {

        }
        */

        _binding?.buttonAddPlan?.setOnClickListener {
            _binding?.inputTestTable?.text?.toString()?.let {
                workoutPlanViewModel.insertPlan(it)
            }
        }
        listOf (
            Exercise(name = "Squats", isBodyweight = false),
            Exercise(name = "Leg curls", isBodyweight = false),
            Exercise(name = "Beinbeuger", isBodyweight = false),
            Exercise(name = "Beinstrecker", isBodyweight = false),
            Exercise(name = "Bench press", isBodyweight = false),
            Exercise(name = "High row", isBodyweight = false),
            Exercise(name = "Lat", isBodyweight = false),
            Exercise(name = "Shoulder press", isBodyweight = false)
        ).forEach {
            workoutPlanViewModel.insertExercise(it)
        }

        workoutPlanViewModel.exercises.observe(viewLifecycleOwner) {
            var columns = ""
            it.forEach { plan ->
                columns += plan.name + " (${plan.exerciseId})\n"
            }
            _binding?.textTestTable?.text = columns
        }

        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

interface ItemSelectedListener {
    fun onItemSelected(item: Any)
}