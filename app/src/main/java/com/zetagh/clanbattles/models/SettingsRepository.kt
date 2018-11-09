package com.zetagh.clanbattles.models

import android.content.Context
import android.preference.PreferenceManager

class SettingsRepository(val context: Context) {
    companion object {
        var didShowOnboarding = "didShowOnboarding"
    }

    val preferences = PreferenceManager
            .getDefaultSharedPreferences(context)

    var didShowOnboarding: Boolean = preferences
            .getBoolean(
                    SettingsRepository.didShowOnboarding,
                    false)
        set(value) = preferences.edit().putBoolean(
                SettingsRepository.didShowOnboarding, value)
                .apply()

    var shouldShowOnboarding: Boolean = !didShowOnboarding
        set(value) {
            didShowOnboarding = !value
        }
}