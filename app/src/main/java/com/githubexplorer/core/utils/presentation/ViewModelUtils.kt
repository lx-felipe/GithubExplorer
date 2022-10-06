package com.githubexplorer.core.utils.presentation

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.MutableLiveData
import com.githubexplorer.core.utils.presentation.livedata.DefaultLiveDataFactory
import com.githubexplorer.core.utils.presentation.livedata.LiveDataFactory

object ViewModelUtils {
    var factory: LiveDataFactory = DefaultLiveDataFactory()
        private set

    @VisibleForTesting
    fun setFactory(factory: LiveDataFactory) {
        ViewModelUtils.factory = factory
    }
}

inline fun <reified T : Any> mutableLiveData(): MutableLiveData<T> {
    return ViewModelUtils.factory.mutableLiveData<T>()
}