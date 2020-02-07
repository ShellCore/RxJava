package mx.com.shellcore.android.rxretrofit.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_repo.view.*
import mx.com.shellcore.android.rxretrofit.R
import mx.com.shellcore.android.rxretrofit.model.GithubRepo

class RepositoryAdapter(private var repos: List<GithubRepo>) :
    RecyclerView.Adapter<RepositoryAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_repo, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(repos[position])

    override fun getItemCount(): Int = repos.size

    fun setRepos(list: List<GithubRepo>) {
        this.repos = list
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val txtRepoName = view.txtRepoName
        val txtRepoLanguage = view.txtRepoLanguage
        val txtRepoStars = view.txtRepoStars

        fun bind(repo: GithubRepo) {
            txtRepoName.text = repo.name
            txtRepoLanguage.text = repo.language
            txtRepoStars.text = "${repo.stargazersCount}"
        }
    }
}