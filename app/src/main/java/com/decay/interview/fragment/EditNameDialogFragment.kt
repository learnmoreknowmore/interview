package com.decay.interview.fragment

import android.os.Bundle
import android.view.*
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.fragment.app.DialogFragment
import com.decay.interview.R


class EditNameDialogFragment : DialogFragment(), TextView.OnEditorActionListener {
    private var mEditText: EditText? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_edit_name, container)
    }

    // 1. Defines the listener interface with a method passing back data result.
    interface EditNameDialogListener {
        fun onFinishEditDialog(inputText: String?)
    }
    override fun onViewCreated(view: View, @Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Get field from view
        mEditText = view.findViewById(R.id.txt_your_name)

        // Fetch arguments from bundle and set title
        val title = arguments?.getString("title", "Enter Name")
        dialog?.setTitle(title)

        // Show soft keyboard automatically and request focus to field
        mEditText?.requestFocus()
        dialog?.window?.setSoftInputMode(
            WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE
        )
        // 2. Setup a callback when the "Done" button is pressed on keyboard
        mEditText?.setOnEditorActionListener(this)
    }

    companion object {
        fun newInstance(title: String?): EditNameDialogFragment {
            val frag = EditNameDialogFragment()
            val args = Bundle()
            args.putString("title", title)
            frag.arguments = args
            return frag
        }
    }

    override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
        if (EditorInfo.IME_ACTION_DONE == actionId) {
            // Return input text back to activity through the implemented listener
            val listener = activity as EditNameDialogListener?
            listener!!.onFinishEditDialog(mEditText!!.text.toString())
            // Close the dialog and return back to the parent activity
            dismiss()
            return true
        }

        return false
    }
}