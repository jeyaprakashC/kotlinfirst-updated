package jey.co.`in`.kotlin.first.di

import dagger.Module
import dagger.Provides
import jey.co.`in`.kotlin.first.network.AppNetworkInterface
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Named


@Module
class NetworkModule(private val baseUrl: String) {
    val TIMEOUT_REQUEST: Long = 30
    @Provides
    fun providesNetworkInterface(retrofit: Retrofit): AppNetworkInterface {
        return retrofit.create(AppNetworkInterface::class.java)
    }

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(TIMEOUT_REQUEST, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_REQUEST, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_REQUEST, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl).addCallAdapterFactory(RxJava2CallAdapterFactory.create())

            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

}