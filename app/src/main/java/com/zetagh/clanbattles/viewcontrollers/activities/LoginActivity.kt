package com.zetagh.clanbattles.viewcontrollers.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.GoogleApiClient
import com.zetagh.clanbattles.R
import kotlinx.android.synthetic.main.content_login.*

class LoginActivity : AppCompatActivity(), GoogleApiClient.OnConnectionFailedListener {

    private var googleApiClient: GoogleApiClient? = null
    private val SIGN_IN_CODE = 666

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var gso: GoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()


        googleApiClient = GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build()

        loginButton.setOnClickListener {
            val context = it.context
            context.startActivity(Intent(context,OnBoardingActivity::class.java))
        }

        signInButtonCustomize()

        signInButton.setOnClickListener {
            var intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient)
            startActivityForResult(intent,SIGN_IN_CODE)
        }

    }

    private fun signInButtonCustomize() {
        signInButton.setSize(SignInButton.SIZE_WIDE)
        signInButton.setColorScheme(SignInButton.COLOR_DARK )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == SIGN_IN_CODE){
            var result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            handleSignInResult(result)
        }
    }

    private fun handleSignInResult(result: GoogleSignInResult?) {
        if(result!!.isSuccess){
            goMainScreen()
        }else{
            Toast.makeText(applicationContext,getString(R.string.not_log_in), Toast.LENGTH_SHORT).show()
        }
    }

    private fun goMainScreen() {
        var intent = Intent(this,OnBoardingActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    override fun onConnectionFailed(p0: ConnectionResult) {
    }

}

