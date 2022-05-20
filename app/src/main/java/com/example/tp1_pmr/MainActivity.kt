package com.example.tp1_pmr

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity(), View.OnClickListener {

    var refBtnOK: Button? = null
    var refEdtPseudo: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        refEdtPseudo = findViewById(R.id.pseudo)

        refBtnOK = findViewById(R.id.btnOK)
        refBtnOK?.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when(view.id) {
            R.id.btnOK -> {
                val bundle = Bundle().apply {
                    putString("pseudo", refEdtPseudo?.text.toString());
                }
                val choixListIntent = Intent(this,ChoixListActivity::class.java).apply {
                    putExtras(bundle)
                }
                startActivity(choixListIntent)
            }
        }
    }
}