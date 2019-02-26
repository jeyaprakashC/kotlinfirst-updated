package jey.co.`in`.kotlin.first

import android.util.Log
import jey.co.`in`.kotlin.first.di.NetworkModule
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import java.io.File
import javax.inject.Inject
import kotlin.test.AfterTest

abstract class BaseTest {

    lateinit var mockServer: MockWebServer
    @Inject
    lateinit var viewModeFactory: AppViewModelFactory

    lateinit var testAppComponent: TestAppComponent

    @Before
    open fun setUp() {
        this.configureMockServer()
        configureDagger()
    }

    @AfterTest
    open fun tearDown() {
        this.stopMockServer()


    }

    abstract fun isMockServerEnabled(): Boolean


    open fun configureDagger() {

        val url = mockServer.url("/").toString();
        this.testAppComponent =
            DaggerTestAppComponent.builder().networkModule(NetworkModule(url)).build()
        this.testAppComponent.inject(this)
    }

    open fun configureMockServer() {
        if (isMockServerEnabled()) {
            mockServer = MockWebServer()
            mockServer.start()
        }
    }

    open fun stopMockServer() {
        if (isMockServerEnabled()) {
            mockServer.shutdown()
        }
    }

    open fun mockHttpResponse(fileName: String, responseCode: Int) = mockServer.enqueue(
        MockResponse()
            .setResponseCode(responseCode)
            .setBody(getJson(fileName))
    )

    private fun getJson(path: String): String {
        val uri = this.javaClass.classLoader.getResource(path)
        val file = File(uri.path)

        val json = String(file.readBytes())

        Log.d("BaseTest", "BaseTest" + json)
        return json
    }
}