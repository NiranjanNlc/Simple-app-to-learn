package com.example.myapplication.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.myapplication.R
import com.example.myapplication.Service.DbService
import com.example.myapplication.modal.Destination
import kotlinx.android.synthetic.main.activity_destiny_create.*


class DestinationCreateActivity : AppCompatActivity() {

    var  dbService = DbService()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_destiny_create)

       // setSupportActionBar(toolbar as Toolbar?)
        val context = this

        // Show the Up button in the action bar.
       // supportActionBar?.setDisplayHomeAsUpEnabled(true)
        var name = findViewById<TextView>(R.id.et_city)
        var  ward =findViewById<TextView>(R.id.et_country)
        var desc =findViewById<TextView>(R.id.et_description)
        var btn_add = findViewById<Button>(R.id.btn_add)
        btn_add.setOnClickListener {
        //    Toast.makeText(this,"Loading data",Toast.LENGTH_SHORT)
            val id = Math.random().toString()
            dbService.addData( name.toString(),ward.toString(),desc.toString())
            val intent = Intent(this, DestinationListActivity::class.java)
          //  Toast.makeText(this,"Loading data",Toast.LENGTH_LONG)
            startActivity(intent)
        }
    }
}
