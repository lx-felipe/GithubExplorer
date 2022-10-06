package com.githubexplorer.features.projectlist.data

import com.githubexplorer.features.projectlist.data.model.ProjectItemResponse
import com.githubexplorer.features.projectlist.domain.model.ProjectItem

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