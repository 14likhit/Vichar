package com.likhit.vichar.ui.detail;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.bumptech.glide.Glide;
import com.likhit.vichar.R;
import com.likhit.vichar.base.BaseActivity;
import com.likhit.vichar.data.model.Article;
import com.likhit.vichar.databinding.ActivityDetailsBinding;
import com.likhit.vichar.sharedpreference.Preferences;

import java.util.ArrayList;
import java.util.List;

public class Details extends BaseActivity {

    private String title;
    private String urlImage;
    private String content;
    private String date;
    private boolean articleExists = false;

    private ActivityDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details);

        setupToolbar("Vichar", true);

        if (getIntent().getExtras() != null) {
            title = getIntent().getStringExtra("title");
            urlImage = getIntent().getStringExtra("image");
            content = getIntent().getStringExtra("content");
            date = getIntent().getStringExtra("date");
        }

        initView();

    }

    private void initView() {
        if (title != null) {
            binding.tvNewsTitle.setText(title);
        }
        if (urlImage != null) {
            Glide.with(this)
                    .load(urlImage)
                    .into(binding.ivNews);
        }
        if (content != null) {
            binding.tvNewsArticle.setText(content);
        }
        if (date != null) {
            binding.dateCard.setText(date);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bookmark, menu);
        if (Preferences.getInstance().getBookmarkedArticle() != null) {
            if (Preferences.getInstance().getBookmarkedArticle().size() > 0) {

                for (Article article : Preferences.getInstance().getBookmarkedArticle()) {
                    if (article.getTitle().equalsIgnoreCase(title)) {
                        articleExists = true;
                        break;
                    }
                }
                if (articleExists) {
                    menu.findItem(R.id.menu_bookmark).setIcon(android.R.drawable.btn_star_big_on);
                }

            }
        }

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_bookmark && !articleExists) {
            if (Preferences.getInstance().getBookmarkedArticle() == null) {
                Article article = new Article(null, null, title, null, null, urlImage, date, content);
                Preferences.getInstance().bookMarkArticle(article);
            } else if (Preferences.getInstance().getBookmarkedArticle().isEmpty()) {
                Article article = new Article(null, null, title, null, null, urlImage, date, content);
                Preferences.getInstance().bookMarkArticle(article);
            } else if (Preferences.getInstance().getBookmarkedArticle().size() > 0) {
                boolean articleExists = false;
                for (Article article : Preferences.getInstance().getBookmarkedArticle()) {
                    if (article.getTitle().equalsIgnoreCase(title)) {
                        Preferences.getInstance().bookMarkArticle(article);
                        articleExists = true;
                    }
                }
                if (!articleExists) {
                    Article article = new Article(null, null, title, null, null, urlImage, date, content);
                    Preferences.getInstance().bookMarkArticle(article);
                }
            }
            item.setIcon(android.R.drawable.btn_star_big_on);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
