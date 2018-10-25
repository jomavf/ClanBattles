package com.zetagh.clanbattles.viewcontrollers.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.zetagh.clanbattles.R
import com.zetagh.clanbattles.models.Clan
import com.zetagh.clanbattles.networking.ClanBattlesApi
import com.zetagh.clanbattles.networking.ClanResponse
import com.zetagh.clanbattles.viewcontrollers.adapters.ClanAdapter
import kotlinx.android.synthetic.main.fragment_rank.view.*

class RankFragment : Fragment() {

    private var clans = ArrayList<Clan>()
    private lateinit var clanRecyclerView: RecyclerView
    private lateinit var clanAdapter : ClanAdapter
    private lateinit var clanLayoutManager : RecyclerView.LayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_rank, container, false)
        val getClansUrl = ClanBattlesApi.getClanUrl(2)
        clanRecyclerView = view.clansRecyclerView
        clanAdapter = ClanAdapter(clans,view.context)
        clanLayoutManager = LinearLayoutManager(view.context)
        clanRecyclerView.adapter = clanAdapter
        clanRecyclerView.layoutManager = clanLayoutManager

        AndroidNetworking.get(getClansUrl)
                .setPriority(Priority.LOW)
                .setTag(ClanBattlesApi.tag)
                .build()
                .getAsObject(ClanResponse::class.java, object : ParsedRequestListener<ClanResponse> {
                    override fun onResponse(response: ClanResponse) {
                        clans = response.clans!!
                        clanAdapter.clans = clans
                        clanAdapter.notifyDataSetChanged()
                    }
                    override fun onError(anError: ANError?) {
                        Log.d(ClanBattlesApi.tag, anError!!.message)
                    }
                })

        return view
    }
}
