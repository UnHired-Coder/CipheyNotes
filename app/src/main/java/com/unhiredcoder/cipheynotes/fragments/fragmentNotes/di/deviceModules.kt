package com.unhiredcoder.cipheynotes.fragments.fragmentNotes.di

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
class DeviceModules {

    @SuppressLint("HardwareIds")
    @Provides
    @DeviceId
    fun provideDeviceId(@ApplicationContext context: Context): String {
        return Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
            .toString()
    }
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DeviceId