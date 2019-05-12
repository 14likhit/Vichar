package com.likhit.vichar.ui.home;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.likhit.vichar.R;
import com.likhit.vichar.data.model.Article;
import com.likhit.vichar.databinding.LayoutNewsBinding;
import com.likhit.vichar.databinding.NewsCardItemBinding;
import com.likhit.vichar.ui.detail.Details;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private List<Article> newsArticle;
    private LayoutInflater layoutInflater;
    private Context context;

    public NewsAdapter(Context context) {
        this.context = context;
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

        return new NewsAdapter.NewsViewHolder(layoutInflater.inflate(R.layout.news_card_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.NewsViewHolder newsViewHolder, int i) {
        i = i % newsArticle.size();
        final Article article = newsArticle.get(i);
        newsViewHolder.binding.tvNewsTitle.setText(article.getTitle());
        newsViewHolder.binding.dateCard.setText(article.getPublishedAt());

//        Picasso.Builder builder = new Picasso.Builder(context);
//        builder.downloader(new OkHttp3Downloader(context));
//        builder.build().load(article.getUrlTOImage())
//                .placeholder((R.mipmap.ic_vichar_round))
//                .error(R.drawable.vichar)
//                .into(newsViewHolder.binding.ivNews);
//        Picasso.get().load(article.getUrlTOImage())
//                .resize(50, 50)
//                .centerCrop()
//                .into(newsViewHolder.binding.ivNews);
        Glide.with(context)
                .load(article.getUrlTOImage())
                .into(newsViewHolder.binding.ivNews);

        newsViewHolder.binding.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, Details.class);
                i.putExtra("title", article.getTitle());
                i.putExtra("image", article.getUrlTOImage());
                i.putExtra("content", article.getContent());
                i.putExtra("date", article.getPublishedAt());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (newsArticle != null) {
//            return newsArticle.size();
            return Integer.MAX_VALUE;
        }
        return 0;
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {
        private NewsCardItemBinding binding;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }

    public void clearAll() {
        newsArticle.clear();
        notifyDataSetChanged();
    }
}
