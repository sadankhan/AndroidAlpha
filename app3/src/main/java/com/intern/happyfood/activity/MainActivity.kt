package com.intern.happyfood.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.intern.happyfood.R

class MainActivity : AppCompatActivity() {

    /*Life-cycle method*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}