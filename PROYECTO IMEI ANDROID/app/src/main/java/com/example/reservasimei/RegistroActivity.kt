package com.example.reservasimei

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text

class RegistroActivity : AppCompatActivity() {
    private lateinit var uname: EditText
    private lateinit var email: EditText
    private lateinit var pass: EditText
    private lateinit var cpword: EditText

    private lateinit var btnRegistrar: Button
    private lateinit var db: DataBase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        uname=findViewById(R.id.etNombres)
        email=findViewById(R.id.etCorreo)
        pass=findViewById(R.id.etPass1)
        cpword=findViewById(R.id.etPass2)
        btnRegistrar=findViewById(R.id.btnRegistrar)
        db= DataBase(this)
        btnRegistrar.setOnClickListener {
            val nombresApellidosText=uname.text.toString()
            val correoText=email.text.toString()
            val passText=pass.text.toString()
            val cpassText=cpword.text.toString()

            val guardar=db.insertData(nombresApellidosText,correoText,passText)
            if (TextUtils.isEmpty(nombresApellidosText) || TextUtils.isEmpty(correoText) || TextUtils.isEmpty(passText) || TextUtils.isEmpty(cpassText)){
                Toast.makeText(this,"Se requiere que todos los campos esten llenos",Toast.LENGTH_SHORT).show()
            }else{
                if (passText.equals(cpassText)){
                    if (guardar==true){
                        Toast.makeText(this,"Registro exitoso",Toast.LENGTH_SHORT).show()
                        val i=Intent(applicationContext,MainActivity::class.java)
                        startActivity(i)
                    }else{
                        Toast.makeText(this,"Correo existente",Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(this,"Las contraseñas no coinciden",Toast.LENGTH_SHORT).show()
                }
            }
        }

        val btnRegresar=findViewById<TextView>(R.id.btnVolverLogin)
        btnRegresar.setOnClickListener{
            regresarLogin()
        }
    }
    private fun regresarLogin(){
        val i=Intent(this,MainActivity::class.java)
        startActivity(i)
    }
}