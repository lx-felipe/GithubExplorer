package com.githubexplorer.features.repolist.presentation.viewholder

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.githubexplorer.databinding.ItemProjectListLoadingBinding

class ProjectListLoadingViewHolder(
    private val binding: ItemProjectListLoadingBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind() {
        binding.footerLoadingView.isVisible = true
    }
}