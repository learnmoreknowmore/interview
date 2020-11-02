package com.decay.interview.fragment

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.decay.interview.R

//demo from https://guides.codepath.com/android/Using-DialogFragment
class DialogDemoActivity : AppCompatActivity(), EditNameDialogFragment.EditNameDialogListener {
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog_demo)
        showMyParentFragment()
        //showAlertDialog()
    }

    private fun showMyParentFragment() {
        val fm = supportFragmentManager
        val fragmentTransaction = fm.beginTransaction()
        var myFragment = MyParentFragment()
        fragmentTransaction.add(myFragment, "myParentFragment")
        fragmentTransaction.show(myFragment).commit()

    }

    private fun showAlertDialog() {
        val fm = supportFragmentManager
        val alertDialog = MyAlertDialogFragment.newInstance("Some title")
        alertDialog.show(fm, "fragment_alert")
    }

    // 3. This method is invoked in the activity when the listener is triggered
    // Access the data result passed to the activity here
    override fun onFinishEditDialog(inputText: String?) {
        Toast.makeText(this, "Hi, $inputText", Toast.LENGTH_SHORT).show();
    }
}