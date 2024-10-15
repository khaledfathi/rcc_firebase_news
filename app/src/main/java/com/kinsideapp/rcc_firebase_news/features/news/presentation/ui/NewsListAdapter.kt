//package com.kinsideapp.rcc_firebase_news.features.news.presentation.activity
//
//import android.app.Activity
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.kinsideapp.rcc_firebase_news.features.news.data.model.ArticleModel
//
//class NewsListAdapter(val activity: Activity, val data: List<ArticleModel>) :
//    RecyclerView.Adapter<NewsListAdapter.VH>() {
//    class VH(view: View) : RecyclerView.ViewHolder(view) {
//
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
//        return VH(
//            LayoutInflater.from(parent.context).inflate(R. , parent , false)
//        )
//    }
//
//    override fun getItemCount() = data.size
//
//    override fun onBindViewHolder(holder: VH, position: Int) {
//    }
//}