package com.zetagh.clanbattles.models

import android.os.Bundle

data class Game( val id:Int,
                 val name:String?,
                 val rating:Int,
                 val description:String?,
                 val urlWebPage:String?,
                 val urlToImage:String?,
                 val status:String?) {
    companion object {
        fun from(bundle: Bundle) : Game {
            return Game(
                    bundle.getInt("id"),
                    bundle.getString("name"),
                    bundle.getInt("rating"),
                    bundle.getString("description"),
                    bundle.getString("urlWebPage"),
                    bundle.getString("urlToImage"),
                    bundle.getString("status")
            )
        }
    }


    fun toBundle(): Bundle {
        val bundle = Bundle()
        bundle.putInt("id", id)
        bundle.putString("name", name)
        bundle.putInt("rating",rating)
        bundle.putString("description", description)
        bundle.putString("urlWebPage",urlWebPage)
        bundle.putString("urlToImage", urlToImage)
        bundle.putString("status", status)
        return bundle
    }

}