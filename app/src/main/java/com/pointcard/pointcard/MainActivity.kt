package com.pointcard.pointcard

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()

        val buttonSignUp = findViewById<Button>(R.id.SignUpButton)
        val buttonLogin = findViewById<Button>(R.id.LoginButton)

        buttonSignUp.setOnClickListener {
            var isValid = true

            val emailEditText = findViewById<EditText>(R.id.emailEditText)
            val emailText = emailEditText.text.toString()

            if (emailText.isEmpty()) {
                emailEditText.error = "E-mailが未入力です"
                isValid = false
            }

            val passEditText = findViewById<EditText>(R.id.passEditText)
            val passText = passEditText.text.toString()

            if (passText.isEmpty()) {
                passEditText.error = "passwordが未入力です"
                isValid = false
            }

            if(isValid) {
                // [START create_user_with_email]
                auth.createUserWithEmailAndPassword(emailText, passText)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            val user = auth.currentUser
                            val intent = Intent(this, UserActivity::class.java)
                            startActivity(intent)
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(baseContext, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                        }

                    }
                // [END create_user_with_email]

            }
        }

        buttonLogin.setOnClickListener {
            //妥当かどうか
            var isValid = true

            val emailEditText = findViewById<EditText>(R.id.emailEditText)
            val emailText = emailEditText.text.toString()

            if (emailText.isEmpty()) {
                emailEditText.error = "E-mailが未入力です"
                isValid = false
            }

            val passEditText = findViewById<EditText>(R.id.passEditText)
            val passText = passEditText.text.toString()

            if (passText.isEmpty()) {
                passEditText.error = "passwordが未入力です"
                isValid = false
            }

            if(isValid) {
                auth.signInWithEmailAndPassword(emailText, passText)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            val user = auth.currentUser
                            val intent = Intent(this, UserActivity::class.java)
                            startActivity(intent)
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(baseContext, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                        }

                    }

            }
        }
    }

}
