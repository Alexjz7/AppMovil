package com.example.reservasimei

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ListaReservasActivity : AppCompatActivity() {
    private var userId: Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_reservas)
        userId = intent.getIntExtra("userId", -1)

        val listadoTextView = findViewById<TextView>(R.id.listado)

        val db = DataBase(this)

        val reservas = db.buscarReservas(userId)

        val listadoReservas = StringBuilder()

        for (reserva in reservas) {
            listadoReservas.append("ID: ${reserva.id}\n")
            listadoReservas.append("Horas: ${reserva.horas}\n")
            listadoReservas.append("Fecha de Reserva: ${reserva.fechaReserva}\n")
            listadoReservas.append("Hora de Reserva: ${reserva.horaReserva}\n")
            listadoReservas.append("Pago: ${reserva.pago}\n")
            listadoReservas.append("Nombre de Campo: ${reserva.nombreCampo}\n")
            listadoReservas.append("Tipo de Campo: ${reserva.tipoCampo}\n")
            listadoReservas.append("Precio por Hora: ${reserva.precioHora}\n")
            listadoReservas.append("Total: ${reserva.total}\n")
            listadoReservas.append("\n") // Separador entre reservas
        }

        listadoTextView.text = listadoReservas.toString()
    }
}