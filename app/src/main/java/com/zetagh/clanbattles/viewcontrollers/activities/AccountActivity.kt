package com.zetagh.clanbattles.viewcontrollers.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.api.OptionalPendingResult
import com.zetagh.clanbattles.R
import com.zetagh.clanbattles.models.AccountSettings
import com.zetagh.clanbattles.viewcontrollers.adapters.AccountAdapter
import kotlinx.android.synthetic.main.content_account.*

class AccountActivity : AppCompatActivity(),GoogleApiClient.OnConnectionFailedListener {

    private lateinit var listConfiguration:ArrayList<AccountSettings>
    private lateinit var accountAdapter:AccountAdapter
    private lateinit var accountLayoutManager:RecyclerView.LayoutManager
    private lateinit var mAccountRecyclerView:RecyclerView
    //Shared preference
    private lateinit var sharePref:SharedPreferences
    private var username:String?=null
    private var urlToUserPhoto:String?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)

        initializeRecyclerView()
        readSharePreference()
        updateUserData()
    }

    private fun updateUserData() {
        userFullNameTextView.text = username
        Glide.with(this).load(Uri.parse(urlToUserPhoto)).into(userImageView)
    }

    private fun readSharePreference() {
        sharePref = getSharedPreferences("com.zetagh.clanbattles.userData",Context.MODE_PRIVATE)
        username = sharePref.getString("username","No username found")
        urlToUserPhoto = sharePref.getString("urlToUserImage","No image found")
        Log.d("test1","User -> $username")
        Log.d("test1","urlPhoto -> $urlToUserPhoto")
    }

    private fun backButtonListener() {
//        TODO("Implement the back button to MainActivity")
    }

    private fun initializeRecyclerView() {
        val myAccount = AccountSettings(1,R.drawable.ic_settings_black_24dp,"Settings")
        val signOut = AccountSettings(2,R.drawable.ic_open_in_new_black_24dp,"Sign Out")
        listConfiguration = ArrayList()
        listConfiguration.add(myAccount)
        listConfiguration.add(signOut)

        accountAdapter = AccountAdapter(listConfiguration,applicationContext)
        accountLayoutManager = LinearLayoutManager(this)
        mAccountRecyclerView = accountRecyclerView
        mAccountRecyclerView.adapter = accountAdapter
        mAccountRecyclerView.layoutManager = accountLayoutManager

    }
    override fun onConnectionFailed(p0: ConnectionResult) {
    }
}
