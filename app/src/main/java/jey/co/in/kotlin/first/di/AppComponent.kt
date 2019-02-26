package jey.co.`in`.kotlin.first.di

import dagger.Component
import jey.co.`in`.kotlin.first.home.BaseFragment
import jey.co.`in`.kotlin.first.home.DashBoardActivity
import retrofit2.Retrofit

@Component(modules = arrayOf( ViewModelModule::class, NetworkModule::class))
interface AppComponent {

    fun inject(activity: DashBoardActivity)
    fun inject(fragment: BaseFragment)
    fun getRetrofitClient():Retrofit


}