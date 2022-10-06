package com.githubexplorer.features.repolist

import androidx.paging.PagingData
import com.githubexplorer.features.repolist.data.model.ProjectItemResponse
import com.githubexplorer.features.repolist.data.model.ProjectListResponse
import com.githubexplorer.features.repolist.data.model.ProjectOwner
import com.githubexplorer.features.repolist.domain.model.ProjectItem

object ProjectListStubs {

    fun generateProjectsFirstPage() = listOf(
        ProjectItem(
            id = 1,
            name = "okhttp",
            fullName = "square/okhttp",
            isPrivate = false,
            starsCount = 49999,
            forksCount = 74844,
            ownerAvatarUrl = "url"
        ),
        ProjectItem(
            id = 2,
            name = "kotlin",
            fullName = "JetBrains/kotlin",
            isPrivate = false,
            starsCount = 49999,
            forksCount = 74844,
            ownerAvatarUrl = "url"
        )
    )

    fun generateProjectsFirstPageResponse() = ProjectListResponse(
        projects = listOf(
            ProjectItemResponse(
                id = 1,
                name = "okhttp",
                fullName = "square/okhttp",
                isPrivate = false,
                starsCount = 49999,
                forksCount = 74844,
                owner = ProjectOwner(
                    avatarUrl = "url"
                )
            ),
            ProjectItemResponse(
                id = 2,
                name = "kotlin",
                fullName = "JetBrains/kotlin",
                isPrivate = false,
                starsCount = 49999,
                forksCount = 74844,
                owner = ProjectOwner(
                    avatarUrl = "url"
                )
            )
        )
    )

    fun generateProjectsSecondPageResponse() = ProjectListResponse(
        projects = listOf(
            ProjectItemResponse(
                id = 3,
                name = "architecture-samples",
                fullName = "android/architecture-samples",
                isPrivate = false,
                starsCount = 49999,
                forksCount = 74844,
                owner = ProjectOwner(
                    avatarUrl = "url"
                )
            )
        )
    )

    fun generatePagingData() = PagingData.empty<ProjectItem>()
}