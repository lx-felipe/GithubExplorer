package com.githubexplorer.features.projectlist.domain.repository

import androidx.paging.PagingData
import com.githubexplorer.features.projectlist.domain.model.ProjectItem
import kotlinx.coroutines.flow.Flow

interface ProjectsListRepository {
    fun getProjectsList(): Flow<PagingData<ProjectItem>>
}