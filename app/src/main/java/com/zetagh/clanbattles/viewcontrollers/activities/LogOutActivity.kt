package com.zetagh.clanbattles.viewcontrollers.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.zetagh.clanbattles.R
import kotlinx.android.synthetic.main.activity_log_out.*

class LogOutActivity : AppCompatActivity(),GoogleApiClient.OnConnectionFailedListener {

    private lateinit var gso: GoogleSignInOptions
    private lateinit var googleApiClient: GoogleApiClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_out)

        getObjectResult()


        buttonLogOutYes.setOnClickListener {
            revoke()
        }
        buttonLogOutNo.setOnClickListener {
            val intent = Intent(this,AccountActivity::class.java)
            //Probably fix (clear) the upButton task ->  toolbar " '<' ClanBattles "
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }

    private fun getObjectResult() {
        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()
        googleApiClient = GoogleApiClient.Builder(this).enableAutoManage(this,this).addApi(Auth.GOOGLE_SIGN_IN_API,gso).build()
    }


    private fun goLogInScreen() {
        val intent = Intent(this,LoginActivity::class.java)
        startActivity(intent)
    }

    override fun onConnectionFailed(p0: ConnectionResult) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun logOut(){
        Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback {
            if(it.isSuccess){
                goLogInScreen()
            }else{
                Toast.makeText(applicationContext,"Impossible to close session", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun revoke(){
        Auth.GoogleSignInApi.revokeAccess(googleApiClient).setResultCallback {
            if(it.isSuccess){
                goLogInScreen()
            }else{
                Toast.makeText(applicationContext,"Impossible to close session", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
