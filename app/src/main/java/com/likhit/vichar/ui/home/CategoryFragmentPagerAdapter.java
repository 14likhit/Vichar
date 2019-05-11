package com.likhit.vichar.ui.home;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.likhit.vichar.R;
import com.likhit.vichar.utils.AppConstants;

public class CategoryFragmentPagerAdapter extends FragmentPagerAdapter {

    private Context context;

    public CategoryFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case AppConstants.HOME:
                return HomeFragment.newInstance(AppConstants.CATEGORY_GENERAL);
            case AppConstants.BUSINESS:
                return HomeFragment.newInstance(AppConstants.CATEGORY_BUSINESS);
            case AppConstants.TECHNOLOGY:
                return HomeFragment.newInstance(AppConstants.CATEGORY_TECHNOLOGY);
            case AppConstants.SPORT:
                return HomeFragment.newInstance(AppConstants.CATEGORY_SPORTS);
            case AppConstants.ENTERTAINMENT:
                return HomeFragment.newInstance(AppConstants.CATEGORY_ENTERTAINMENT);
            case AppConstants.HEALTH:
                return HomeFragment.newInstance(AppConstants.CATEGORY_HEALTH);
            case AppConstants.SCIENCE:
                return HomeFragment.newInstance(AppConstants.CATEGORY_SCIENCE);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 7;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        int titleResId;
        switch (position) {
            case AppConstants.HOME:
                titleResId = R.string.ic_title_home;
                break;
            case AppConstants.BUSINESS:
                titleResId = R.string.ic_title_business;
                break;
            case AppConstants.TECHNOLOGY:
                titleResId = R.string.ic_title_technology;
                break;
            case AppConstants.SPORT:
                titleResId = R.string.ic_title_sport;
                break;
            case AppConstants.ENTERTAINMENT:
                titleResId = R.string.ic_title_entertainment;
                break;
            case AppConstants.HEALTH:
                titleResId = R.string.ic_title_health;
                break;
            case AppConstants.SCIENCE:
                titleResId = R.string.ic_title_science;
                break;
            default:
                titleResId = R.string.ic_title_home;
                break;
        }
        return context.getString(titleResId);
    }
}
