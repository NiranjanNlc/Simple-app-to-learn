package com.example.myapplication.Service

import android.content.ContentValues.TAG
import android.util.Log
import com.example.myapplication.modal.Destination
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.FirebaseFirestore


class DbService
{
    var db = FirebaseFirestore.getInstance()
    // Write a message to the database
    val myRef = FirebaseDatabase.getInstance().getReference("destination");
    fun loadData(): MutableList<Destination> {
        var destList = mutableListOf<Destination>()
        // Read from the database
        // Read from the database
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) { // This method is called once with the initial value and again
            // whenever data at this location is updated.
//                for (document in dataSnapshot) {
//                    Log.d(TAG, document.id + " => " + document.data)
//                    val destination = document.toObject(Destination::class.java)
//                    destList.add(destination)
//                }
                val value =
                    dataSnapshot.getValue(String::class.java)!!
                Log.d(TAG, "Value is: $value")
            }
            override fun onCancelled(error: DatabaseError) { // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException())
            }
        })

        val dataSnapshot= db.collection("destination")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    for (document in task.result!!) {
                        Log.d(TAG, document.id + " => " + document.data)
                        val destination = document.toObject(Destination::class.java)
                        destList.add(destination)
                    }
                } else {
                    Log.w(TAG, "Error getting documents.", task.exception)
                }
            }

        return destList;
    }

    fun addData( destination: Destination)
    {
// Add a new document with a generated ID
        // Add a new document with a generated ID
        db.collection("destination")
            .add(destination)
            .addOnSuccessListener {
            }
            .addOnFailureListener { e -> Log.w(TAG, "Error adding document", e) }
    }

    fun addData(name: String, ward: String,desc: String)
    {
         myRef.setValue(Destination(name,ward,desc)).addOnCompleteListener{}
             .addOnFailureListener{}
    }

}