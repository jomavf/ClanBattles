package com.zetagh.clanbattles.networking

class ClanBattlesApi {
    companion object {
        private val baseUrl = "http://clanbattles.somee.com/clanbattles"
        val getGameUrl = "$baseUrl/v1/games"
        val getLanCentersUrl = "$baseUrl/v1/lancenters"
        val getGamersUrl = "$baseUrl/v1/gamers"
        fun getPublicationByGamer(gamerId:Int):String{return "$getGameUrl/$gamerId/publications"}
        fun urlPostPublication(gamerId:Int):String{return "$getGamersUrl/$gamerId/publications"}
        fun getClanUrl(gameId: Int): String {return "$getGameUrl/$gameId/clans"}
        val tag = "ClanBattles"
    }
}