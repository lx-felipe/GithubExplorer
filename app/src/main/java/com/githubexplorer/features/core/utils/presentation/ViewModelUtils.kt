package com.githubexplorer.features.core.utils.presentation

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.MutableLiveData
import com.githubexplorer.features.core.utils.presentation.livedata.DefaultLiveDataFactory
import com.githubexplorer.features.core.utils.presentation.livedata.LiveDataFactory

object ViewModelUtils {
    var factory: LiveDataFactory = DefaultLiveDataFactory()
        private set

    @VisibleForTesting
    fun setFactory(factory: LiveDataFactory) {
        this.factory = factory
    }
}

inline fun <reified T : Any> mutableLiveData(): MutableLiveData<T> {
    return ViewModelUtils.factory.mutableLiveData<T>()
}