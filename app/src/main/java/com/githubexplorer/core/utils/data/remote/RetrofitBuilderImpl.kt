package com.githubexplorer.core.utils.data.remote

import com.githubexplorer.BuildConfig
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

private const val MEDIA_TYPE_JSON = "application/json"

@OptIn(ExperimentalSerializationApi::class)
class RetrofitBuilderImpl: RemoteBuilder {

    override fun build(): Retrofit {
        val json = Json {
            ignoreUnknownKeys = true
        }

        return Retrofit.Builder()
            .client(OkHttpClient.Builder().build())
            .baseUrl(BuildConfig.ENVIROMENT)
            .addConverterFactory(json.asConverterFactory(MediaType.get(MEDIA_TYPE_JSON)))
            .build()
    }
}