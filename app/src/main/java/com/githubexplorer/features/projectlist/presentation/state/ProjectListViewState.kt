package com.githubexplorer.features.projectlist.presentation.state

import androidx.paging.PagingData
import com.githubexplorer.features.projectlist.domain.model.ProjectItem

data class ProjectListViewState(
    val isLoading: Boolean = false,
    val isErrorVisible: Boolean = false,
    val projects: PagingData<ProjectItem>? = null
)