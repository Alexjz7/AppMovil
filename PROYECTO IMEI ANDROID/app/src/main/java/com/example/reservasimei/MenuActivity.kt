package com.example.reservasimei

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.reservasimei.util.PreferenceHelper
import com.example.reservasimei.util.PreferenceHelper.set

class MenuActivity : AppCompatActivity() {
    private var userId: Int = -1
    private lateinit var db:DataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        userId = intent.getIntExtra("userId", -1)

        val nombresYApellidosTextView = findViewById<TextView>(R.id.nombresYApellidos)
        val db = DataBase(this)
        val userName = db.retornarNombre(userId)
        if (userName != null) {
            nombresYApellidosTextView.text = "$userName"
        }

        val btnReservarCampo = findViewById<Button>(R.id.btnReservarCampo)
        btnReservarCampo.setOnClickListener {
            reservarCampo()
        }

        val btnListaReserva = findViewById<Button>(R.id.listaReservas)
        btnListaReserva.setOnClickListener {
            listaReserva()
        }

        val btnCerrar = findViewById<Button>(R.id.btnCerrarSesion)
        btnCerrar.setOnClickListener {
            cerrarSesion()
        }
    }

    private fun reservarCampo() {
        val i = Intent(this, ReservaCampoActivity::class.java)

        i.putExtra("userId", userId)

        startActivity(i)
    }

    private fun listaReserva() {
        val i = Intent(this, ListaReservasActivity::class.java)

        i.putExtra("userId", userId)

        startActivity(i)
    }

    private fun cerrarSesion() {
        val parametro = PreferenceHelper.defaultPrefs(this)
        parametro["session"] = false
        regresarLogin()
    }

    private fun regresarLogin() {
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
        finish()
    }
}
