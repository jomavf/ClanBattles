package com.zetagh.clanbattles.viewcontrollers.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.zetagh.clanbattles.R
import com.zetagh.clanbattles.models.Game
import com.zetagh.clanbattles.networking.ClanBattlesApi
import com.zetagh.clanbattles.networking.GameResponse
import com.zetagh.clanbattles.viewcontrollers.adapters.GameAdapter
import kotlinx.android.synthetic.main.activity_on_boarding.*

class OnBoardingActivity : AppCompatActivity() {

    private var games = ArrayList<Game>()
    private lateinit var gamesAdapter : GameAdapter
    private lateinit var gamesLayoutManager: RecyclerView.LayoutManager
    private lateinit var gamesRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding)

        setSupportActionBar(toolbar)
        //TODO("Improve above name - set a resource")
        toolbar.title = "What are you preference?"

        gamesAdapter = GameAdapter(games, this)
        gamesLayoutManager = LinearLayoutManager(this)
        gamesRecyclerView = this.gamesRecyclerViewLayout
        gamesRecyclerView.adapter = gamesAdapter
        gamesRecyclerView.layoutManager  = gamesLayoutManager


        AndroidNetworking.get(ClanBattlesApi.getGameUrl)
                .setPriority(Priority.MEDIUM)
                .setTag(ClanBattlesApi.tag)
                .build()
                .getAsObject(GameResponse::class.java, object : ParsedRequestListener<GameResponse> {
                    override fun onResponse(response: GameResponse) {
                        games = response.games!!
                        Log.d(ClanBattlesApi.tag, "Parsed: Found ${games.size} games")
                        gamesAdapter.games = games
                        gamesAdapter.notifyDataSetChanged()
                    }

                    override fun onError(anError: ANError?) {
                        Log.d(ClanBattlesApi.tag, anError!!.message)
                    }

                })
    }
}