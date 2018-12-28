package com.pointcard.pointcard

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.zxing.integration.android.IntentIntegrator
import android.util.Log
import android.widget.LinearLayout


class UserActivity : AppCompatActivity() {


    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser

        val database = FirebaseDatabase.getInstance()

        if (user != null){
            val uid = user.uid

            val myRef = database.getReference(uid)
            val layout = findViewById<LinearLayout>(R.id.linear)

            myRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {

                    for (h in dataSnapshot.children){
                        val card = h.getValue(Int::class.java)
                        val button = Button(this@UserActivity)
                        button.text = h.key.toString()
                        layout.addView(button)
                        Log.d("Data",card.toString())
                        button.setOnClickListener {
                            val intent = Intent(this@UserActivity, CardActivity::class.java)
                            intent.putExtra("card",h.key.toString())
                            startActivity(intent)
                        }

                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value
                    Log.w("onCancelled", "error:", error.toException())
                }
            })


            val buttonQR = findViewById<Button>(R.id.QRButton)

            buttonQR.setOnClickListener {

                val qr = IntentIntegrator(this)
                //qr.initiateScan()

                myRef.child("card1").setValue(1)
                myRef.child("card2").setValue(2)
                myRef.child("card3").setValue(3)
                myRef.child("card4").setValue(4)
                Log.d("TAG", uid)
            }
        }
    }
}
