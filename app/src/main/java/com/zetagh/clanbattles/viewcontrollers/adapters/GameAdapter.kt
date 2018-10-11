package com.zetagh.clanbattles.viewcontrollers.adapters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zetagh.clanbattles.R
import com.zetagh.clanbattles.models.Game
import com.zetagh.clanbattles.viewcontrollers.activities.MainActivity
import kotlinx.android.synthetic.main.item_game.view.*

class GameAdapter(var games:ArrayList<Game>, val context: Context) : RecyclerView.Adapter<GameAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                LayoutInflater.from(context)
                        .inflate(R.layout.item_game, parent, false))
    }

    override fun getItemCount(): Int {
        return games.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val game = games.get(position)
        holder.updateFrom(game)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val pictureImageView = view.pictureImageView
        val gameLayout = view.gameLayout
        fun updateFrom(game: Game) {
            with(pictureImageView) {
                pictureImageView.setDefaultImageResId(R.mipmap.ic_launcher)
                pictureImageView.setErrorImageResId(R.mipmap.ic_launcher)
                pictureImageView.setImageUrl(game.urlToImage)
                pictureImageView.clipToOutline = true

            }
            gameLayout.setOnClickListener { view ->
                val context = view.context
                context.startActivity(
                        Intent(context,
                                MainActivity::class.java)
                                .putExtras(game.toBundle()))
            }
        }
    }
}