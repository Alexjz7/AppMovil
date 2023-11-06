package com.example.reservasimei

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.reservasimei.util.PreferenceHelper
import com.example.reservasimei.util.PreferenceHelper.get
import com.example.reservasimei.util.PreferenceHelper.set

class MainActivity : AppCompatActivity() {
    private lateinit var btnLogin:Button
    private lateinit var etCorreo:EditText
    private lateinit var etPass:EditText
    private lateinit var db:DataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnLogin=findViewById(R.id.btnLogin)
        etCorreo=findViewById(R.id.etUser)
        etPass=findViewById(R.id.etPass)
        db= DataBase(this)

        btnLogin.setOnClickListener {
            val userText=etCorreo.text.toString()
            val passText=etPass.text.toString()
            if (TextUtils.isEmpty(userText) || TextUtils.isEmpty(passText)){
                Toast.makeText(this,"Agrega correo y contraseña",Toast.LENGTH_SHORT).show()
            }else{
                val id_ingreso=db.comprobarLogin(userText,passText)
                if (id_ingreso!=-1){
                    Toast.makeText(this,"Logueo exitoso",Toast.LENGTH_SHORT).show()
                    logueo(id_ingreso)
                }else{
                    Toast.makeText(this,"Usuario o contraseña equivocados",Toast.LENGTH_SHORT).show()
                }
            }
        }
        val antierror=PreferenceHelper.defaultPrefs(this)
        if (antierror["session",false]){
            val userText=etCorreo.text.toString()
            val passText=etPass.text.toString()
            val id_ingreso=db.comprobarLogin(userText,passText)
            logueo(id_ingreso)
        }
        val btnRegistrar=findViewById<TextView>(R.id.btnRegistrar)
        btnRegistrar.setOnClickListener{
            entrarRegistro()
        }

        val btnRegistrarEmpresa=findViewById<TextView>(R.id.btnRegistrarEmpresa)
        btnRegistrarEmpresa.setOnClickListener{
            entrarRegistroEmpresa()
        }
    }
    private fun entrarRegistro(){
        val i=Intent(this,RegistroActivity::class.java)
        startActivity(i)
    }
    private fun entrarRegistroEmpresa(){
        val i=Intent(this,RegistroActivity::class.java)
        startActivity(i)
    }
    private fun logueo(userId: Int) {
        val i = Intent(this, MenuActivity::class.java)

        i.putExtra("userId", userId)

        crearSesionPermanente()
        startActivity(i)
        finish()
    }

    private fun crearSesionPermanente(){
        val parametro=PreferenceHelper.defaultPrefs(this)
        parametro["session"]=true
    }
}