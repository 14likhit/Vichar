package com.likhit.vichar.ui.home;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.likhit.vichar.R;
import com.likhit.vichar.data.model.Article;
import com.likhit.vichar.databinding.LayoutNewsBinding;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private List<Article> newsArticle;
    private LayoutInflater layoutInflater;

    public NewsAdapter() {
    }

    public NewsAdapter(List<Article> newsArticle) {
        this.newsArticle = newsArticle;
    }

    public void setNewsArticle(List<Article> newsArticle) {
        this.newsArticle = newsArticle;
    }

    @NonNull
    @Override
    public NewsAdapter.NewsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(viewGroup.getContext());
        }

        return new NewsAdapter.NewsViewHolder(layoutInflater.inflate(R.layout.layout_news, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.NewsViewHolder newsViewHolder, int i) {
        final Article article = newsArticle.get(i);
        newsViewHolder.binding.tvNewsTitle.setText(article.getTitle());
    }

    @Override
    public int getItemCount() {
        if (newsArticle != null) {
            return newsArticle.size();
        }
        return 0;
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {
        private LayoutNewsBinding binding;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
