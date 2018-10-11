package com.zetagh.clanbattles.viewcontrollers.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zetagh.clanbattles.R
import com.zetagh.clanbattles.models.Publication
import kotlinx.android.synthetic.main.item_publication.view.*

class PublicationAdapter(var publications:ArrayList<Publication>,val context: Context): RecyclerView.Adapter<PublicationAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_publication,parent,false))
    }

    override fun getItemCount(): Int {
        return publications.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val publication = publications[position]
        holder.updateFrom(publication)
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val avatarImage = view.avatarImagePublication
        val titleTextView  = view.titleTextViewPublication
        val usernameTextView = view.usernameTextViewPublication
        val descriptionImageView = view.descriptionImageViewPublication
        val descriptionTextView = view.descriptionTextViewPublication

        fun updateFrom(publication: Publication) {

            titleTextView.text = publication.title
            usernameTextView.text = publication.title
            descriptionTextView.text = publication.description

            avatarImage.setDefaultImageResId(R.mipmap.ic_launcher)
            avatarImage.setErrorImageResId(R.mipmap.ic_launcher)
            avatarImage.setImageUrl(publication.urlToImage)
//            avatarImage.clipToOutline = true

            descriptionImageView.setDefaultImageResId(R.mipmap.ic_launcher)
            descriptionImageView.setErrorImageResId(R.mipmap.ic_launcher)
            descriptionImageView.setImageUrl(publication.urlToImage)
            descriptionImageView.clipToOutline = true
        }

    }
}