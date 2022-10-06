package com.githubexplorer.features.repolist.domain.model

data class ProjectItem(
    val id: Int,
    val name: String,
    val fullName: String,
    val isPrivate: Boolean,
    val starsCount: Int,
    val forksCount: Int,
    val ownerAvatarUrl: String
)