package mx.com.shellcore.android.rxretrofit.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_contributor.view.*
import mx.com.shellcore.android.rxretrofit.R
import mx.com.shellcore.android.rxretrofit.model.Contributor

class ContributorsAdapter(private var contributors: List<Contributor>) :
    RecyclerView.Adapter<ContributorsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_contributor,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(contributors[position])

    override fun getItemCount(): Int = contributors.size

    fun setContributors(contributors: List<Contributor>) {
        this.contributors = contributors
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val txtContributorName = view.txtContributorName
        private val txtContributorContributions = view.txtContributorContributions

        fun bind(contributor: Contributor) {
            txtContributorName.text = contributor.login
            txtContributorContributions.text = "${contributor.contributions}"
        }
    }
}