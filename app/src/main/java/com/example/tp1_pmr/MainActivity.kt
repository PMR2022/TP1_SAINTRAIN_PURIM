package com.example.tp1_pmr

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson


class MainActivity : AppCompatActivity(), View.OnClickListener {

    var refBtnOK: Button? = null
    var refEdtPseudo: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        refEdtPseudo = findViewById(R.id.pseudo)

        refBtnOK = findViewById(R.id.btnOK)
        refBtnOK?.setOnClickListener(this)

        // Alguns testes das classes
        var itemzinhos: MutableList<ItemTD> = mutableListOf(ItemTD("Correr"), ItemTD("Andar", true), ItemTD("Pular"));
        var listinha = ListTD("Exercicios");
        println(listinha);
        listinha.setItems(itemzinhos);
        // Checking if copied correctly
        itemzinhos = mutableListOf();
        println(listinha);
        println(listinha.search("Andar"));
        var pessoa = Profile("Andreis");
        println(pessoa);
        pessoa.addList(listinha);
        println(pessoa);

        // Para usar o GSON: não esqueça de importar ali em cima,
        // E também de de verificar se o build.gradle possui a linha:
        // implementation 'com.google.code.gson:gson:2.9.0'
        // Cria um key-value de GSON que depois irá ser usado pelo sharedPreference

        // Para criar alguma coisa
        val gson = Gson();
        val login = Profile("blabla");
        val loginJson = gson.toJson(login);
        // ai no chared preferences você precisa fazer um
        // var bla = sharedPreferences.edit()
        // bla.putString("$name",login)
        // bla.commit()
        // Ai na parte de você buscar na lista de ativdades ou na parte de mostrar, tu faz:
        // val preferencias = getSharedPreferences("logins" ou oq tu chamou, 0)
        // val loginJson = preferencias.getString("$login","nome_alguma coisa")
        // val gson = Gson();
        // E aqui é a hora que tu vai puxar do JSON pelo nome que é, transformando o profile novamente em uma classe)
        // val login: Profile = gson.fromJson(loginJson, Profile::class.java)
        // E ai tu é feliz :)

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