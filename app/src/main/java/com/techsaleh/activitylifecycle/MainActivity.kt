package com.techsaleh.activitylifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.scrollview_example)
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