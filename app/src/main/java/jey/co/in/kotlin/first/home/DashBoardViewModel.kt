package jey.co.`in`.kotlin.first.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import jey.co.`in`.kotlin.first.AppDataRepository
import jey.co.`in`.kotlin.first.models.Photos
import jey.co.`in`.kotlin.first.models.Users
import javax.inject.Inject


class DashBoardViewModel @Inject constructor(val repository: AppDataRepository) : ViewModel() {



    fun getPhotos(): LiveData<List<Photos>> {
        return repository.fetchPhotosFromServer()
    }


    fun getUsers(): LiveData<List<Users>> {
        return repository.fetchUsersFromServer()
    }

}