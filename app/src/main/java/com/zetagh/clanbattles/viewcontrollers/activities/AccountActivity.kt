package com.zetagh.clanbattles.viewcontrollers.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
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
    private lateinit var gso:GoogleSignInOptions
    private lateinit var googleApiClient: GoogleApiClient


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)

        backButtonListener()
        initializeRecyclerView()
        getObjectResult()
    }

    private fun getObjectResult() {
        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()
        googleApiClient = GoogleApiClient.Builder(this).enableAutoManage(this,this).addApi(Auth.GOOGLE_SIGN_IN_API,gso).build()
    }

    override fun onStart() {
        super.onStart()
        val opr :OptionalPendingResult<GoogleSignInResult> = Auth.GoogleSignInApi.silentSignIn(googleApiClient)
        if(opr.isDone){
            val result = opr.get()
            handlerSignInResult(result)
        }else{
            opr.setResultCallback {
                handlerSignInResult(it)
            }
        }
    }

    private fun handlerSignInResult(result: GoogleSignInResult?) {
        if(result!!.isSuccess){
            val account = result.signInAccount
            Glide.with(this).load(account!!.photoUrl).into(userImageView)
            userFullNameTextView.text = account.displayName

        }else{

            goLogInScreen()
        }
    }

    private fun goLogInScreen() {
        val intent = Intent(this,LoginActivity::class.java)
        startActivity(intent)
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
    override fun onConnectionFailed(p0: ConnectionResult) {
    }

    private fun logOut(){
        Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback {
            if(it.isSuccess){
                goLogInScreen()
            }else{
                Toast.makeText(applicationContext,"Impossible to close session",Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun revoke(){
        Auth.GoogleSignInApi.revokeAccess(googleApiClient).setResultCallback {
            if(it.isSuccess){
                goLogInScreen()
            }else{
                Toast.makeText(applicationContext,"Impossible to close session",Toast.LENGTH_SHORT).show()
            }
        }
    }
}
