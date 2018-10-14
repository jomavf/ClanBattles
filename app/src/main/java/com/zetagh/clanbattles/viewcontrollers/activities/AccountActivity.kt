package com.zetagh.clanbattles.viewcontrollers.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.zetagh.clanbattles.R
import com.zetagh.clanbattles.models.AccountSettings
import com.zetagh.clanbattles.viewcontrollers.adapters.AccountAdapter
import kotlinx.android.synthetic.main.content_account.*

class AccountActivity : AppCompatActivity() {

    private lateinit var listConfiguration:ArrayList<AccountSettings>
    private lateinit var accountAdapter:AccountAdapter
    private lateinit var accountLayoutManager:RecyclerView.LayoutManager
    private lateinit var mAccountRecyclerView:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)

        initializeRecyclerView()
        backButtonListener()

    }

    private fun backButtonListener() {
//        TODO("Implement the back button to MainActivity")
    }

    private fun initializeRecyclerView() {
        val myAccount = AccountSettings(1,R.drawable.ic_settings_black_24dp,"My account")
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

}
