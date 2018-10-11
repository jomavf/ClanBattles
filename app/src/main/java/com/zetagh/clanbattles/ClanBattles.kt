package com.zetagh.clanbattles

import android.app.Application
import com.androidnetworking.AndroidNetworking

class ClanBattles : Application(){
    override fun onCreate() {
        super.onCreate()
        AndroidNetworking.initialize(applicationContext)
    }
}