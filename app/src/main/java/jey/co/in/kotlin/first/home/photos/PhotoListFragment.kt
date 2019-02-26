package jey.co.`in`.kotlin.first.home.photos

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import jey.co.`in`.kotlin.first.R
import jey.co.`in`.kotlin.first.home.BaseFragment
import jey.co.`in`.kotlin.first.home.DashBoardActivity
import jey.co.`in`.kotlin.first.home.DashBoardViewModel
import jey.co.`in`.kotlin.first.models.Photos
import jey.co.`in`.kotlin.first.network.NetworkResult
import jey.co.`in`.kotlin.first.network.Status
import kotlinx.android.synthetic.main.fragment_users_list.*

class PhotoListFragment : BaseFragment() {


    var viewManager: LinearLayoutManager? = null

    lateinit var viewAdaptor: PhotoListAdaptor

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_users_list, container, false)
        viewManager = LinearLayoutManager(this.context)
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activityObject = activity as DashBoardActivity

        val viewmodel = ViewModelProviders.of(activityObject, this.viewModeFactory).get(DashBoardViewModel::class.java)

        viewAdaptor = PhotoListAdaptor()


        viewmodel.photosResult.observe(this, object : Observer<NetworkResult<List<Photos>>> {
            override fun onChanged(t: NetworkResult<List<Photos>>?) {
                when (t?.status) {
                    Status.SUCCESS -> setCollection(t?.data)
                    Status.ERROR -> showError()
                }
            }

        })

        recyleview.apply {
            adapter = viewAdaptor
            layoutManager = viewManager
            setHasFixedSize(true)
        }
    }


    fun setCollection(data: List<Photos>?) {
        if (data != null) {
            data.map {

            }
            viewAdaptor.setCollections(data)
            viewAdaptor.notifyDataSetChanged()
        }
    }

    companion object {

        fun newInstance(): PhotoListFragment {
            return PhotoListFragment()
        }
    }
}