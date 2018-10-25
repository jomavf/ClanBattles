package com.zetagh.clanbattles.viewcontrollers.activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.zetagh.clanbattles.R
import kotlinx.android.synthetic.main.content_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var sharePref: SharedPreferences
    private val TAG = "loginModule"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        signInButtonListener()
        logInButtonListener()

    }

    private fun signInButtonListener(){
        signInButton.setOnClickListener {
            createSignInIntent()
        }
    }

    private fun logInButtonListener(){
        loginButton.setOnClickListener {
            val context = it.context
            context.startActivity(Intent(context,MainActivity::class.java))

        }
    }

    private fun createSignInIntent(){

        val providers = arrayListOf(
                AuthUI.IdpConfig.GoogleBuilder().build())

        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .build(),
                RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)

            if (resultCode == Activity.RESULT_OK) {
                val user = FirebaseAuth.getInstance().currentUser
                saveUserToSharePreference(user!!.displayName,user.photoUrl.toString())
                goMainScreen()
            } else {
                Toast.makeText(applicationContext,"Not possible to log in.",Toast.LENGTH_SHORT).show()
                goLogInScreen()
                Log.d(TAG,"Not possible to log in. -> ${response!!.error!!}")
            }
        }
    }

    private fun goLogInScreen() {
        val intent = Intent(this,LoginActivity::class.java)
        startActivity(intent)
    }

    private fun saveUserToSharePreference(username:String?,urlToUserImage:String) {
        sharePref = getSharedPreferences("com.zetagh.clanbattles.userData", Context.MODE_PRIVATE)
        with(sharePref.edit()){
            putString("username",username)
            putString("urlToUserImage",urlToUserImage)
            apply()
        }
        Log.d("test",sharePref.getString("username","NF"))
        Log.d("test",sharePref.getString("urlToUserImage","NF"))
    }


    private fun goMainScreen() {
        val intent = Intent(this,MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }
    companion object {
        private const val RC_SIGN_IN = 9001
    }
}

