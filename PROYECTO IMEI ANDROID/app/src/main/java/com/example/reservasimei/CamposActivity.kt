package com.example.reservasimei

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView


class CamposActivity : AppCompatActivity() {
    private var userId: Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_campos)

        userId = intent.getIntExtra("userId", -1)
        val nombreCampo = intent.getStringExtra("NombreCampo")

        val campoNombreTextView = findViewById<TextView>(R.id.nombreCampoDeport)
        campoNombreTextView.text = "$nombreCampo"

        val btnReservarCampo=findViewById<androidx.cardview.widget.CardView>(R.id.btnCampo1)
        btnReservarCampo.setOnClickListener {
            reservar("$nombreCampo","Futbol")
        }
        val btnReservarCampo2=findViewById<androidx.cardview.widget.CardView>(R.id.btnCampo2)
        btnReservarCampo2.setOnClickListener {
            reservar("$nombreCampo","Basquet")
        }
    }
    private fun reservar(nombreCampo:String,campo:String){
        val i= Intent(this,ReservaActivity::class.java)
        i.putExtra("userId", userId)
        i.putExtra("NombreCampo",nombreCampo)
        i.putExtra("Campo",campo)
        startActivity(i)
    }
}