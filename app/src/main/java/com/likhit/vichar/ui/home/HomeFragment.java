package com.likhit.vichar.ui.home;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.likhit.vichar.R;
import com.likhit.vichar.base.BaseFragment;
import com.likhit.vichar.data.model.Article;
import com.likhit.vichar.databinding.FragmentHomeBinding;
import com.likhit.vichar.utils.Utils;

import java.util.List;

public class HomeFragment extends BaseFragment implements HomeContract.View {

    private FragmentHomeBinding binding;

    private NewsAdapter adapter;

    private HomePresenter presenter;

    private String category;

    public HomeFragment() {
    }

    public static HomeFragment newInstance(String category) {
        HomeFragment homeFragment = new HomeFragment();
        Bundle bundle = new Bundle();
        bundle.putString("category", category);
        homeFragment.setArguments(bundle);
        return homeFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){
            category=getArguments().getString("category");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        binding.swipeRefresh.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        binding.swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.i("TAG", "onRefresh called from SwipeRefreshLayout");
                // restart the loader
                initiateRefresh();
                Toast.makeText(getActivity(), "UpdateNow",
                        Toast.LENGTH_SHORT).show();
            }
        });

        if (binding.rvListNews.getLayoutManager() == null) {
            binding.rvListNews.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        }

        if (adapter == null) {
            adapter = new NewsAdapter(getActivity());
        }
        binding.rvListNews.setAdapter(adapter);

        presenter = new HomePresenter();
        presenter.attachView(this);

        // Check for network connectivity and initialize the loader
        initializeLoader(Utils.isConnected(getActivity()));

        return binding.getRoot();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        presenter.detachView();
    }

    @Override
    public void onResume() {
        super.onResume();
        restartLoader(Utils.isConnected(getActivity()));
    }


    private void initializeLoader(boolean isConnected) {
        if (isConnected) {
            presenter.getNewsArticle(category);
        } else {

            binding.loadingIndicator.setVisibility(View.GONE);
            // Update empty state with no connection error message and image
            binding.emptyView.setText("Check Internet Connection");
        }
    }

    private void restartLoader(boolean isConnected) {
        if (isConnected) {
            presenter.getNewsArticle(category);
        } else {
            binding.loadingIndicator.setVisibility(View.GONE);
            // Update empty state with no connection error message and image
            binding.emptyView.setText("Check Internet COnnection");

            // Hide SwipeRefreshLayout
            binding.swipeRefresh.setVisibility(View.GONE);
        }
    }

    private void initiateRefresh() {
        restartLoader(Utils.isConnected(getActivity()));
    }

    @Override
    public void onNewsReceived(List<Article> articles) {
        if (articles.size() > 0) {
            adapter.setNewsArticle(articles);
            adapter.notifyDataSetChanged();
        } else {
            // Otherwise, display error
            // First, hide loading indicator so error message will be visible
//            binding.loadingIndicator.setVisibility(View.GONE);
            // Update empty state with no connection error message and image
            binding.emptyView.setText("Check Internet COnnection");
//            mEmptyStateTextView.setCompoundDrawablesWithIntrinsicBounds(Constants.DEFAULT_NUMBER,
//                    R.drawable.ic_network_check, Constants.DEFAULT_NUMBER, Constants.DEFAULT_NUMBER);

            // Hide SwipeRefreshLayout
            binding.swipeRefresh.setVisibility(View.GONE);
        }
        // First, hide loading indicator so error message will be visible
        binding.loadingIndicator.setVisibility(View.GONE);
        binding.swipeRefresh.setRefreshing(false);
    }

}
