package com.pointcard.pointcard

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase


class UserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("point")

        val point = findViewById<TextView>(R.id.point)

        // Read from the database
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = dataSnapshot.getValue(String::class.java)
                point.text = value.toString()

            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
            }
        })

        val buttonQR = findViewById<Button>(R.id.QRButton)

        buttonQR.setOnClickListener {
            val intent = Intent(this, QRActivity::class.java)
            startActivity(intent)
        }
    }
}
