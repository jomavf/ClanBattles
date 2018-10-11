package com.zetagh.clanbattles.viewcontrollers.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zetagh.clanbattles.R
import com.zetagh.clanbattles.models.LanCenter
import kotlinx.android.synthetic.main.item_lancenter.view.*

class LanCenterAdapter(var lanCenters:ArrayList<LanCenter>, val context: Context) : RecyclerView.Adapter<LanCenterAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                LayoutInflater.from(context)
                        .inflate(R.layout.item_lancenter, parent, false))
    }

    override fun getItemCount(): Int {
        return lanCenters.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val lanCenter = lanCenters.get(position)
        holder.updateFrom(lanCenter)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val rankAvatarImage = view.rankAvatarImage
        val titleTextView = view.titleTextView
        val usernameTextView = view.usernameTextView

        fun updateFrom(lanCenter: LanCenter) {

            titleTextView.text = lanCenter.name
            usernameTextView.text = lanCenter.name
            rankAvatarImage.setDefaultImageResId(R.mipmap.ic_launcher)
            rankAvatarImage.setErrorImageResId(R.mipmap.ic_launcher)
            rankAvatarImage.setImageUrl(lanCenter.urlToImage)
        }
    }
}