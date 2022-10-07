package com.example.recyclerview_example

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tsumfilter.CustomAdapter
import java.io.InputStream

class MainActivity : AppCompatActivity() {
    private lateinit var customAdapter: CustomAdapter
    lateinit var context: Context
    lateinit var tsumnames: MutableList<String>
    lateinit var tsumlimit: MutableList<String>
    lateinit var tsumdescription: MutableList<String>


    fun getTsumMatrix(): MutableList<List<String>>{
        val inputStream: InputStream = assets.open("finaltsummatrix.txt")
        val lineList = mutableListOf<String>()
        val newlineList = mutableListOf<List<String>>()

        inputStream.bufferedReader().forEachLine { lineList.add(it) }
        lineList.forEach{newlineList.add(it.split(";"))}
        return newlineList

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "RecyclerView example"
        context = this.baseContext
        val tsumMatrix: MutableList<List<String>> = getTsumMatrix()
        tsumnames = mutableListOf()
        tsumlimit = mutableListOf()
        tsumdescription = mutableListOf()

        tsumMatrix.forEach {
            tsumnames.add(it[0])
            tsumlimit.add(it[1])
            tsumdescription.add(it[2])}

        customAdapter = CustomAdapter(tsumnames,tsumlimit,tsumdescription,context)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerview)

        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = customAdapter
    }


  }