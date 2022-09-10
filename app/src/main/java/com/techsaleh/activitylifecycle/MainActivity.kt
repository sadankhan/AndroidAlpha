package com.techsaleh.activitylifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity(){

    private lateinit var etMobileNumber: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var txtForgotPassword: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        title = "Log In"

        etMobileNumber = findViewById(R.id.etMobileNumber)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)
        txtForgotPassword = findViewById(R.id.txtForgotPassword)

        btnLogin.setOnClickListener{
            Toast.makeText(this@MainActivity, "Login Clicked",
                Toast.LENGTH_SHORT).show()
        }
    }

    override fun onStart() {
        super.onStart()
        println("On Start is Called")
    }

    override fun onResume() {
        super.onResume()
        println("On Resume is Called")
    }

    override fun onPause() {
        super.onPause()
        println("On Pause is Called")
    }

    override fun onStop() {
        super.onStop()
        println("On Stop is Called")
    }

    override fun onRestart() {
        super.onRestart()
        println("On Restart is Called")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("On Destroy is Called")
    }
}