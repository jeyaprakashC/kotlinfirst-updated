package jey.co.`in`.kotlin.first

import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.MutableLiveData
import io.reactivex.Observable
import jey.co.`in`.kotlin.first.models.Photos
import jey.co.`in`.kotlin.first.models.Users
import jey.co.`in`.kotlin.first.network.AppNetworkInterface
import jey.co.`in`.kotlin.first.network.NetworkResult
import jey.co.`in`.kotlin.first.network.Status
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class AppDataRepository @Inject constructor(val apiInterface: AppNetworkInterface) {


//    fun fetchPhotosFromServer(): MutableLiveData<List<Photos>> {
//        val data = MutableLiveData<List<Photos>>()
//        val response: Call<List<Photos>> = apiInterface.getAlbums()
//
//        response.enqueue(object : Callback<List<Photos>> {
//            override fun onFailure(call: Call<List<Photos>>, t: Throwable) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//            }
//
//            override fun onResponse(call: Call<List<Photos>>, response: Response<List<Photos>>) {
//
//                object : AsyncTask<Void, Void, Void>() {
//
//                    override fun doInBackground(vararg params: Void?): Void? {
//
//                        val photosResponse: List<Photos>? = response.body()
//
//                        if (photosResponse != null)
//                            for (photo in photosResponse) {
//
//                            }
//                        Log.d("PhotoListFragment", "posting")
//                        data.postValue(photosResponse)
//
//                        return null
//                    }
//
//
//                }.execute()
//
//            }
//
//
//        })
//        return data
//    }


    fun fetchPhotosFromServer(): MutableLiveData<NetworkResult<List<Photos>>> {
        val data = MutableLiveData<NetworkResult<List<Photos>>>()
        val response: Call<List<Photos>> = apiInterface.getAlbums()


        response.enqueue(object : Callback<List<Photos>> {
            override fun onFailure(call: Call<List<Photos>>, t: Throwable) {
                data.postValue(NetworkResult(jey.co.`in`.kotlin.first.network.Status.ERROR, null))
            }

            override fun onResponse(call: Call<List<Photos>>, response: Response<List<Photos>>) {

                object : AsyncTask<Void, Void, Void>() {

                    override fun doInBackground(vararg params: Void?): Void? {

                        val photosResponse: List<Photos>? = response.body()

                        if (photosResponse != null)
                            for (photo in photosResponse) {

                            }
                        Log.d("PhotoListFragment", "posting")

                        data.postValue(
                            NetworkResult(jey.co.`in`.kotlin.first.network.Status.SUCCESS, photosResponse)
                        )

                        return null
                    }


                }.execute()

            }


        })
        return data
    }

    fun fetchUsersFromServer(): MutableLiveData<NetworkResult<List<Users>>> {
        val data = MutableLiveData<NetworkResult<List<Users>>>()

        val response: Call<List<Users>> = apiInterface.getUsers()

        response.enqueue(object : Callback<List<Users>> {
            override fun onFailure(call: Call<List<Users>>, t: Throwable) {

                data.postValue(NetworkResult(jey.co.`in`.kotlin.first.network.Status.ERROR, null))

            }

            override fun onResponse(call: Call<List<Users>>, response: Response<List<Users>>) {

                object : AsyncTask<Void, Void, Void>() {

                    override fun doInBackground(vararg params: Void?): Void? {

                        val responseBody: List<Users>? = response.body()

                        if (responseBody != null)
                            for (user in responseBody) {

                            }
                        data.postValue(NetworkResult(jey.co.`in`.kotlin.first.network.Status.ERROR, responseBody))


                        return null
                    }


                }.execute()

            }
        })
        return data

    }


    fun getUsersData(): Observable<List<Users>> {
        return this.apiInterface.getUsersData()
    }

}