package com.decay.interview.fragment

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment

internal class MyAlertDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val title: String? = requireArguments().getString("title")
        val alertDialogBuilder: AlertDialog.Builder = AlertDialog.Builder(activity)
        alertDialogBuilder.setTitle(title)
        alertDialogBuilder.setMessage("Are you sure?")
        alertDialogBuilder.setPositiveButton("OK") { dialog, which ->
            // on success
            Toast.makeText(context, title, Toast.LENGTH_SHORT).show()
        }
        alertDialogBuilder.setNegativeButton("Cancel") { dialog, which ->
            dialog?.dismiss()
        }
        return alertDialogBuilder.create()
    }

    companion object {
        fun newInstance(title: String?): MyAlertDialogFragment {
            val frag = MyAlertDialogFragment()
            val args = Bundle()
            args.putString("title", title)
            frag.arguments = args
            return frag
        }
    }
}