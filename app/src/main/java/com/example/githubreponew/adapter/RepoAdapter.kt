package com.example.githubreponew.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.githubreponew.R
import com.example.githubreponew.model.Repository
import kotlinx.android.synthetic.main.item_row.view.*

class RepoAdapter(private val context: Context, private val list: List<Repository>) :
    RecyclerView.Adapter<RepoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.repo_name.text = list[position].name
        holder.repo_description.text = list[position].description

        //open github repo url in browser
        holder.itemView.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(list[position].html_url))
            context.startActivity(intent)
        }

        //setting the color of repo_title and description
        holder.repo_name.setTextColor(ContextCompat.getColor(context, R.color.card_title))
        holder.repo_description.setTextColor(ContextCompat.getColor(context, R.color.card_description))

        //sharing repo_name and link to other apps using sharesheet
        val repo = list[position].name
        val link = list[position].html_url
        holder.shareBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, "$repo\n$link")
            context.startActivity(Intent.createChooser(intent, "Share repository via"))
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val repo_name = itemView.repo_name
        val repo_description = itemView.description
        val shareBtn = itemView.btn_share
    }
}
