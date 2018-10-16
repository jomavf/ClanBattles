package com.zetagh.clanbattles.viewcontrollers.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.zetagh.clanbattles.R
import com.zetagh.clanbattles.models.SettingsRepository
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val settings = SettingsRepository(this)
        switchOnBoarding.isChecked = settings.shouldShowOnboarding

        switchOnBoarding.setOnCheckedChangeListener{ _ , isChecked ->
            settings.shouldShowOnboarding = isChecked
        }
    }


}
