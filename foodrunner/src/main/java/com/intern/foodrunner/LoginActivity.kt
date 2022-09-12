package com.intern.foodrunner

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class LoginActivity : AppCompatActivity() {

    /*Declaring the different variables used for this activity*/
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var registerYourself: TextView
    private lateinit var login: Button
    private lateinit var etMobileNumber: EditText
    private lateinit var etPassword: EditText
    private lateinit var txtForgotPassword: TextView

    /*Life-cycle method of the activity*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*Linking the view*/
        setContentView(R.layout.activity_login)

        /*Implementing SharedPrefrences*/
        sharedPreferences = getSharedPreferences(getString(R.string.preference_file_name), Context.MODE_PRIVATE)

        val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)

        if (isLoggedIn) {
            val intent = Intent(this@LoginActivity, DashboardActivity::class.java)
            startActivity(intent)
            finish()
        }

        title = "Log In"

        /*Initialising the views with the ones defined in the XML*/
        etMobileNumber = findViewById(R.id.etMobileNumber)
        etPassword = findViewById(R.id.etPassword)
        txtForgotPassword = findViewById(R.id.txtForgotPassword)
        registerYourself = findViewById(R.id.txtRegisterYourself)
        login = findViewById(R.id.btnLogin)

        /*Handling the clicks using the setOnClickListener method*/
        txtForgotPassword.setOnClickListener {
            startActivity(Intent(this@LoginActivity, ForgotPasswordActivity::class.java))
        }
        registerYourself.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }
        login.setOnClickListener {

            /*Declaring Value*/
            var s = "Dashboard"
            savePreferences(s)

            /*Declaring the intent which sets up the route for the navigation of the activity*/
            val intent = Intent(this@LoginActivity, DashboardActivity::class.java)

            /*Declaring the bundle object which will carry the data
            * You can send the data inside intents
            *
            * We specifically used Bundle just to demonstrate a new technique*/
            val bundle = Bundle()

            /*Setting a value data which is activity specific. This will be used to identify from where the data was sent*/
            bundle.putString("data", "login")

            /*Putting the values in Bundle*/
            bundle.putString("mobile", etMobileNumber.text.toString())
            bundle.putString("password", etPassword.text.toString())

            /*Putting the Bundle to be shipped with the intent*/
            intent.putExtra("details", bundle)

            /*Starting the new activity by sending the intent in the startActivity method*/
            startActivity(intent)
        }
    }
    /* When user clicks back button this activity need to be finished. */
    override fun onPause() {
        super.onPause()
        finish()
    }

    private fun savePreferences(title: String) {
        sharedPreferences.edit().putBoolean("isLoggedIn", true).apply()
        sharedPreferences.edit().putString("title", title).apply()
    }
}