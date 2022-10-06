package com.githubexplorer.features.projectlist.presentation.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.githubexplorer.databinding.ItemProjectListBinding
import com.githubexplorer.features.projectlist.domain.model.ProjectItem

class ProjectItemViewHolder(
    private val binding: ItemProjectListBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ProjectItem) = with(binding) {
        nameTextView.text = item.name
        fullnameTextView.text = item.fullName
        gradeTextView.text = item.starsCount.toString()
        forkTextView.text = item.forksCount.toString()

        Glide.with(binding.root)
            .load(item.ownerAvatarUrl)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .circleCrop()
            .into(avatarImageView)
    }

    companion object {
        fun create(parent: ViewGroup): ProjectItemViewHolder {
            return ProjectItemViewHolder(
                ItemProjectListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        }
    }
}