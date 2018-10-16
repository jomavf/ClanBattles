package com.zetagh.clanbattles.viewcontrollers.adapters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.zetagh.clanbattles.R
import com.zetagh.clanbattles.models.AccountSettings
import com.zetagh.clanbattles.viewcontrollers.activities.LogOutActivity
import com.zetagh.clanbattles.viewcontrollers.activities.SettingsActivity
import kotlinx.android.synthetic.main.item_account.view.*

class AccountAdapter(private var listOfConfig:ArrayList<AccountSettings>,private val mContext: Context):RecyclerView.Adapter<AccountAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.item_account,parent,false))
    }

    override fun getItemCount(): Int {
        return listOfConfig.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mAccountSetting = listOfConfig[position]
        holder.updateFrom(mAccountSetting,mContext)
    }

    class ViewHolder(view:View):RecyclerView.ViewHolder(view){
        private val accountTextView = view.accountTextView
        private val accountImageView = view.accountImageView
        private val mAccountLinearLayout = view.accountLinearLayout

        fun updateFrom(mAccountSettings:AccountSettings,mContext: Context){
            accountTextView.text = mAccountSettings.text
            accountImageView.setDefaultImageResId(mAccountSettings.image)

            mAccountLinearLayout.setOnClickListener {
                when(mAccountSettings.id){
                    1->{
                        //do something
                        Toast.makeText(mContext,"You clicked Settings" ,Toast.LENGTH_SHORT).show()
                        val context = it.context
                        context.startActivity(Intent(context,SettingsActivity::class.java))
                    }
                    2->{
                        //TODO(Do something to sign out -> silence login should be in other part)
//                        Toast.makeText(mContext,"You clicked sign out",Toast.LENGTH_SHORT).show()
                        val context = it.context
                        context.startActivity(Intent(context,LogOutActivity::class.java))
                    }
                }
            }
        }
    }
}