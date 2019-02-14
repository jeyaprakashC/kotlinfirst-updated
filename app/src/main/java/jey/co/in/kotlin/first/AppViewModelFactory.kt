package jey.co.`in`.kotlin.first

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import jey.co.`in`.kotlin.first.home.DashBoardViewModel
import javax.inject.Inject

class AppViewModelFactory @Inject constructor(val repository: AppDataRepository) : ViewModelProvider.Factory {


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(DashBoardViewModel::class.java!!)) {
            DashBoardViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}