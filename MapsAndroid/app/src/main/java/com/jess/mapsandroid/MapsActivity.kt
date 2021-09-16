package com.jess.mapsandroid

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.jess.mapsandroid.databinding.ActivityMapsBinding

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(
            MarkerOptions()
                .position(sydney)
                .title("Estoy aqui")
                .snippet("Breve descricao")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.map_marker))
        )
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 16f))

        //val fiapVilaOlimpia = LatLng(-23.595219, -46.685316)
        //val shoppingVilaOlimpia = LatLng(-23.595343, -46.686796)

        //val polylineOptions = PolylineOptions()
        //polylineOptions.add(fiapVilaOlimpia)
        //polylineOptions.add(shoppingVilaOlimpia)
        //polylineOptions.color(Color.GREEN)
        //polylineOptions.width(15f)

        //mMap.addPolyline(polylineOptions)
        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(fiapVilaOlimpia, 16f))

        //val fiap = LatLng(-23.59219, -46.685316)

        //val circleOptions = CircleOptions()
        //circleOptions.center(fiap)
        //circleOptions.radius(200.0)
        //circleOptions.fillColor(Color.argb(128, 0, 51, 102))
        //circleOptions.strokeWidth(10f)
        //circleOptions.strokeColor(Color.argb(128, 0, 51, 102))

        //mMap.addMarker(MarkerOptions().position(fiap).title("Fiap"))
        //mMap.addCircle(circleOptions)
        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(fiap, 16f))

    }
}