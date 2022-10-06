package com.githubexplorer.features.repolist.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.githubexplorer.features.core.utils.presentation.mutableLiveData
import com.githubexplorer.features.repolist.domain.usecase.GetProjectListUseCase
import com.githubexplorer.features.repolist.domain.model.ProjectItem
import com.githubexplorer.features.repolist.presentation.state.ProjectListViewState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class ProjectListViewModel(
    private val getProjectListUseCase: GetProjectListUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val state = mutableLiveData<ProjectListViewState>()
    val viewState: LiveData<ProjectListViewState> = state

    init {
        loadProjectList()
    }

    fun loadProjectList() {
        viewModelScope.launch(dispatcher) {
            getProjectListUseCase()
                .flowOn(dispatcher)
                .onStart { setLoadingState() }
                .catch { setErrorState() }
                .cachedIn(viewModelScope)
                .collect { setSuccessState(it) }
        }
    }

    private fun setLoadingState() {
        state.postValue(ProjectListViewState(isLoading = true))
    }

    private fun setErrorState() {
        state.postValue(ProjectListViewState(isErrorVisible = true))
    }

    private fun setSuccessState(projectItems: PagingData<ProjectItem>) {
        state.postValue(ProjectListViewState(projects = projectItems))
    }
}