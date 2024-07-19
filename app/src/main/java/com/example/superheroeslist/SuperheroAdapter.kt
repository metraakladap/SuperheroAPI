package com.example.superheroeslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

class SuperheroAdapter : RecyclerView.Adapter<SuperheroAdapter.ViewHolder>() {
    private var superheroes: List<Superhero> = emptyList()

    fun updateSuperheros(newSuperheroes: List<Superhero>) {
        superheroes = newSuperheroes
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.superhero_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val superhero = superheroes[position]
        holder.bind(superhero)
    }

    override fun getItemCount() = superheroes.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.superheroImage)
        private val nameTextView: TextView = itemView.findViewById(R.id.superheroName)
        private val fullNameTextView: TextView = itemView.findViewById(R.id.superheroFullName)
        private val publisherTextView: TextView = itemView.findViewById(R.id.superheroPublisher)
        private val powerstatsTextView: TextView = itemView.findViewById(R.id.superheroPowerstats)

        fun bind(superhero: Superhero) {
            nameTextView.text = superhero.name
            fullNameTextView.text = "Full name: ${superhero.biography.fullName.ifEmpty { "Unknown" }}"
            publisherTextView.text = "Publisher: ${superhero.biography.publisher.ifEmpty { "Unknown" }}"

            val powerstats = superhero.powerstats
            powerstatsTextView.text = "Power stats:\nINT: ${powerstats.intelligence} | STR: ${powerstats.strength} | SPD: ${powerstats.speed}\n" +
                    "DUR: ${powerstats.durability} | PWR: ${powerstats.power} | CBT: ${powerstats.combat}"

            Glide.with(itemView.context)
                .load(superhero.images.md)
                .transform(CenterCrop(), RoundedCorners(8))
                .into(imageView)
        }
    }
}