package com.likhit.vichar.ui.bookmarks;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.likhit.vichar.R;
import com.likhit.vichar.base.BaseActivity;
import com.likhit.vichar.databinding.ActivityBookmarksBinding;
import com.likhit.vichar.sharedpreference.Preferences;

public class BookmarksActivity extends BaseActivity {

    private ActivityBookmarksBinding binding;
    private BookmarkAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_bookmarks);

        setupToolbar("Bookmarks", true);

        initViews();
    }

    private void initViews() {
        if (Preferences.getInstance().getBookmarkedArticle() == null) {
            binding.ivEmpty.setVisibility(View.VISIBLE);
        } else {
            if (binding.rvListNews.getLayoutManager() == null) {
                binding.rvListNews.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            }

            if (adapter == null) {
                adapter = new BookmarkAdapter(this);
            }
            adapter.setNewsArticle(Preferences.getInstance().getBookmarkedArticle());
            binding.rvListNews.setAdapter(adapter);
        }
    }
}
