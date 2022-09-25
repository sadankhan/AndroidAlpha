package com.intern.happyfood.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.widget.Toolbar
import com.intern.happyfood.R

class ForgotPasswordActivity : AppCompatActivity() {
    /*Declaring the different variables used for this activity*/
    private lateinit var toolbar: Toolbar
    private lateinit var etForgotMobile: EditText
    private lateinit var etForgotEmail: EditText
    private lateinit var btnForgotNext: Button

    /*Life-cycle method of the activity*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*Linking the view*/
        setContentView(R.layout.activity_forgot_password)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Reset Password"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)



        /*Initialising the views with the ones defined in the XML*/
        etForgotMobile = findViewById(R.id.etForgotMobile)
        etForgotEmail = findViewById(R.id.etForgotEmail)
        btnForgotNext = findViewById(R.id.btnForgotNext)

        /*Handling the click on the button using the setOnClickListener method*/
        btnForgotNext.setOnClickListener {

            /*Declaring the intent which sets up the route for the navigation of the activity*/
            val intent = Intent(this@ForgotPasswordActivity, ValidationActivity::class.java)

            /*Starting the new activity by sending the intent in the startActivity method*/
            startActivity(intent)
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}