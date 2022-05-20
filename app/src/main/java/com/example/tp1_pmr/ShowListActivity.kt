package com.example.tp1_pmr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ShowListActivity : AppCompatActivity(), View.OnClickListener {
    val dataSet = mutableListOf("Item One","Item Two","Item Three")

    var list : RecyclerView? = null
    var refBtnOK: Button? = null
    var refEdtNewItem: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_list)

        list = findViewById(R.id.list)
        list?.adapter = ItemAdapter(dataSet)
        list?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        refEdtNewItem = findViewById(R.id.newItem)
        refBtnOK = findViewById(R.id.btnOK)
        refBtnOK?.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id){
            R.id.btnOK -> {
                dataSet.add(refEdtNewItem?.text.toString())
                list?.adapter?.notifyItemInserted(dataSet.size-1)
            }
        }
    }
}