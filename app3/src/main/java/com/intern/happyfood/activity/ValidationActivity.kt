package com.intern.happyfood.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.intern.happyfood.R

class ValidationActivity : AppCompatActivity() {
    private lateinit var toolbar: Toolbar
    private lateinit var btnReset: Button
    private lateinit var etOTP: EditText
    private lateinit var etNewPassword: EditText
    private lateinit var etConfirmNewPassword: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_validation)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Reset Password"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        toolbar = findViewById(R.id.toolbar)
        etOTP = findViewById(R.id.etOTP)
        etNewPassword = findViewById(R.id.etPassword)
        etConfirmNewPassword = findViewById(R.id.etConfirmPassword)
        btnReset = findViewById(R.id.btnReset)

        /*Fetching the details from the intent*/
        val details = intent.getBundleExtra("details")


        btnReset.setOnClickListener {

            /*Declaring the intent which sets up the route for the navigation of the activity*/
            val intent = Intent(this@ValidationActivity, LoginActivity::class.java)

            /*Declaring the bundle object which will carry the data*/
            val bundle = Bundle()
            bundle.putString("data", "reset")
            /*Putting the values in Bundle*/
            bundle.putString("otp", etOTP.text.toString())
            bundle.putString("password", etNewPassword.text.toString())

            /*Putting the Bundle to be shipped with the intent*/
            intent.putExtra("details", bundle)
            startActivity(intent)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        finish()
    }

    override fun onStop() {
        super.onStop()
        Toast.makeText(application, "Password Reset Successfully!", Toast.LENGTH_SHORT).show()
    }
}
