package com.example.testingtoast

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.input.input

class MainActivity : AppCompatActivity() {

    lateinit var buttonLaunchDialog: Button
    lateinit var mainTextViewName: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonLaunchDialog = findViewById(R.id.buttonLaunchDialog)
        mainTextViewName = findViewById(R.id.mainTextViewName)

        buttonLaunchDialog.setOnClickListener {
            showDialog()
        }
    }

    @SuppressLint("CheckResult")
    private fun showDialog(){
        MaterialDialog(this)
            .show {
                input (
                    waitForPositiveButton = true,
                    allowEmpty = false
                ){ dialog, name ->
                    setNameToTextView(name.toString())
                    showToast(buildToastMessage(name.toString()))
                }
                title(R.string.text_enter_name)
                positiveButton(R.string.text_ok)
            }
    }

    private fun setNameToTextView(name: String){
        mainTextViewName.text = name
    }

    private fun showToast(message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    companion object{
        fun buildToastMessage(name: String): String{
            return "Your name is $name."
        }
    }
}