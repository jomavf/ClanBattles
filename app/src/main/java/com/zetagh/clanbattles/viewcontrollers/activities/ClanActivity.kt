package com.zetagh.clanbattles.viewcontrollers.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.zetagh.clanbattles.R
import kotlinx.android.synthetic.main.activity_clan.*

class ClanActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clan)
        setSupportActionBar(toolbar)
    }
}
