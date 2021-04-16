package com.kylix.submissionbfaa3.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kylix.submissionbfaa3.databinding.ItemUserListBinding
import com.kylix.submissionbfaa3.model.GithubUser
import kotlinx.android.synthetic.main.item_user_list.view.*

class UserAdapter(private val githubUsers: ArrayList<GithubUser>, private val clickListener: (String, View) -> Unit) : RecyclerView.Adapter<UserAdapter.UsersViewHolder>() {

    fun setData(items: List<GithubUser>){
        githubUsers.apply {
            clear()
            addAll(items)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        return UsersViewHolder(ItemUserListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) = holder.bind(githubUsers[position], clickListener)

    override fun getItemCount(): Int = githubUsers.size

    inner class UsersViewHolder(private val binding: ItemUserListBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(githubUser: GithubUser, click: (String, View) -> Unit) {
            binding.data = githubUser
            binding.root.transitionName = githubUser.login
            binding.root.setOnClickListener { click(githubUser.login, binding.root) }
        }
    }
}
