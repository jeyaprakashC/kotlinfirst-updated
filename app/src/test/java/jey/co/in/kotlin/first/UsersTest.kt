package jey.co.`in`.kotlin.first

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import jey.co.`in`.kotlin.first.home.DashBoardViewModel
import jey.co.`in`.kotlin.first.models.Users
import org.junit.Rule
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import java.net.HttpURLConnection
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

@RunWith(RobolectricTestRunner::class)

class UsersTest() : BaseTest() {


    private lateinit var viewModel: DashBoardViewModel

    private lateinit var activity: FragmentActivity

    override fun isMockServerEnabled(): Boolean {
        return true
    }

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @BeforeTest
    override fun setUp() {
        super.setUp()
        this.activity = Robolectric.setupActivity(FragmentActivity::class.java)
        this.viewModel = ViewModelProviders.of(this.activity, viewModeFactory)[DashBoardViewModel::class.java]


    }

    @AfterTest
    override fun tearDown() {
        super.tearDown()
    }


    @Test
    fun getUsers() {
        this.mockHttpResponse("response.json", HttpURLConnection.HTTP_OK)


        this.viewModel.getUsersList()

        val user: Users? = this.viewModel.usersResult.value?.data?.get(0)

        assertEquals("Leanne Graham", user?.name, "User matched..")

    }
}