package com.example.recyclerview_example

class TsumMatrixClass(matrixlist:MutableList<List<String>>) {


    lateinit var tsumnames: MutableList<String>
    lateinit var tsumlimit: MutableList<String>
    lateinit var tsumdescription: MutableList<String>
    lateinit var tsumcat: MutableList<String>

    init {
        if ( !this::tsumnames.isInitialized){
            tsumnames = mutableListOf()
            tsumlimit = mutableListOf()
            tsumdescription = mutableListOf()
            tsumcat = mutableListOf()
            matrixlist.forEach { tsumnames.add(it[0])
                tsumlimit.add(it[1])
                tsumdescription.add(it[2])
                tsumcat.add(it[3])}
        }

    }
    fun gettsumnames(): MutableList<String> {
        return(tsumnames)
    }
    fun gettsumlimit(): MutableList<String> {
        return(tsumlimit)
    }
    fun gettsumdescription(): MutableList<String> {
        return(tsumdescription)
    }
    fun gettsumcat(): MutableList<String> {
        return(tsumcat)
    }

}