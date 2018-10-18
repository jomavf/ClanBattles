package com.zetagh.clanbattles.viewcontrollers.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.*
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.tasks.Task
import com.zetagh.clanbattles.R
import kotlinx.android.synthetic.main.content_login.*

class LoginActivity : AppCompatActivity(), GoogleApiClient.OnConnectionFailedListener {

    private var googleSignInClient: GoogleSignInClient? = null
    private lateinit var gso:GoogleSignInOptions
    private val RC_SIGN_IN = 777

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()

        googleSignInClient = GoogleSignIn.getClient(this,gso)

        userAlreadyLogIn()

        loginButton.setOnClickListener {
            val context = it.context
            context.startActivity(Intent(context,MainActivity::class.java))
        }

        //Sign in button listener
        signInButtonCustomize()
        signInButton.setOnClickListener {
            val signInIntent:Intent= googleSignInClient!!.signInIntent
            startActivityForResult(signInIntent,RC_SIGN_IN)
        }

    }

    private fun userAlreadyLogIn() {
        val account : GoogleSignInAccount? = GoogleSignIn.getLastSignedInAccount(this)
        updateUI(account)
    }

    private fun updateUI(account: GoogleSignInAccount?) {
        if(account != null){
            //user already signed in the app
            goMainScreen()
        }else{
            //the user has not yet signed in
            Toast.makeText(applicationContext,"Log in please",Toast.LENGTH_SHORT).show()
        }
    }

    private fun signInButtonCustomize() {
        signInButton.setSize(SignInButton.SIZE_WIDE)
        signInButton.setColorScheme(SignInButton.COLOR_DARK )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == RC_SIGN_IN){
            val task : Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completeTask: Task<GoogleSignInAccount>?) {
        try {
            //TODO(Should I save here in the shared Preference?)
            val account : GoogleSignInAccount = completeTask!!.getResult(ApiException::class.java)!!
            Log.d("test","Log in act Username -> ${account.displayName}")
            Log.d("test","Log in act PhotoUrl -> ${account.photoUrl}")
            goMainScreen()
        }catch (e:ApiException ){
            Log.d("signIn","signInResult:failed code= ${e.statusCode}")
        }
    }

    private fun goMainScreen() {
        val intent = Intent(this,MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    override fun onConnectionFailed(p0: ConnectionResult) {
    }

}

