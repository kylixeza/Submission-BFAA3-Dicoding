package com.kylix.demosubmissionbfaa

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kylix.demosubmissionbfaa.databinding.ItemListUserBinding
import com.kylix.demosubmissionbfaa.model.User

class UserAdapter: RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private val listUser = ArrayList<User>()

    fun setAllData(data: List<User>) {
        listUser.apply {
            clear()
            addAll(data)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = ItemListUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(listUser[position])
    }

    override fun getItemCount(): Int = listUser.size

    inner class UserViewHolder(private val view: ItemListUserBinding): RecyclerView.ViewHolder(view.root) {

        fun bind(user: User) {

            view.apply {
                tvUsername.text = user.username
                tvName.text = user.name
            }

            Glide.with(itemView.context)
                .load(user.avatar)
                .apply(RequestOptions.circleCropTransform())
                .into(view.ivImgUser)
        }
    }
}