package com.likhit.vichar.ui.bookmarks;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.likhit.vichar.R;
import com.likhit.vichar.data.model.Article;
import com.likhit.vichar.databinding.LayoutBookmarkBinding;
import com.likhit.vichar.ui.detail.Details;
import com.likhit.vichar.ui.home.NewsAdapter;

import java.util.List;

public class BookmarkAdapter extends RecyclerView.Adapter<BookmarkAdapter.BookmarksViewHolder> {

    private List<Article> newsArticle;
    private LayoutInflater layoutInflater;
    private Context context;

    public BookmarkAdapter(Context context) {
        this.context = context;
    }

    public BookmarkAdapter(List<Article> newsArticle) {
        this.newsArticle = newsArticle;
    }

    public void setNewsArticle(List<Article> newsArticle) {
        this.newsArticle = newsArticle;
    }

    @NonNull
    @Override
    public BookmarkAdapter.BookmarksViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(viewGroup.getContext());
        }

        return new BookmarkAdapter.BookmarksViewHolder(layoutInflater.inflate(R.layout.layout_bookmark, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BookmarkAdapter.BookmarksViewHolder newsViewHolder, int i) {
        final Article article = newsArticle.get(i);
        newsViewHolder.binding.tvNewsTitle.setText(article.getTitle());
        newsViewHolder.binding.trailTextCard.setText(article.getContent());

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
            return newsArticle.size();
        }
        return 0;
    }

    public class BookmarksViewHolder extends RecyclerView.ViewHolder {
        private LayoutBookmarkBinding binding;

        public BookmarksViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }

    public void clearAll() {
        newsArticle.clear();
        notifyDataSetChanged();
    }

}
