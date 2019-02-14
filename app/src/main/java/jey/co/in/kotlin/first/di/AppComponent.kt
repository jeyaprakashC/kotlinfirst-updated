package jey.co.`in`.kotlin.first.di

import dagger.Component
import jey.co.`in`.kotlin.first.home.BaseFragment
import jey.co.`in`.kotlin.first.home.DashBoardActivity

@Component(modules = arrayOf(AppContextModule::class,ViewModelModule::class))
interface AppComponent {

    fun inject(activity: DashBoardActivity)
    fun inject(fragment: BaseFragment)


}