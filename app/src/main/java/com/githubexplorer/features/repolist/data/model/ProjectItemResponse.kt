package com.githubexplorer.features.repolist.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProjectItemResponse(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("full_name") val fullName: String,
    @SerialName("private") val isPrivate: Boolean,
    @SerialName("stargazers_count") val starsCount: Int,
    @SerialName("forks_count") val forksCount: Int,
    @SerialName("owner") val owner: ProjectOwner
)

@Serializable
data class ProjectOwner(
    @SerialName("avatar_url") val avatarUrl: String
)