package com.example.reservasimei

import android.widget.Toast
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import com.example.reservasimei.Clases.Reservaciones

class ReservaActivity : AppCompatActivity()  {
    private var userId: Int = -1
    private lateinit var db:DataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reserva)

        userId = intent.getIntExtra("userId", -1)
        val nombreCampo = intent.getStringExtra("NombreCampo")
        val campo = intent.getStringExtra("Campo")

        val nmrHora= findViewById<EditText>(R.id.etNmrHoras)
        val fecha= findViewById<EditText>(R.id.etfecha)
        val hora= findViewById<EditText>(R.id.ethora)
        val spinner = findViewById<Spinner>(R.id.spiner1)

        val mediosDePago = resources.getStringArray(R.array.mediosDePago)
        val adaptador = ArrayAdapter(this, android.R.layout.simple_spinner_item, mediosDePago)
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adaptador

        val btnVistaReserva=findViewById<Button>(R.id.btnReservar)
        btnVistaReserva.setOnClickListener {
            if (nmrHora.text.toString().isNotEmpty() && fecha.text.toString().isNotEmpty() && hora.text.toString().isNotEmpty()) {

                val reserva = Reservaciones(
                    userId,
                    nmrHora.text.toString().toInt(),
                    fecha.text.toString(),
                    hora.text.toString(),
                    spinner.selectedItem.toString(),
                    "$nombreCampo",
                    "$campo",
                    5.0,
                    nmrHora.text.toString().toInt() * 5.0
                )

                val db = DataBase(this)
                val exito = db.insertReservacion(
                    reserva.userId,
                    reserva.horas,
                    reserva.fechaReserva,
                    reserva.horaReserva,
                    reserva.pago,
                    reserva.nombreCampo,
                    reserva.tipoCampo,
                    reserva.precioHora
                )

                if (exito) {
                    Toast.makeText(this, "Reserva exitosa", Toast.LENGTH_SHORT).show()
                    reservar()
                } else {
                    Toast.makeText(this, "Error al hacer la reserva", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun reservar(){
        val i= Intent(this,ListaReservasActivity::class.java)
        i.putExtra("userId", userId)
        startActivity(i)
    }
}