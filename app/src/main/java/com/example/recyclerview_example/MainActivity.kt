package com.example.recyclerview_example

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.BaseExpandableListAdapter
import android.widget.EditText
import android.widget.ListAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tsumfilter.CustomAdapter
import java.io.InputStream

class MainActivity : AppCompatActivity() {
    private lateinit var customAdapter: CustomAdapter
    lateinit var listAdapter: ArrayAdapter<String>
    lateinit var context: Context
    lateinit var tsumMatrixList: MutableList<List<String>>
    lateinit var tsumnames: MutableList<String>
    lateinit var tsumlimit: MutableList<String>
    lateinit var tsumdescription: MutableList<String>
    lateinit var tsumcat: MutableList<String>


    fun refreshMatrix(){

    }

    fun getTsumMatrix(): MutableList<List<String>>{
        val inputStream: InputStream = assets.open("finaltsummatrix.txt")
        val lineList = mutableListOf<String>()
        val newlineList = mutableListOf<List<String>>()

        inputStream.bufferedReader().forEachLine { lineList.add(it) }
        lineList.forEach{newlineList.add(it.split(";"))}
        return newlineList

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        //optionslist
        val arrayList:ArrayList<String> = arrayListOf()
        arrayList.add("Bursty Skilly")
        val dialog = Dialog(this)

        dialog.setContentView(R.layout.spinner_options)
        dialog.window?.setLayout(650,800)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
        val listView:ListView = dialog.findViewById(R.id.list_view)
        listAdapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList)
        listView.adapter = listAdapter
        listView.setOnItemClickListener { adapterView, view, i, l -> print("whaaat")
            tsumMatrixList.forEachIndexed { index, strings ->  }}

        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "TsumFilter"
        // context for Customadapter resource fetching
        context = this.baseContext

        tsumMatrixList = getTsumMatrix()
        val recyclerView: RecyclerView = findViewById(R.id.recyclerview)
        val layoutManager = LinearLayoutManager(applicationContext)
        val tsumMatrixClass = TsumMatrixClass(tsumMatrixList)

        //optionslist
        val arrayList:ArrayList<String> = arrayListOf()
        arrayList.add("Bursty Skilly")

        //searchview part
        var editText:EditText = findViewById(R.id.tsumtextsearch)
        editText.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                filtercat.add("Center Burst Skill")
                customAdapter = CustomAdapter(tsumMatrixClass.gettsumnames(),tsumMatrixClass.gettsumlimit(),tsumMatrixClass.gettsumdescription(),context)
                recyclerView.layoutManager = layoutManager
                recyclerView.adapter = customAdapter
            }

            val filtercat = mutableListOf<String>()

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                tsumnames = mutableListOf()
                tsumlimit = mutableListOf()
                tsumdescription = mutableListOf()

                tsumMatrixList.forEachIndexed { index, s ->
                    if (p0?.let { s[3].contains(it) } == true && filtercat.all { term ->s[3].any { word-> word.contains(term) } } ){
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


