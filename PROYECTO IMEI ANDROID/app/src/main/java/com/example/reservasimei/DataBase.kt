package com.example.reservasimei

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.reservasimei.Clases.Reservaciones

class DataBase(context: Context) : SQLiteOpenHelper(context, "UserData", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("create table UserData(user_id INTEGER PRIMARY KEY AUTOINCREMENT,username TEXT, email TEXT, password TEXT)")
        db?.execSQL("create table Reservaciones(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "user_id INTEGER, " +
                "horas INTEGER, " +
                "fechaReserva TEXT, " +
                "horaReserva TEXT, " +
                "pago TEXT, " +
                "nombreCampo TEXT, " +
                "tipoCampo TEXT, " +
                "precioHora REAL)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("drop table if exists UserData")
        db?.execSQL("drop table if exists Reservations")
    }

    fun insertData(username: String, email: String, password: String): Boolean {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put("username", username)
        cv.put("email", email)
        cv.put("password", password)
        val result = db.insert("UserData", null, cv)
        if (result == -1L) {
            return false
        }
        return true
    }

    fun comprobarLogin(email: String, password: String): Int {
        val db = this.readableDatabase
        val query = "select user_id from UserData where email='$email' and password='$password'"
        val cursor = db.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            val idIndex=cursor.getColumnIndex("user_id")
            val userId = cursor.getInt(idIndex)
            cursor.close()
            return userId
        }

        cursor.close()
        return -1
    }

    fun insertReservacion(userId: Int, horas: Int, fechaReserva: String, horaReserva: String, pago: String, nombreCampo: String, tipoCampo: String, precioHora: Double): Boolean {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put("user_id", userId)
        cv.put("horas", horas)
        cv.put("fechaReserva", fechaReserva)
        cv.put("horaReserva", horaReserva)
        cv.put("pago", pago)
        cv.put("nombreCampo", nombreCampo)
        cv.put("tipoCampo", tipoCampo)
        cv.put("precioHora", precioHora)

        val result = db.insert("Reservaciones", null, cv)
        return result != -1L
    }

    fun mostrarReservas(userId: Int): MutableList<Reservaciones> {
        var list: MutableList<Reservaciones> =ArrayList()

        val db = this.readableDatabase
        val query = "SELECT * FROM Reservaciones WHERE user_id = $userId"
        val result = db.rawQuery(query, null)

        if (result.moveToFirst()) {
            val idIndex=result.getColumnIndex("id")
            val horasIndex=result.getColumnIndex("horas")
            val fechaReservaIndex=result.getColumnIndex("fechaReserva")
            val horaReservaIndex=result.getColumnIndex("horaReserva")
            val pagoIndex=result.getColumnIndex("pago")
            val nombreCampoIndex=result.getColumnIndex("nombreCampo")
            val tipoIndex=result.getColumnIndex("tipoCampo")
            val precioHoraIndex=result.getColumnIndex("precioHora")
            do {
                var r=Reservaciones()
                r.id = result.getInt(idIndex)
                r.horas = result.getInt(horasIndex)
                r.fechaReserva = result.getString(fechaReservaIndex)
                r.horaReserva = result.getString(horaReservaIndex)
                r.pago = result.getString(pagoIndex)
                r.nombreCampo = result.getString(nombreCampoIndex)
                r.tipoCampo = result.getString(tipoIndex)
                r.precioHora = result.getDouble(precioHoraIndex)
                list.add(r)
            } while (result.moveToNext())
        }

        result.close()
        db.close()
        return list
    }
    fun retornarNombre(userId: Int): String? {
        val db = this.readableDatabase
        val query = "SELECT username FROM UserData WHERE user_id = $userId"
        val cursor = db.rawQuery(query, null)

        var userName: String? = null

        if (cursor.moveToFirst()) {
            val userNameIndex = cursor.getColumnIndex("username")
            userName = cursor.getString(userNameIndex)
        }

        cursor.close()
        db.close()

        return userName
    }
    fun buscarReservas(userId: Int): List<Reservaciones> {
        val reservas = ArrayList<Reservaciones>()

        val db = this.readableDatabase
        val query = "SELECT * FROM Reservaciones WHERE user_id = $userId"
        val cursor = db.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            val idIndex = cursor.getColumnIndex("id")
            val horasIndex = cursor.getColumnIndex("horas")
            val fechaReservaIndex = cursor.getColumnIndex("fechaReserva")
            val horaReservaIndex = cursor.getColumnIndex("horaReserva")
            val pagoIndex = cursor.getColumnIndex("pago")
            val nombreCampoIndex = cursor.getColumnIndex("nombreCampo")
            val tipoCampoIndex = cursor.getColumnIndex("tipoCampo")
            val precioHoraIndex = cursor.getColumnIndex("precioHora")

            do {
                val reserva = Reservaciones()
                reserva.id = cursor.getInt(idIndex)
                reserva.horas = cursor.getInt(horasIndex)
                reserva.fechaReserva = cursor.getString(fechaReservaIndex)
                reserva.horaReserva = cursor.getString(horaReservaIndex)
                reserva.pago = cursor.getString(pagoIndex)
                reserva.nombreCampo = cursor.getString(nombreCampoIndex)
                reserva.tipoCampo = cursor.getString(tipoCampoIndex)
                reserva.precioHora = cursor.getDouble(precioHoraIndex)

                // Calcular el total
                reserva.total = reserva.horas * reserva.precioHora

                reservas.add(reserva)
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()

        if (reservas.isEmpty()) {
            val mensajeNoReservas = Reservaciones()
            mensajeNoReservas.nombreCampo = "No tienes reservaciones"
            return listOf(mensajeNoReservas) // Retorna solo el mensaje "No tienes reservaciones"
        }

        return reservas
    }



}
