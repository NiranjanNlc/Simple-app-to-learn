package com.example.myapplication.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.DestAdapter
import com.example.myapplication.R
import com.example.myapplication.Service.DbService
import kotlinx.android.synthetic.main.activity_destiny_list.*


class DestinationListActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_destiny_list)

		//setSupportActionBar(toolbar as Toolbar?)

		fab.setOnClickListener {
			val intent = Intent(this@DestinationListActivity, DestinationCreateActivity::class.java)
			Toast.makeText(this,"Loading data",Toast.LENGTH_LONG)
			startActivity(intent)
		}
	}

	override fun onResume() {
		super.onResume()
		loadDestinations()
	}

	private fun loadDestinations()
	{
		var dbService :DbService = DbService()
		val recyclerView = findViewById(R.id.destiny_recycler_view) as RecyclerView
		recyclerView.layoutManager= LinearLayoutManager(this, RecyclerView.VERTICAL,false)
		val  userAdapter =  DestAdapter(dbService.loadData().toTypedArray())
		recyclerView.adapter=userAdapter
    }
}









