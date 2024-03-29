package com.techsaleh.activitylifecycle

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText

class AvengersActivity : AppCompatActivity() {

    private var titleName = "The Avengers"
    private lateinit var etMessage: EditText
    private lateinit var btnSendMessage: Button
    private lateinit var btnLogout: Button
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPreferences = getSharedPreferences(getString(R.string.preference_file_name), Context.MODE_PRIVATE)

        setContentView(R.layout.activity_avengers)

        titleName = sharedPreferences.getString("title", "The Avengers") as String

        title = titleName

        etMessage = findViewById(R.id.etMessage)
        btnSendMessage = findViewById(R.id.btnSendMessage)


        btnLogout = findViewById(R.id.btnLogout)

        btnSendMessage.setOnClickListener {
            val intent = Intent(this@AvengersActivity, MessageActivity::class.java)
            val message = etMessage.text.toString()
            intent.putExtra("Message", message)
            startActivity(intent)
        }

        /*Log out from the application. Remember to clear the shared preferences when you log out of the app*/
        btnLogout.setOnClickListener {
            startActivity(Intent(this@AvengersActivity, LoginActivity::class.java))
            sharedPreferences.edit().clear().apply()
            finish()
        }
    }
}
