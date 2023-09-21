package com.example.threerapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class Help : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help)

        val backBtn3: ImageButton = findViewById(R.id.GobackBtn3)

//        backBtn3.setOnClickListener {
//            val intent = Intent(this, ActivityFragment::class.java)
//            startActivity(intent)
//        }
    }
}