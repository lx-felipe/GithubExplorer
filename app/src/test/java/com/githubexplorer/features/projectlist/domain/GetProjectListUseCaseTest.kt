package com.githubexplorer.features.projectlist.domain

import androidx.paging.PagingData
import app.cash.turbine.test
import com.githubexplorer.utils.CoroutineTestRule
import com.githubexplorer.features.projectlist.domain.model.ProjectItem
import com.githubexplorer.features.projectlist.domain.repository.ProjectsListRepository
import com.githubexplorer.features.projectlist.domain.usecase.GetProjectListUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
internal class GetProjectListUseCaseTest {

    @get:Rule
    val rule = CoroutineTestRule()

    private val mockedRepository = mockk<ProjectsListRepository>()
    private val useCase = GetProjectListUseCase(mockedRepository)

    @Test
    fun `invoke should call repository and return projects paging data`() = runTest(rule.testDispatcher) {
        val expectedPagingData = mockk<PagingData<ProjectItem>>()
        coEvery { mockedRepository.getProjectsList() } returns flowOf(expectedPagingData)

        val result = useCase.invoke()

        result.test {
            assertEquals(this.expectMostRecentItem(), expectedPagingData)
            awaitComplete()
        }
    }

    @Test
    fun `invoke should call repository to get projects paging data and return error`() = runTest(rule.testDispatcher) {
        coEvery { mockedRepository.getProjectsList() } returns flow { throw NullPointerException() }

        val result = useCase.invoke()

        result.test {
            assertThat(awaitError(), IsInstanceOf(NullPointerException::class.java))
        }
    }
}