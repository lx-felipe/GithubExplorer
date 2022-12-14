package com.githubexplorer.features.projectlist.data.service

import com.githubexplorer.features.projectlist.data.model.ProjectListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ProjectsListService {

    @GET("search/repositories?q=language:kotlin&sort=stars")
    suspend fun requestProjectList(@Query("page") pageNumber: Int): ProjectListResponse
}