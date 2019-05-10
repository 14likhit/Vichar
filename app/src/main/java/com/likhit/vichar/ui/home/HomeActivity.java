package com.likhit.vichar.ui.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.SwipeDismissBehavior;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;

import com.likhit.vichar.base.BaseActivity;
import com.likhit.vichar.R;
import com.likhit.vichar.data.model.Article;
import com.likhit.vichar.databinding.ActivityHomeBinding;

import java.util.List;

public class HomeActivity extends BaseActivity implements HomeContract.View, SwipeRefreshLayout.OnRefreshListener {
    private ActivityHomeBinding binding;
    private NewsAdapter adapter;

    private HomePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);

        setupToolbar("Vichar", true);
        initViews();

        presenter = new HomePresenter();
        presenter.attachView(this);
        presenter.getNewsArticle();

    }

    @Override
    protected void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }

    private void initViews() {
        if (binding.rvListNews.getLayoutManager() == null) {
            binding.rvListNews.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        }

        if (adapter == null) {
            adapter = new NewsAdapter();
        }
        binding.rvListNews.setAdapter(adapter);
    }

    @Override
    public void onNewsReceived(List<Article> articles) {
        if (articles.size() > 0) {
            adapter.setNewsArticle(articles);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onRefresh() {
        presenter.getNewsArticle();
    }
}
