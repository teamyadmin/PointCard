package com.pointcard.pointcard

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class CardActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card)

        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser

        val card = intent.getStringExtra("card")
        val database = FirebaseDatabase.getInstance()

        if (user != null) {
            val uid = user.uid
            val myRef = database.getReference(uid)
            val myRefchild = myRef.child(card)
            val point = findViewById<TextView>(R.id.textViewValue)
            Log.d("INFO", card.toString())


            myRefchild.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val p = dataSnapshot.getValue(Int::class.java)
                    point.text = p.toString()

                }
                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value
                    Log.w("onCancelled", "error:", error.toException())
                }
            })

        }
    }
}
