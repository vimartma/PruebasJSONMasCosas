package com.example.pruebasjson

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pruebasjson.Model.Circuito_Modelo
import com.example.pruebasjson.Model.Piloto_Modelo
import kotlinx.android.synthetic.main.circuito_layout.view.*
import kotlinx.android.synthetic.main.pilotos_layout.view.*

class PilotoAdapter(val context: Context, val items: ArrayList<Piloto_Modelo>) : RecyclerView.Adapter<PilotoAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //println("-------------------------")
        //println(items.size)
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.pilotos_layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items.get(position)
        //La parte del holder. se corresponde al nombre dado en la clase de "ViewHolder" la del item. en la clase Circuito_modelo
        //println("-------------------------")
        //println(items.size)
        holder.nom_pil.text = item.nombre.toString()
        holder.ap_pil.text = item.apellido
        holder.url_pil.text = item.url

    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // Holds the TextView that will add each item to
        val nom_pil = view.nom_piloto
        val ap_pil = view.ap_piloto
        val url_pil = view.url_wiki
        //val url_Foto = "Aqui añadir la URL"
    }
}