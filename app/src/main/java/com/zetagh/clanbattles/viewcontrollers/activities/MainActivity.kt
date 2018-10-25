package com.zetagh.clanbattles.viewcontrollers.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.api.OptionalPendingResult
import com.google.firebase.auth.FirebaseAuth
import com.zetagh.clanbattles.R
import com.zetagh.clanbattles.models.SettingsRepository
import com.zetagh.clanbattles.viewcontrollers.fragments.ChatFragment
import com.zetagh.clanbattles.viewcontrollers.fragments.HomeFragment
import com.zetagh.clanbattles.viewcontrollers.fragments.MapFragment
import com.zetagh.clanbattles.viewcontrollers.fragments.RankFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item -> return@OnNavigationItemSelectedListener navigateTo(item) }
    private val TAG = "mainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        shouldShowOnBoardingFunc()
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        navigation.selectedItemId = R.id.navigation_home
        setOnListenerFloatingActionButton()
    }

    private fun shouldShowOnBoardingFunc(){
        val settings = SettingsRepository(this)
        if (settings.shouldShowOnboarding) {
            startActivity(
                    Intent(this,
                            OnBoardingActivity::class.java))
        }
    }


    override fun onStart() {
        super.onStart()
        if(FirebaseAuth.getInstance().currentUser == null){
            Log.d(TAG,"User not logged in.")
            Toast.makeText(applicationContext,"Log in first please.",Toast.LENGTH_SHORT).show()
            goLogInScreen()
        }
    }

    private fun goLogInScreen() {
        val intent = Intent(this,LoginActivity::class.java)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.navigation_toolbar,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){
            R.id.action_account->{
                goToAccountActivity()
                return true
            }
            R.id.action_search->{
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun goToAccountActivity() {
        val intent = Intent(this,AccountActivity::class.java)
        startActivity(intent)
    }

    private fun fragmentFor(item: MenuItem): Fragment? {
        when(item.itemId){
            R.id.navigation_home -> {
                toolbar.title = "Home"
                mainFloatingActionButton.visibility = View.VISIBLE
                return HomeFragment()
            }
            R.id.navigation_sources -> {
                toolbar.title = "Lan Centers Map"
                mainFloatingActionButton.visibility = View.GONE
                return MapFragment()
            }
            R.id.navigation_ranking -> {
                toolbar.title = "General Ranking"
                mainFloatingActionButton.visibility = View.GONE
                return RankFragment()
            }
            R.id.navigation_favorites -> {
                toolbar.title = "Notifications"
                mainFloatingActionButton.visibility = View.GONE
                // TODO(Change fragment name to notification)
                return ChatFragment()
            }
        }
        return HomeFragment()
    }

    private fun navigateTo(item: MenuItem):Boolean {
        item.isChecked = true
        val fragment: Fragment = fragmentFor(item)!!
        return supportFragmentManager
                .beginTransaction()
                .replace(R.id.content,fragment)
                .commit()>0
    }

    private fun setOnListenerFloatingActionButton() {
        mainFloatingActionButton.setOnClickListener {
            val context = it.context
            context.startActivity(
                    Intent(context,AddPublicationActivity::class.java)
            )
        }
    }

}

