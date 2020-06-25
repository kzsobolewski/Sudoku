package com.kzsobolewski.sudoku.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.kzsobolewski.sudoku.R
import com.kzsobolewski.sudoku.databinding.FragmentGameplayBinding
import com.kzsobolewski.sudoku.main.viewModels.GameplayViewModel
import com.kzsobolewski.sudoku.main.views.GameBoardView
import com.kzsobolewski.sudoku.main.views.Position
import kotlinx.android.synthetic.main.fragment_gameplay.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class GameplayFragment : Fragment(), GameBoardView.OnClickListener {

    private val viewModel by viewModel<GameplayViewModel>()
    private lateinit var binding: FragmentGameplayBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGameplayBinding
            .inflate(inflater, container, false).apply {
                lifecycleOwner = viewLifecycleOwner
                gameBoardView.registerListener(this@GameplayFragment)
            }
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.board.observe(viewLifecycleOwner) {
            binding.gameBoardView.updateBoard(it)
        }

        validateButton.setOnClickListener {
            if (viewModel.validateBoard())
                findNavController().navigate(R.id.action_gameplayFragment_to_gameFinishedDialogFragment)
            else
                Toast.makeText(
                    requireContext(),
                    "Sudoku is not filled properly",
                    Toast.LENGTH_SHORT
                ).show()
        }
    }

    override fun onClick(position: Position) {
        viewModel.updatePosition(position)
    }

}