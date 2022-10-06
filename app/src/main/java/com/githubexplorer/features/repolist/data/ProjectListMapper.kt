package com.githubexplorer.features.repolist.data

import com.githubexplorer.features.repolist.data.model.ProjectItemResponse
import com.githubexplorer.features.repolist.domain.model.ProjectItem

class ProjectListMapper {
    operator fun invoke(source: ProjectItemResponse): ProjectItem {
        return ProjectItem(
            id = source.id,
            name = source.name,
            fullName = source.fullName,
            isPrivate = source.isPrivate,
            starsCount = source.starsCount,
            forksCount = source.forksCount,
            ownerAvatarUrl = source.owner.avatarUrl
        )
    }
}