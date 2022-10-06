package com.githubexplorer.features.projectlist.data.datasource.impl

import androidx.paging.PagingSource
import com.githubexplorer.utils.CoroutineTestRule
import com.githubexplorer.features.projectlist.ProjectListStubs.generateProjectsFirstPageResponse
import com.githubexplorer.features.projectlist.ProjectListStubs.generateProjectsSecondPageResponse
import com.githubexplorer.features.projectlist.data.pagingsource.ProjectsListPagingSource
import com.githubexplorer.features.projectlist.data.service.ProjectsListService
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.Test
import java.io.IOException
import kotlin.test.assertTrue

private const val PAGE_SIZE = 10
private const val FIRST_PAGE_NUMBER = 0
private const val SECOND_PAGE_NUMBER = 1

@OptIn(ExperimentalCoroutinesApi::class)
@ExtendWith(CoroutineTestRule::class)
internal class ProjectsListPagingSourceTest {

    @get:Rule
    val rule = CoroutineTestRule()

    private val mockedService = mockk<ProjectsListService>()
    private val pagingSource = ProjectsListPagingSource(mockedService)

    @Test
    fun `load should call service and return project list`() = runTest(rule.testDispatcher) {
        coEvery { mockedService.requestProjectList(FIRST_PAGE_NUMBER) } returns generateProjectsFirstPageResponse()

        val result = pagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = FIRST_PAGE_NUMBER,
                loadSize = PAGE_SIZE,
                placeholdersEnabled = false
            )
        )

        assertTrue {
            (result as PagingSource.LoadResult.Page).data == generateProjectsFirstPageResponse().projects
        }
    }

    @Test
    fun `load should call service twice and return first and second page`() =
        runTest(rule.testDispatcher) {
            coEvery {
                mockedService.requestProjectList(FIRST_PAGE_NUMBER)
            } returns generateProjectsFirstPageResponse()

            coEvery {
                mockedService.requestProjectList(SECOND_PAGE_NUMBER)
            } returns generateProjectsSecondPageResponse()

            val firstPageResult = pagingSource.load(
                PagingSource.LoadParams.Refresh(
                    key = FIRST_PAGE_NUMBER,
                    loadSize = PAGE_SIZE,
                    placeholdersEnabled = false
                )
            )

            assertTrue {
                (firstPageResult as PagingSource.LoadResult.Page).data == generateProjectsFirstPageResponse().projects
            }

            val secondPageResult = pagingSource.load(
                PagingSource.LoadParams.Refresh(
                    key = SECOND_PAGE_NUMBER,
                    loadSize = PAGE_SIZE,
                    placeholdersEnabled = false
                )
            )

            assertTrue {
                (secondPageResult as PagingSource.LoadResult.Page).data == generateProjectsSecondPageResponse().projects
            }
        }

    @Test
    fun `load should call service to get project list and return error`() =
        runTest(rule.testDispatcher) {
            val expectedError = IOException()
            coEvery {
                mockedService.requestProjectList(FIRST_PAGE_NUMBER)
            } answers { throw expectedError }

            val result = pagingSource.load(
                PagingSource.LoadParams.Refresh(
                    key = FIRST_PAGE_NUMBER,
                    loadSize = PAGE_SIZE,
                    placeholdersEnabled = false
                )
            )

            assertTrue {
                (result as PagingSource.LoadResult.Error).throwable == expectedError
            }
        }
}