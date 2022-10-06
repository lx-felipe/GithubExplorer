package com.githubexplorer.features.repolist.domain.repository

import androidx.paging.PagingData
import com.githubexplorer.features.repolist.domain.model.ProjectItem
import kotlinx.coroutines.flow.Flow

interface ProjectsListRepository {
    fun getProjectsList(): Flow<PagingData<ProjectItem>>
}