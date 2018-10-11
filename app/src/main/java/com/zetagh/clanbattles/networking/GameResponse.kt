package com.zetagh.clanbattles.networking

import com.zetagh.clanbattles.models.Game

class GameResponse(
        val status: String? = null,
        val games: ArrayList<Game>? = ArrayList()
)
