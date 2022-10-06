package com.githubexplorer.features.projectlist.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProjectListResponse(
    @SerialName("items") val projects: List<ProjectItemResponse>
)