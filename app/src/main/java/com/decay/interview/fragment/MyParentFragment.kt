package com.decay.interview.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.decay.interview.fragment.EditNameDialogFragment.EditNameDialogListener

class MyParentFragment : Fragment(), EditNameDialogListener {
    // Call this method to launch the edit dialog
    private fun showEditDialog() {
        val fm = fragmentManager
        val editNameDialogFragment: EditNameDialogFragment = EditNameDialogFragment.newInstance("Some Title")
        // SETS the target fragment for use later when sending results
        editNameDialogFragment.setTargetFragment(this@MyParentFragment, 300)
        editNameDialogFragment.show(fm!!, "fragment_edit_name")
    }

    // This is called when the dialog is completed and the results have been passed
    override fun onFinishEditDialog(inputText: String?) {
        Toast.makeText(requireContext(), "Hi, $inputText", Toast.LENGTH_SHORT).show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showEditDialog()
    }
}