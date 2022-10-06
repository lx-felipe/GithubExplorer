package com.githubexplorer.features.projectlist.presentation.viewmodel

import androidx.lifecycle.Observer
import androidx.paging.PagingData
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.githubexplorer.utils.TestLiveDataFactory
import com.githubexplorer.core.utils.presentation.ViewModelUtils
import com.githubexplorer.features.projectlist.domain.usecase.GetProjectListUseCase
import com.githubexplorer.features.projectlist.domain.model.ProjectItem
import com.githubexplorer.features.projectlist.presentation.state.ProjectListViewState
import com.githubexplorer.utils.CoroutineTestRule
import com.githubexplorer.utils.InstantExecutorExtension
import io.mockk.coEvery
import io.mockk.coVerifyOrder
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.runner.RunWith
import kotlin.test.assertTrue

@RunWith(AndroidJUnit4::class)
@ExtendWith(InstantExecutorExtension::class, CoroutineTestRule::class)
@OptIn(ExperimentalCoroutinesApi::class)
internal class ProjectListViewModelTest {

    private lateinit var viewModel: ProjectListViewModel

    @get:Rule
    val rule = CoroutineTestRule()

    private val mockedGetProjectListUseCase = mockk<GetProjectListUseCase>()

    private val observer: Observer<ProjectListViewState> = mockk(relaxed = true)

    @BeforeEach
    fun setup() {
        ViewModelUtils.setFactory(TestLiveDataFactory(observer))
    }

    @Test
    fun `init should call useCase and return error state`() = runTest {
        val expectedException = NullPointerException()
        coEvery { mockedGetProjectListUseCase() } returns flow { throw expectedException }

        createViewModel()

        coVerifyOrder {
            observer.onChanged(ProjectListViewState(isLoading = true))
            observer.onChanged(ProjectListViewState(isErrorVisible = true))
        }
    }

    @Test
    fun `init should call useCase and return success state`() = runTest {
        val expectedPagingData = mockk<PagingData<ProjectItem>>(relaxed = true)
        coEvery { mockedGetProjectListUseCase() } returns flowOf(expectedPagingData)

        createViewModel()

        coVerifyOrder {
            observer.onChanged(ProjectListViewState(isLoading = true))
            observer.onChanged(any())
        }
        with(viewModel.viewState.value as ProjectListViewState) {
            assertTrue { !isLoading }
            assertTrue { !isErrorVisible }
            assertTrue { projects != null }
        }
    }

    private fun createViewModel() {
        viewModel = ProjectListViewModel(
            getProjectListUseCase = mockedGetProjectListUseCase,
            dispatcher = rule.testDispatcher
        )
    }
}