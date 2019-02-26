package jey.co.`in`.kotlin.first

import dagger.Component
import jey.co.`in`.kotlin.first.di.NetworkModule
import jey.co.`in`.kotlin.first.di.ViewModelModule

@Component(modules = arrayOf( ViewModelModule::class, NetworkModule::class))
interface TestAppComponent {

    fun inject(baseTest: BaseTest)
}