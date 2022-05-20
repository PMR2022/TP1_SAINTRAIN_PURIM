package com.example.tp1_pmr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ChoixListActivity : AppCompatActivity(), View.OnClickListener {
    val dataSet = mutableListOf("Liste 1","Liste 2","Liste 3")

    var list : RecyclerView? = null
    var refBtnOK: Button? = null
    var refEdtNewList: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choix_list)

        list = findViewById(R.id.list)
        list?.adapter = ListAdapter(dataSet)
        list?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        refEdtNewList = findViewById(R.id.newList)
        refBtnOK = findViewById(R.id.btnOK)
        refBtnOK?.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id){
            R.id.btnOK -> {
                dataSet.add(refEdtNewList?.text.toString())
                list?.adapter?.notifyItemInserted(dataSet.size-1)
            }
        }
    }
}