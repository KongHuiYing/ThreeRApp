package com.example.threerapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
//import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.database.FirebaseDatabase
//import com.google.firebase.ktx.Firebase

class SuggestEvent : AppCompatActivity() {
    data class UserSuggest(
        val name: String,
        val email: String,
        val phone: String,
        val detail: String
    )

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_suggest_event)

        val etName: EditText = findViewById(R.id.SEnameET)
        val etEmail: EditText = findViewById(R.id.SEemailET)
        val etPhone: EditText = findViewById(R.id.SEphoneET)
        val etDetail: EditText = findViewById(R.id.SEdetailET)
        val suggestBtn: Button = findViewById(R.id.SuggestBtn)
        val backBtn1: ImageButton = findViewById(R.id.GobackBtn1)

//        backBtn1.setOnClickListener {
//            val intent = Intent(this, ActivityFragment::class.java)
//            startActivity(intent)
//        }

        suggestBtn.setOnClickListener {
            val name = etName.text.toString()
            val email = etEmail.text.toString()
            val phone = etPhone.text.toString()
            val detail = etDetail.text.toString()

            if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || detail.isEmpty()){
                if (name.isEmpty()) {
                    etName.error = "Please enter your name"
                }
                if (email.isEmpty()) {
                    etEmail.error = "Please enter your email"
                }
                if (phone.isEmpty()) {
                    etPhone.error = "Please enter your phone number"
                }
                if (detail.isEmpty()) {
                    etDetail.error = "Please enter the details"
                }
            } else {
                val database = FirebaseDatabase.getInstance()
                val etSuggest = database.getReference("UserSuggest")


                val suggestion = UserSuggest(name, email, phone, detail)

                etSuggest.child(name).setValue(suggestion).addOnCompleteListener { task->
                    if (task.isSuccessful){
                        Toast.makeText(applicationContext, "Suggestion saved successfully", Toast.LENGTH_LONG).show()
                        etName.text.clear()
                        etEmail.text.clear()
                        etPhone.text.clear()
                        etDetail.text.clear()
                    } else {
                        Toast.makeText(applicationContext, "Failed to save suggestion", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }

    }
}