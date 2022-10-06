package com.githubexplorer.core.utils.data.remote

import retrofit2.Retrofit

interface RemoteBuilder {
    fun build(): Retrofit
}