package com.zetagh.clanbattles.viewcontrollers.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.zetagh.clanbattles.R
import com.zetagh.clanbattles.models.AccountSettings
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
//                TODO("Improve below logic")
                when(mAccountSettings.id){
                    1->{
                        Toast.makeText(mContext,"You clicked my account" ,Toast.LENGTH_SHORT).show()
                    }
                    2->{
                        Toast.makeText(mContext,"You clicked sign out",Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}