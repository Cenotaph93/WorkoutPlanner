package de.adue.workoutplanner.ui.workoutplan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import de.adue.workoutplanner.databinding.FragmentWorkoutPlanBinding

class WorkoutPlanFragment : Fragment() {

    private lateinit var workoutPlanViewModel: WorkoutPlanViewModel
    private var _binding: FragmentWorkoutPlanBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        workoutPlanViewModel =
            ViewModelProvider(this).get(WorkoutPlanViewModel::class.java)

        _binding = FragmentWorkoutPlanBinding.inflate(inflater, container, false)
        val root: View? = binding?.root

        val textView: TextView? = _binding?.textHome
        workoutPlanViewModel.text.observe(viewLifecycleOwner, {
            textView?.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}