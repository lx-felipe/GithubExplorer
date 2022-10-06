package com.githubexplorer.features.repolist.data.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.githubexplorer.features.repolist.data.model.ProjectItemResponse
import com.githubexplorer.features.repolist.data.service.ProjectsListService
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.MissingFieldException
import retrofit2.HttpException
import java.io.IOException

private const val ONE_INDEX = 1
private const val PAGE_CONTENT_SIZE = 10

class ProjectsListPagingSource(
    private val service: ProjectsListService
) : PagingSource<Int, ProjectItemResponse>() {

    override fun getRefreshKey(state: PagingState<Int, ProjectItemResponse>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(ONE_INDEX)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(ONE_INDEX)
        }
    }

    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ProjectItemResponse> {
        return try {
            val pageNumber = params.key ?: ONE_INDEX
            val projects = service.requestProjectList(pageNumber).projects

            val nextKey = if (projects.isEmpty()) {
                null
            } else {
                pageNumber + (params.loadSize / PAGE_CONTENT_SIZE)
            }

            LoadResult.Page(
                data = projects,
                prevKey = if (pageNumber == ONE_INDEX) null else pageNumber - ONE_INDEX,
                nextKey = nextKey
            )
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        } catch (exception: MissingFieldException) {
            LoadResult.Error(exception)
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        }
    }
}