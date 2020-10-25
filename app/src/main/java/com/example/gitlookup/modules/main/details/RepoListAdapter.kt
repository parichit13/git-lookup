package com.example.gitlookup.modules.main.details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gitlookup.R
import com.example.gitlookup.models.GithubRepo
import com.example.gitlookup.utils.applyText
import kotlinx.android.synthetic.main.item_repo_list.view.*

class RepoListAdapter : ListAdapter<GithubRepo, RepoViewHolder>(RepoListDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        return RepoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_repo_list, parent, false))
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
}

class RepoListDiffCallback : DiffUtil.ItemCallback<GithubRepo>() {
    override fun areItemsTheSame(oldItem: GithubRepo, newItem: GithubRepo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: GithubRepo, newItem: GithubRepo): Boolean {
        return oldItem == newItem
    }

}

class RepoViewHolder(mainView: View) : RecyclerView.ViewHolder(mainView) {

    fun onBind(repo: GithubRepo) {
        itemView.apply {

            name?.applyText(repo.name)
            url?.applyText(repo.url)
            description?.applyText(repo.description)
            language?.applyText(repo.language)
            forks?.applyText(repo.forkCount?.toString()?.plus(" forks"))
            stars?.applyText(repo.starCount?.toString()?.plus(" stars"))

        }
    }
}
