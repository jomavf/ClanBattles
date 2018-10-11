package com.zetagh.clanbattles.viewcontrollers.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.zetagh.clanbattles.R
import com.zetagh.clanbattles.viewcontrollers.fragments.ChatFragment
import com.zetagh.clanbattles.viewcontrollers.fragments.HomeFragment
import com.zetagh.clanbattles.viewcontrollers.fragments.MapFragment
import com.zetagh.clanbattles.viewcontrollers.fragments.RankFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item -> return@OnNavigationItemSelectedListener navigateTo(item) }
    private lateinit var bundle:Bundle

    override fun onCreate(savedInstanceState: Bundle?) {

        val intent = intent?:return
        bundle = intent.extras!!

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        toolbar.title = "Home in case"

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        navigation.selectedItemId = R.id.navigation_home

        //Funcion definida en la parte inferior
        setOnListenerFloatingActionButton()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.navigation_toolbar,menu)
        return true
    }

    private fun fragmentFor(item: MenuItem): Fragment? {
        when(item.itemId){
            R.id.navigation_home -> {
                toolbar.title = "Home"
                mainFloatingActionButton.visibility = View.VISIBLE
                return HomeFragment()
            }
            R.id.navigation_sources -> {
                toolbar.title = "Map"
                mainFloatingActionButton.visibility = View.GONE
                return MapFragment()
            }
            R.id.navigation_ranking -> {
                toolbar.title = "Groups"
                mainFloatingActionButton.visibility = View.GONE
                return RankFragment()
            }
            R.id.navigation_favorites -> {
                toolbar.title = "Message"
                mainFloatingActionButton.visibility = View.GONE
                return ChatFragment()
            }
        }
        return HomeFragment()
    }

    private fun navigateTo(item: MenuItem):Boolean {
        item.isChecked = true
        var fragment: Fragment = fragmentFor(item)!!
        fragment.arguments = bundle
        return supportFragmentManager
                .beginTransaction()
                .replace(R.id.content,fragment)
                .commit()>0
    }

    //SetonClickListener del mainFloatingActionButton
    private fun setOnListenerFloatingActionButton() {
        mainFloatingActionButton.setOnClickListener {
            val context = it.context
            context.startActivity(
                    Intent(context,AddPublicationActivity::class.java).putExtras(bundle)
            )
        }
    }

}

