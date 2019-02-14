package jey.co.`in`.kotlin.first.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import jey.co.`in`.kotlin.first.AppDataRepository
import jey.co.`in`.kotlin.first.AppViewModelFactory
import jey.co.`in`.kotlin.first.di.AppContextModule
import jey.co.`in`.kotlin.first.di.DaggerAppComponent
import javax.inject.Inject

open class BaseFragment : Fragment() {

    @Inject
    lateinit var repository: AppDataRepository


    @Inject
    lateinit var viewModeFactory: AppViewModelFactory
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return super.onCreateView(inflater, container, savedInstanceState)

    }


    override fun onAttach(context: Context?) {
        super.onAttach(context)

        val activityObject = context as DashBoardActivity

        val daggerAppComponent =
            DaggerAppComponent.builder().appContextModule(AppContextModule(activityObject.application)).build()

        daggerAppComponent.inject(this)

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

}