package com.example.pruebasjson

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pruebasjson.Model.Circuito_Modelo
import com.example.pruebasjson.Model.Piloto_Modelo
import com.example.pruebasjson.obtenerCircuitos.CircuitosAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset

class Actividad2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pilotos)
        getPilotosCall()
        barrier.await()
        // Instance of users list using the data model class.
        val pilotos: ArrayList<Piloto_Modelo> = ArrayList()
        try {

            //val obj = JSONObject(getJSONFromAssets()!!)
            val obj = JSONObject(jsonTotal)
            val circuits0 = obj.getJSONObject("MRData")

            val circuits1 = circuits0.getJSONObject("DriverTable")

            val listaPilotos = circuits1.getJSONArray("Drivers")

//            println(listaCircuitos.length())
            for (i in 0 until listaPilotos.length()) {
                val piloto = listaPilotos.getJSONObject(i)
                //println(circuito)

                val id = piloto.getString("driverId")
                val nombre = piloto.getString("givenName")
                val apellido = piloto.getString("familyName")
                val url = piloto.getString("url")

                // Now add all the variables to the data model class and the data model class to the array list.
                val userDetails =
                    Piloto_Modelo(nombre,apellido,url)

                pilotos.add(userDetails)
            }
        } catch (e: JSONException) {
            //exception
            e.printStackTrace()
        }

        val rvPiloto = findViewById<View>(R.id.rvPilotos) as RecyclerView
        // Initialize contacts

        // Create adapter passing in the sample user data
        val adapter = PilotoAdapter(this,pilotos)
        // Attach the adapter to the recyclerview to populate items
        rvPiloto.adapter = adapter
        // Set layout manager to position the items
        rvPiloto.layoutManager = LinearLayoutManager(this)
        // That's all!


        //println("-------------------------222")
    }


    private fun getPilotosCall() {
        println("Llego aqui")
        CoroutineScope(Dispatchers.IO).launch {
            val listResult = CircuitosAPI.retrofitService.getPhotos("https://ergast.com/api/f1/2010/drivers.json")
            jsonTotal=listResult
            barrier.await()

            println("------")
            print(listResult)

        }

    }
}