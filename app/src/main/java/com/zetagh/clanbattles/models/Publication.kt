package com.zetagh.clanbattles.models

import android.os.Bundle

data class Publication(
        var id:Int? = null,
        var gamerId:Int? = null,
        var gameId:Int? = null,
        var title:String? = null,
        var description:String? = null,
        var urlToImage:String? = null ,
        var publicationDate:String? = null,
        var status:String? = null
){
    companion object {
        fun from(bundle: Bundle):Publication {
            return Publication(
                    bundle.getInt("id"),
                    bundle.getInt("gamerId"),
                    bundle.getInt("gameId"),
                    bundle.getString("title"),
                    bundle.getString("description"),
                    bundle.getString("urlToImage"),
                    bundle.getString("publicationDate"),
                    bundle.getString("status")
            )
        }
    }

    fun toBundle(){
        val bundle = Bundle()
        with(bundle){
            putInt("id",id!!)
            putInt("gamerId",gamerId!!)
            putInt("gameId",gameId!!)
            putString("title",title)
            putString("description",description)
            putString("urlToImage",urlToImage)
            putString("publicationDate",publicationDate)
            putString("status",status)
        }
    }
}