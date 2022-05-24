package com.example.tp1_pmr

class ItemTD(private var desc: String = "",private var done: Boolean = false) {
    constructor(desc: String) : this(desc, false)
    fun setDesc(newDesc: String){
        desc = newDesc;
    }
    fun getDesc(): String{
        return desc;
    }
    fun setDone(newDone: Boolean){
        done = newDone;
    }
    fun getDone(): Boolean{
        return done;
    }
    override fun toString(): String{
        return "- "+desc+": ["+ (if(done) "x" else " ") + "]";
    }
}