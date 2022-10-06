package com.githubexplorer.features.core.utils.presentation.livedata

import androidx.lifecycle.MutableLiveData

class DefaultLiveDataFactory : LiveDataFactory {
    override fun <T : Any> mutableLiveData(): MutableLiveData<T> {
        return MutableLiveData<T>()
    }
}