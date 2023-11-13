package com.example.reservasimei

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class LocateActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMapClickListener {
    private lateinit var map: GoogleMap
    private var userId: Int = -1
    private var marker: Marker? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_locate)
        val db = DataBase(this)
        userId = intent.getIntExtra("userId", -1)
        val userName = db.retornarNombreEmpresa(userId)
        createFragment()

        val btnGuardar=findViewById<TextView>(R.id.btnGuardar)
        btnGuardar.setOnClickListener{
            regresar()
        }

    }

    private fun createFragment() {
        val mapFragment: SupportMapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        map.setOnMapClickListener(this)

        // Coordenadas de la Plaza de Armas de Cajamarca
        val plazaDeArmas = LatLng(-7.1575653,-78.5178524)

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(plazaDeArmas, 18f))
    }


    private fun updateMarker(coordenada: LatLng) {
        if (marker == null) {
            marker = map.addMarker(MarkerOptions().position(/* latlng = */ coordenada).title("Empresa"))
        } else {
            marker?.position = coordenada
        }
    }

    override fun onMapClick(coordenada: LatLng) {
        updateMarker(coordenada)
    }
    private fun regresar(){
        val i= Intent(this,MenuEmpresaActivity::class.java)
        startActivity(i)
    }
}
