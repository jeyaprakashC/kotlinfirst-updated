package jey.co.`in`.kotlin.first.home.photos

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import jey.co.`in`.kotlin.first.R
import jey.co.`in`.kotlin.first.home.BaseFragment
import jey.co.`in`.kotlin.first.home.DashBoardActivity
import jey.co.`in`.kotlin.first.home.DashBoardViewModel
import jey.co.`in`.kotlin.first.models.Photos
import kotlinx.android.synthetic.main.fragment_users_list.*

class PhotoListFragment : BaseFragment() {


    var viewManager: LinearLayoutManager? = null

    lateinit var viewAdaptor: PhotoListAdaptor

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_users_list, container, false)

        viewManager = LinearLayoutManager(this.context)



        return view
    }


    override fun onAttach(context: Context?) {
        super.onAttach(context)

        //AndroidInjection.inject(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activityObject = activity as DashBoardActivity

        val viewmodel = ViewModelProviders.of(activityObject,this.viewModeFactory).get(DashBoardViewModel::class.java)

        viewmodel.getPhotos()
        viewAdaptor = PhotoListAdaptor()

        viewmodel.getPhotos().observe(this, object : Observer<List<Photos>> {
            override fun onChanged(t: List<Photos>?) {

                if (t != null) {
                    Log.d("PhotoListFragment", "onChanged")
                    viewAdaptor.setCollections(t)
                    viewAdaptor.notifyDataSetChanged()
                }
            }
        })


        recyleview.apply {
            adapter = viewAdaptor
            layoutManager = viewManager
            setHasFixedSize(true)
        }
    }

    companion object {

        fun newInstance(): PhotoListFragment {
            return PhotoListFragment()
        }
    }
}