package com.kinsideapp.rcc_firebase_news.features.news.presentation.ui.news

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kinsideapp.rcc_firebase_news.R
import com.kinsideapp.rcc_firebase_news.core.RouteToArticle
import com.kinsideapp.rcc_firebase_news.databinding.ArticleCardBinding
import com.kinsideapp.rcc_firebase_news.features.news.domain.entity.ArticleEntity

class NewsListAdapter(private val activity: Activity, val data: List<ArticleEntity>) :
    RecyclerView.Adapter<NewsListAdapter.VH>() {
    class VH(val view: ArticleCardBinding) : RecyclerView.ViewHolder(view.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = ArticleCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(binding)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.view.title.text = data[position].title
        Glide.with(holder.view.root).load(data[position].image).into(holder.view.image)
            .onLoadFailed(AppCompatResources.getDrawable(activity, R.drawable.default_image))
        holder.view.articleCard.setOnClickListener {
            RouteToArticle.article = ArticleEntity(
                title = data[position].title,
                article = data[position].article,
                image = data[position].image,
                id = data[position].id
            )
            RouteToArticle(activity).navigate()
        }
    }
}