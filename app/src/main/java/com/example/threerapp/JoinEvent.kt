package com.example.threerapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase

class JoinEvent : AppCompatActivity() {
    data class JoinEvent(
        val name: String,
        val email: String,
        val phone: String
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join_event)

        val JEtName: EditText = findViewById(R.id.joinEtname)
        val JEtEmail: EditText = findViewById(R.id.joinEtemail)
        val JEtPhone: EditText = findViewById(R.id.joinEtphone)
        val JoinButton: Button = findViewById(R.id.JoinBtn)
        val backBtn2: ImageButton = findViewById(R.id.GobackBtn2)

//        backBtn2.setOnClickListener {
//            val intent = Intent(this, ActivityFragment::class.java)
//            startActivity(intent)
//        }

        JoinButton.setOnClickListener {
            val name = JEtName.text.toString()
            val email = JEtEmail.text.toString()
            val phone = JEtPhone.text.toString()

            if (name.isEmpty() || email.isEmpty() || phone.isEmpty()){
                if (name.isEmpty()) {
                    JEtName.error = "Please enter your name"
                }
                if (email.isEmpty()) {
                    JEtEmail.error = "Please enter your email"
                }
                if (phone.isEmpty()) {
                    JEtPhone.error = "Please enter your phone number"
                }
            } else {
                val database = FirebaseDatabase.getInstance()
                val etJoin = database.getReference("JoinEvent1")

                val joining = JoinEvent(name, email, phone)

                etJoin.child(name).setValue(joining).addOnCompleteListener { task->
                    if (task.isSuccessful){
                        Toast.makeText(applicationContext, "Join event successfully", Toast.LENGTH_LONG).show()
                        JEtName.text.clear()
                        JEtEmail.text.clear()
                        JEtPhone.text.clear()
                    } else {
                        Toast.makeText(applicationContext, "Failed to Join the event", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}