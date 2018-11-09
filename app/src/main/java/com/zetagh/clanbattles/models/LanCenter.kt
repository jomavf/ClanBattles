package com.zetagh.clanbattles.models

import android.os.Bundle

data class LanCenter(
        val id:Int,
        val name:String?,
        val ruc: String?,
        val email:String?,
        val address:String?,
        val latitude:Double?,
        val longitude:Double?,
        val status:String?,
        val urlToImage:String?
){
    companion object {
        fun from(bundle: Bundle):LanCenter{
            return LanCenter(
                    bundle.getInt("id"),
                    bundle.getString("name"),
                    bundle.getString("ruc"),
                    bundle.getString("email"),
                    bundle.getString("address"),
                    bundle.getDouble("latitude"),
                    bundle.getDouble("longitude"),
                    bundle.getString("status"),
                    bundle.getString("urlToImage")
            )
        }
    }

    fun toBundle(): Bundle {
        val bundle = Bundle()
        with(bundle){
            putInt("id",id)
            putString("name",name)
            putString("ruc",ruc)
            putString("email",email)
            putString("address",address)
            putDouble("latitude", latitude!!)
            putDouble("longitude",longitude!!)
            putString("status",status)
            putString("urlToImage",urlToImage)
        }
        return bundle
    }

}