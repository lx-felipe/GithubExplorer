package com.githubexplorer.features.repolist.data.repository

import androidx.paging.PagingData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.map
import com.githubexplorer.features.repolist.data.ProjectListMapper
import com.githubexplorer.features.repolist.data.pagingsource.ProjectsListPagingSource
import com.githubexplorer.features.repolist.data.service.ProjectsListService
import com.githubexplorer.features.repolist.domain.model.ProjectItem
import com.githubexplorer.features.repolist.domain.repository.ProjectsListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private const val PAGE_CONTENT_SIZE = 10

class ProjectsListRepositoryImpl(
    private val service: ProjectsListService,
    private val mapper: ProjectListMapper
) : ProjectsListRepository {

    override fun getProjectsList(): Flow<PagingData<ProjectItem>> {
        return Pager(
            config = PagingConfig(pageSize = PAGE_CONTENT_SIZE),
            pagingSourceFactory = { ProjectsListPagingSource(service) }
        ).flow.map { pagingData ->
            pagingData.map(mapper::invoke)
        }
    }
}