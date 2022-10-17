package com.intern.happyfood.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RelativeLayout
import androidx.appcompat.widget.Toolbar
import com.intern.happyfood.R

@Suppress("DEPRECATION")
class RegisterActivity : AppCompatActivity() {
    private lateinit var toolbar: Toolbar
    private lateinit var btnRegister: Button
    private lateinit var etName: EditText
    private lateinit var etPhoneNumber: EditText
    private lateinit var etPassword: EditText
    private lateinit var etEmail: EditText
    private lateinit var etAddress: EditText
    private lateinit var etConfirmPassword: EditText
    private lateinit var rlRegister: RelativeLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Register Yourself"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        rlRegister = findViewById(R.id.rlRegister)
        etName = findViewById(R.id.etName)
        etPhoneNumber = findViewById(R.id.etPhoneNumber)
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        etConfirmPassword = findViewById(R.id.etConfirmPassword)
        etAddress = findViewById(R.id.etAddress)
        btnRegister = findViewById(R.id.btnRegister)

        btnRegister.setOnClickListener {
            val intent = Intent(/* packageContext = */ this@RegisterActivity, /* cls = */
                MainActivity::class.java)
            val bundle = Bundle()
            bundle.putString("data", "register")
            bundle.putString(/* key = */ "name", /* value = */ etName.text.toString())
            bundle.putString(/* key = */ "mobile", /* value = */ etPhoneNumber.text.toString())
            bundle.putString(/* key = */ "password", /* value = */ etPassword.text.toString())
            bundle.putString(/* key = */ "address", /* value = */ etAddress.text.toString())
            intent.putExtra("details", bundle)
            startActivity(intent)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}