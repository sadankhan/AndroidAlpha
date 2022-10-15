package com.techsaleh.activitylifecycle

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView

class MessageActivity : AppCompatActivity() {

    private lateinit var txtMessage: TextView
    private var message = "Custom Message"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message)

        txtMessage = findViewById(R.id.txtMessage)

        if (intent != null) {
            message = intent.getStringExtra("Message").toString()
            txtMessage.text = message
        }
    }
}
