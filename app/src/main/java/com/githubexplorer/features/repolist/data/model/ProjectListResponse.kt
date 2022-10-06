package com.githubexplorer.features.repolist.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProjectListResponse(
    @SerialName("items") val projects: List<ProjectItemResponse>
)