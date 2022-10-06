package com.githubexplorer.features.repolist.di

import androidx.paging.PagingSource
import com.githubexplorer.features.core.utils.data.remote.RemoteBuilder
import com.githubexplorer.features.core.utils.data.remote.RetrofitBuilderImpl
import com.githubexplorer.features.repolist.data.ProjectListMapper
import com.githubexplorer.features.repolist.data.model.ProjectItemResponse
import com.githubexplorer.features.repolist.data.pagingsource.ProjectsListPagingSource
import com.githubexplorer.features.repolist.data.repository.ProjectsListRepositoryImpl
import com.githubexplorer.features.repolist.data.service.ProjectsListService
import com.githubexplorer.features.repolist.domain.usecase.GetProjectListUseCase
import com.githubexplorer.features.repolist.domain.repository.ProjectsListRepository
import com.githubexplorer.features.repolist.presentation.viewmodel.ProjectListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val projectListModule = module {
    factory<RemoteBuilder> { RetrofitBuilderImpl() }
    factory<ProjectsListService> {
        get<RemoteBuilder>().build().create(ProjectsListService::class.java)
    }
    factory<PagingSource<Int, ProjectItemResponse>> { ProjectsListPagingSource(service = get()) }

    factory<ProjectsListRepository> {
        ProjectsListRepositoryImpl(
            service = get(),
            mapper = ProjectListMapper()
        )
    }

    viewModel {
        ProjectListViewModel(getProjectListUseCase = GetProjectListUseCase(repository = get()))
    }
}