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

    //TODO(Tengo que primero hacer un login silencioso , guardar los datos necesarios en el shared preference o base de datos y luego jalar esa data a los campos correspodientes [acccount activity] , borrar login silencioso de account activity ,by Veliz)

    override fun onCreate(savedInstanceState: Bundle?) {

        val intent = intent?:return
        if(intent.extras != null){
            bundle = intent.extras!!
        }else{
            //TODO("Improve this strategy")
            bundle = Bundle()
            bundle.putInt("id", 1)
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        navigation.selectedItemId = R.id.navigation_home

        //Function below
        setOnListenerFloatingActionButton()
    }

    //This load the Menu in the toolbar
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
                toolbar.title = "Messages"
                mainFloatingActionButton.visibility = View.GONE
                return ChatFragment()
            }
        }
        return HomeFragment()
    }

    private fun navigateTo(item: MenuItem):Boolean {
        item.isChecked = true
        val fragment: Fragment = fragmentFor(item)!!
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

