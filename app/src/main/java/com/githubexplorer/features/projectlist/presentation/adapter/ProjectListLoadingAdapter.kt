package com.githubexplorer.features.projectlist.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.githubexplorer.databinding.ItemProjectListLoadingBinding
import com.githubexplorer.features.projectlist.presentation.viewholder.ProjectListLoadingViewHolder

class ProjectListLoadingAdapter : LoadStateAdapter<ProjectListLoadingViewHolder>() {

    override fun onBindViewHolder(holder: ProjectListLoadingViewHolder, loadState: LoadState) {
        holder.bind()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): ProjectListLoadingViewHolder {
        return ProjectListLoadingViewHolder(
            ItemProjectListLoadingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}