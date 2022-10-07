package com.example.recyclerview_example

import android.app.Dialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.widget.SearchView
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
    lateinit var tsumcat: MutableList<String>
    lateinit var textsearchview: TextView
    lateinit var dialog:Dialog


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




        val recyclerView: RecyclerView = findViewById(R.id.recyclerview)

        val layoutManager = LinearLayoutManager(applicationContext)


        //searchview part
        var editText:EditText = findViewById(R.id.tsumtextsearch)
        editText.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                tsumnames = mutableListOf()
                tsumlimit = mutableListOf()
                tsumdescription = mutableListOf()
                tsumcat = mutableListOf()

                tsumMatrix.forEach {
                    tsumnames.add(it[0])
                    tsumlimit.add(it[1])
                    tsumdescription.add(it[2])
                    tsumcat.add(it[3])}
                customAdapter = CustomAdapter(tsumnames,tsumlimit,tsumdescription,context)
                recyclerView.layoutManager = layoutManager
                recyclerView.adapter = customAdapter
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                tsumnames = mutableListOf()
                tsumlimit = mutableListOf()
                tsumdescription = mutableListOf()
                tsumMatrix.forEachIndexed { index, s ->
                    if (p0?.let{s1 -> s[3].contains(s1)}==true){
                        tsumnames.add(s[0])
                        tsumlimit.add(s[1])
                        tsumdescription.add(s[2])

                    }
                    customAdapter = CustomAdapter(tsumnames,tsumlimit,tsumdescription,context)
                    recyclerView.adapter = customAdapter
                }


            }

            override fun afterTextChanged(p0: Editable?) {
                print("already done it")

            }

        })


    }


  }