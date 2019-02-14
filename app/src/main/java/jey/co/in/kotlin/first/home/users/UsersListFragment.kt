package jey.co.`in`.kotlin.first.home.users

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import jey.co.`in`.kotlin.first.R
import jey.co.`in`.kotlin.first.home.BaseFragment
import jey.co.`in`.kotlin.first.home.DashBoardActivity
import jey.co.`in`.kotlin.first.home.DashBoardViewModel
import jey.co.`in`.kotlin.first.models.Users
import kotlinx.android.synthetic.main.fragment_users_list.*

class UsersListFragment : BaseFragment(), UserListClickListener {


    lateinit var viewManager: RecyclerView.LayoutManager
    lateinit var viewAdaptor: UsersListAdaptor

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_users_list, container, false)

        viewManager = LinearLayoutManager(activity)
        val activityObject = activity as DashBoardActivity

        val viewmodel = ViewModelProviders.of(activityObject,this.viewModeFactory).get(DashBoardViewModel::class.java)
        viewAdaptor = UsersListAdaptor()

        viewmodel.getUsers().observe(this,
            Observer<List<Users>> { t ->
                if (t != null) {
                    Log.d("UsersListFragment", "users data changed ${t.size}")
                    viewAdaptor.myDataLsit = t

                    viewAdaptor.notifyDataSetChanged()

                }
            })
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        recyleview.apply {
            adapter = viewAdaptor
            layoutManager = viewManager
            setHasFixedSize(true)
        }
        recyleview.addOnItemTouchListener(object : RecyclerView.OnItemTouchListener {
            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
            }

            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {


                val child = rv.findChildViewUnder(e.x, e.y)

                Log.d("onInterceptTouchEvent", "" + e.action)


                if (child != null && e.action == MotionEvent.ACTION_DOWN) {
                    val position = rv.getChildAdapterPosition(child)
                    onClick(position)

                }

                return false
            }

            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })


    }

    companion object {

        fun newInstance(): UsersListFragment {
            return UsersListFragment()
        }
    }

    override fun onClick(position: Int) {
        val containerActivity = activity as DashBoardActivity
        containerActivity.showDetailsFragment(position)
    }
}