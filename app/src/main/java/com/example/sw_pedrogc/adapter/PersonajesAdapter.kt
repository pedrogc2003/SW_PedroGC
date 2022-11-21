package com.example.sw_pedrogc.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sw_pedrogc.Modelos.PersonajesResponse
import com.example.sw_pedrogc.R

class PersonajesAdapter(var personajesList: List<com.example.sw_pedrogc.Modelos.Result>):RecyclerView.Adapter<PersonajesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonajesViewHolder {
        val layoutInflater= LayoutInflater.from(parent.context)
        return PersonajesViewHolder(layoutInflater.inflate(R.layout.item_personaje,parent,false))
    }

    override fun onBindViewHolder(holder: PersonajesViewHolder, position: Int) {
        val item = personajesList[position]
        holder.render(item)
    }

    override fun getItemCount(): Int = personajesList.size

}