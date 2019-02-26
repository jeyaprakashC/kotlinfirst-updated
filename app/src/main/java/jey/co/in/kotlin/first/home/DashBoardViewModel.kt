package jey.co.`in`.kotlin.first.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import jey.co.`in`.kotlin.first.AppDataRepository
import jey.co.`in`.kotlin.first.models.Photos
import jey.co.`in`.kotlin.first.models.Users
import jey.co.`in`.kotlin.first.network.NetworkResult
import jey.co.`in`.kotlin.first.network.Status
import javax.inject.Inject


class DashBoardViewModel @Inject constructor(val repository: AppDataRepository) : ViewModel() {


     var photosResult: MutableLiveData<NetworkResult<List<Photos>>>  = MutableLiveData()
     var usersResult: MutableLiveData<NetworkResult<List<Users>>>  = MutableLiveData()

    init {

//        getPhotoResultFromRepository()
//        getUsersFromRepository()

        getUsersList()
    }

    fun getPhotos(): LiveData<NetworkResult<List<Photos>>> {
        return photosResult
    }

    fun getUsers(): LiveData<NetworkResult<List<Users>>> {
        return repository.fetchUsersFromServer()
    }


    fun getUsersFromRepository() {
        usersResult = repository.fetchUsersFromServer()
    }


    fun getPhotoResultFromRepository() {
        photosResult = repository.fetchPhotosFromServer()
    }


    fun getUsersList() {

        val compositeDisposable = CompositeDisposable()

        val disposable =
            repository.getUsersData().subscribeOn(AndroidSchedulers.mainThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(
                    object : DisposableObserver<List<Users>>() {
                        override fun onComplete() {

                            Log.d("getUsersList", "onComplete")

                        }

                        override fun onNext(t: List<Users>) {

                            Log.d("getUsersList", "onNext" + t?.size)

                            usersResult.postValue(NetworkResult(Status.SUCCESS, t))

                        }

                        override fun onError(e: Throwable) {

                            Log.d("getUsersList", "onError" + e.message)
                        }
                    })

        compositeDisposable.add(disposable)
    }
}