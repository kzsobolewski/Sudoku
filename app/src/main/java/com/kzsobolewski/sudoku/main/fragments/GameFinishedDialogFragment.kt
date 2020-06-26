package com.kzsobolewski.sudoku.main.fragments

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.kzsobolewski.sudoku.R

class GameFinishedDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setMessage(R.string.game_finished_dialog_text)
                .setPositiveButton(R.string.exit_dialog_button) { _, _ ->
                    findNavController()
                        .navigate(R.id.action_gameFinishedDialogFragment_to_menuFragment)
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}