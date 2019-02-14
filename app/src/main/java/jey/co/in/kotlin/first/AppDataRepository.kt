package jey.co.`in`.kotlin.first

import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.MutableLiveData
import jey.co.`in`.kotlin.first.models.Photos
import jey.co.`in`.kotlin.first.models.Users
import jey.co.`in`.kotlin.first.network.AppNetworkInterface
import jey.co.`in`.kotlin.first.network.NetworkDataProvider
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class AppDataRepository @Inject constructor() {



    init {
//        fetchPhotosFromServer()
//
//        fetchUsersFromServer()
    }


    fun fetchPhotosFromServer(): MutableLiveData<List<Photos>> {
        val photos = NetworkDataProvider().getPlaceHolderClient().create(AppNetworkInterface::class.java)
        val data = MutableLiveData<List<Photos>>()

        val response: Call<List<Photos>> = photos.getAlbums()

        response.enqueue(object : Callback<List<Photos>> {
            override fun onFailure(call: Call<List<Photos>>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<List<Photos>>, response: Response<List<Photos>>) {

                object : AsyncTask<Void, Void, Void>() {

                    override fun doInBackground(vararg params: Void?): Void? {

                        val photosResponse: List<Photos>? = response.body()

                        if (photosResponse != null)
                            for (photo in photosResponse) {

//                                val album = roomDao.getphotos(photo.id)
//                                if (album != null) {
//                                    roomDao.updatePhotos(photo)
//                                } else {
//                                    roomDao.addphots(photo)
//                                }

                            }
                        Log.d("PhotoListFragment", "posting")
                        data.postValue(photosResponse)

                        return null
                    }


                }.execute()

            }


        })
        return data
    }


    fun fetchUsersFromServer():MutableLiveData<List<Users>> {
        val nwinterface = NetworkDataProvider().getPlaceHolderClient().create(AppNetworkInterface::class.java)
        val data = MutableLiveData<List<Users>>()

        val response: Call<List<Users>> = nwinterface.getUsers()

        response.enqueue(object : Callback<List<Users>> {
            override fun onFailure(call: Call<List<Users>>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<List<Users>>, response: Response<List<Users>>) {

                object : AsyncTask<Void, Void, Void>() {

                    override fun doInBackground(vararg params: Void?): Void? {

                        val responseBody: List<Users>? = response.body()

                        if (responseBody != null)
                            for (user in responseBody) {

//                                val usrfromdb = roomDao.getUser(user.id)
//                                if (usrfromdb != null) {
//                                    roomDao.updateUsers(user)
//                                } else {
//                                    roomDao.addUsers(user)
//                                }
                            }
                        data.postValue(responseBody)

                        return null
                    }


                }.execute()

            }
        })
        return data

    }

}