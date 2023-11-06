package com.example.reservasimei

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class RegistroEmpresaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_empresa)
        val btnRegresar=findViewById<TextView>(R.id.btnVolverLogin)
        btnRegresar.setOnClickListener{
            regresarLogin()
        }
    }
    private fun regresarLogin(){
        val i= Intent(this,MainActivity::class.java)
        startActivity(i)
    }
}