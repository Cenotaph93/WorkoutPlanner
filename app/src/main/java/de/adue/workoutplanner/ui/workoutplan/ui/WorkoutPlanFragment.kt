package de.adue.workoutplanner.ui.workoutplan.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
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

        _binding?.recyclerviewPlans?.apply {

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