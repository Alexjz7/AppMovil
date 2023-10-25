package com.example.reservasimei

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ReservaCampoActivity : AppCompatActivity() {
    private var userId: Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reserva_campo)

        userId = intent.getIntExtra("userId", -1)

        val btnCampo=findViewById<androidx.cardview.widget.CardView>(R.id.btnCampo1)
        btnCampo.setOnClickListener {
            var nombreCampo=findViewById<TextView>(R.id.nombre1).text.toString()
            Campo(nombreCampo)
        }

        val btnCampo2=findViewById<androidx.cardview.widget.CardView>(R.id.btnCampo2)
        btnCampo2.setOnClickListener {
            var nombreCampo=findViewById<TextView>(R.id.nombre2).text.toString()
            Campo(nombreCampo)
        }

        val btnCampo3=findViewById<androidx.cardview.widget.CardView>(R.id.btnCampo3)
        btnCampo3.setOnClickListener {
            var nombreCampo=findViewById<TextView>(R.id.nombre3).text.toString()
            Campo(nombreCampo)
        }

        val btnCampo4=findViewById<androidx.cardview.widget.CardView>(R.id.btnCampo4)
        btnCampo4.setOnClickListener {
            var nombreCampo=findViewById<TextView>(R.id.nombre4).text.toString()
            Campo(nombreCampo)
        }
    }
    private fun Campo(nombreCampo:String){
        val i= Intent(this, CamposActivity::class.java)
        i.putExtra("userId", userId)
        i.putExtra("NombreCampo",nombreCampo)
        startActivity(i)
    }
}