package com.githubexplorer.features.repolist.data.repository

import app.cash.turbine.test
import com.githubexplorer.features.repolist.ProjectListStubs.generateProjectsFirstPageResponse
import com.githubexplorer.features.repolist.data.ProjectListMapper
import com.githubexplorer.features.repolist.data.service.ProjectsListService
import com.githubexplorer.utils.CoroutineTestRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class ProjectsListRepositoryImplTest {

    @get:Rule
    val rule = CoroutineTestRule()

    private val mockedService = mockk<ProjectsListService>(relaxed = true)
    private val repository = ProjectsListRepositoryImpl(mockedService, ProjectListMapper())

    @Test
    fun `load should call service and return project list`() = runTest(rule.testDispatcher) {
        coEvery { mockedService.requestProjectList(any()) } returns generateProjectsFirstPageResponse()

        repository.getProjectsList()
            .test {
                awaitEvent()
            }
    }
}