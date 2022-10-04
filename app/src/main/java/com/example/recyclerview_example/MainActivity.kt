package com.example.recyclerview_example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tsumfilter.CustomAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var customAdapter: CustomAdapter




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "RecyclerView example"
        var cwdstring = assets.list("tsumImages")

        val recyclerView: RecyclerView = findViewById(R.id.recyclerview)
        customAdapter = CustomAdapter(cwdstring)
        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = customAdapter
    }


  }