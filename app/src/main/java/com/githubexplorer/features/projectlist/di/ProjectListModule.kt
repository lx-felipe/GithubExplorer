package com.githubexplorer.features.projectlist.di

import androidx.paging.PagingSource
import com.githubexplorer.core.utils.data.remote.RemoteBuilder
import com.githubexplorer.core.utils.data.remote.RetrofitBuilderImpl
import com.githubexplorer.features.projectlist.data.ProjectListMapper
import com.githubexplorer.features.projectlist.data.model.ProjectItemResponse
import com.githubexplorer.features.projectlist.data.pagingsource.ProjectsListPagingSource
import com.githubexplorer.features.projectlist.data.repository.ProjectsListRepositoryImpl
import com.githubexplorer.features.projectlist.data.service.ProjectsListService
import com.githubexplorer.features.projectlist.domain.usecase.GetProjectListUseCase
import com.githubexplorer.features.projectlist.domain.repository.ProjectsListRepository
import com.githubexplorer.features.projectlist.presentation.viewmodel.ProjectListViewModel
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