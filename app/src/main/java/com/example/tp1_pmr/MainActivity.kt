package com.example.tp1_pmr

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var sharedPreferences: SharedPreferences? = null
    private var refBtnOK: Button? = null
    private var refEdtPseudo: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = getSharedPreferences("Profiles",0)

        refEdtPseudo = findViewById(R.id.pseudo)

        refBtnOK = findViewById(R.id.btnOK)
        refBtnOK?.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when(view.id) {
            R.id.btnOK -> {
                // The string typed by the user
                val pseudo = refEdtPseudo?.text.toString()

                // Creates a new profile if necessary
                if (sharedPreferences?.contains(pseudo) == false) {
                    val profile = Profile(pseudo)
                    val profileGson = Gson().toJson(profile)
                    sharedPreferences?.edit()?.apply() {
                        putString(pseudo, profileGson)
                        apply()
                    }
                }

                // Bundles the pseudo and start choixListActivity
                val bundle = Bundle().apply {
                    putString("pseudo", pseudo)
                }
                val choixListIntent = Intent(this,ChoixListActivity::class.java).apply {
                    putExtras(bundle)
                }
                startActivity(choixListIntent)
            }
        }
    }
}