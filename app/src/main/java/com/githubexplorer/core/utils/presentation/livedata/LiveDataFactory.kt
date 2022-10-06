package com.githubexplorer.core.utils.presentation.livedata

import androidx.lifecycle.MutableLiveData

interface LiveDataFactory {
    fun <T : Any> mutableLiveData(): MutableLiveData<T>
}