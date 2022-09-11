package com.intern.foodrunner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RelativeLayout
import androidx.appcompat.widget.Toolbar

class RegisterActivity : AppCompatActivity() {

    /*Declaring the different variables used for this activity*/
    private lateinit var toolbar: Toolbar
    private lateinit var btnRegister: Button
    private lateinit var etName: EditText
    private lateinit var etPhoneNumber: EditText
    private lateinit var etPassword: EditText
    private lateinit var etEmail: EditText
    private lateinit var etAddress: EditText
    private lateinit var etConfirmPassword: EditText
    private lateinit var rlRegister: RelativeLayout

    /*Life-cycle method of the activity*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*Linking the view*/
        setContentView(R.layout.activity_register)

        /*Toolbar
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Register Yourself"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)*/

        /*Initialising the views with the ones defined in the XML*/
        rlRegister = findViewById(R.id.rlRegister)
        etName = findViewById(R.id.etName)
        etPhoneNumber = findViewById(R.id.etPhoneNumber)
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        etConfirmPassword = findViewById(R.id.etConfirmPassword)
        etAddress = findViewById(R.id.etAddress)
        btnRegister = findViewById(R.id.btnRegister)

        /*Handling the click on the button using the setOnClickListener method*/
        btnRegister.setOnClickListener {

            /*Declaring the intent which sets up the route for the navigation of the activity*/
            val intent = Intent(this@RegisterActivity, DashboardActivity::class.java)

            /*Declaring the bundle object which will carry the data*/
            val bundle = Bundle()

            /*Setting a value data which is activity specific. This will be used to identify from where the data was sent*/
            bundle.putString("data", "register")

            /*Putting the values in Bundle*/
            bundle.putString("name", etName.text.toString())
            bundle.putString("mobile", etPhoneNumber.text.toString())
            bundle.putString("email", etEmail.text.toString())
            bundle.putString("password", etPassword.text.toString())
            bundle.putString("address", etAddress.text.toString())

            /*Putting the Bundle to be shipped with the intent*/
            intent.putExtra("details", bundle)

            /*Starting the new activity by sending the intent in the startActivity method*/
            startActivity(intent)
        }
    }

    /*Setting up the navigation on toolbar
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }*/
}