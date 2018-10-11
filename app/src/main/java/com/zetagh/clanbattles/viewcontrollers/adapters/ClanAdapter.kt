package com.zetagh.clanbattles.viewcontrollers.adapters

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.Bitmap
import android.support.v4.graphics.drawable.RoundedBitmapDrawable
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.BitmapRequestListener
import com.zetagh.clanbattles.R
import com.zetagh.clanbattles.models.Clan
import com.zetagh.clanbattles.viewcontrollers.activities.ClanActivity
import kotlinx.android.synthetic.main.item_rank.view.*

class ClanAdapter(var clans:ArrayList<Clan>, val context: Context) : RecyclerView.Adapter<ClanAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                LayoutInflater.from(context)
                        .inflate(R.layout.item_rank, parent, false))
    }

    override fun getItemCount(): Int {
        return clans.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val clan = clans[position]
        holder.updateFrom(clan)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val rankAvatarImage = view.rankAvatarImage
        val titleTextView = view.titleTextView
        val usernameTextView = view.usernameTextView
        val clansLayout = view.clansLayout

        fun updateFrom(clan: Clan) {

            titleTextView.text = clan.name
            usernameTextView.text = clan.name

            rankAvatarImage.setDefaultImageResId(R.mipmap.ic_launcher)
            rankAvatarImage.setErrorImageResId(R.mipmap.ic_launcher)
            rankAvatarImage.setImageUrl(clan.urlToImage)

            clansLayout.setOnClickListener{view->
                val context = view.context
                context.startActivity(
                        Intent(context,
                                ClanActivity::class.java)
                                .putExtras(clan.toBundle()))
            }
        }

        private fun roundBitmap(bitmap: Bitmap): RoundedBitmapDrawable {
            var rbd = RoundedBitmapDrawableFactory.create(Resources.getSystem(),bitmap)
            rbd.isCircular = true
            return rbd
        }

        private fun getBitmapFromUrl(url:String): Bitmap {
            var bitmap: Bitmap?=null
            AndroidNetworking.get(url).setTag("urlToBitmap")
                    .setPriority(Priority.MEDIUM)
                    .setBitmapMaxHeight(100)
                    .setBitmapMaxWidth(100)
                    .setBitmapConfig(Bitmap.Config.ARGB_8888)
                    .build()
                    .getAsBitmap(object: BitmapRequestListener {
                        override fun onResponse(response: Bitmap?) {
                            bitmap = response
                        }
                        override fun onError(anError: ANError?) {
                            Log.d("urlToBitmap",anError.toString())
                        }
                    })
            return bitmap!!
        }
    }
}