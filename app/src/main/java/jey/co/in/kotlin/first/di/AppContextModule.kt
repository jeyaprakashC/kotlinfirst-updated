package jey.co.`in`.kotlin.first.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class AppContextModule(val application: Application) {


    @Provides
    fun provideContenxt(): Context {
        return application.applicationContext
    }
}