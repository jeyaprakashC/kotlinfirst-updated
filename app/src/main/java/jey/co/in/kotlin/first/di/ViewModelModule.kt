package jey.co.`in`.kotlin.first.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import jey.co.`in`.kotlin.first.AppViewModelFactory
import jey.co.`in`.kotlin.first.home.DashBoardViewModel
import kotlin.reflect.KClass

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: AppViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(DashBoardViewModel::class)
    internal abstract fun postListViewModel(viewModel: DashBoardViewModel): ViewModel


    @Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
    @MapKey
    annotation class ViewModelKey(val value: KClass<out ViewModel>)

}