package com.likhit.vichar.ui.home;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.likhit.vichar.base.BaseActivity;
import com.likhit.vichar.R;
import com.likhit.vichar.data.model.Article;
import com.likhit.vichar.databinding.ActivityHomeBinding;
import com.likhit.vichar.sharedpreference.Preferences;
import com.likhit.vichar.ui.bookmarks.BookmarksActivity;
import com.likhit.vichar.ui.login.LoginActivity;
import com.likhit.vichar.utils.AppConstants;

import java.util.List;

public class HomeActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {
    private ActivityHomeBinding binding;

    private ViewPager viewPager;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);

        setupToolbar("Vichar", false);

        Preferences.initialize(this);

        initViews();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initViews() {
        toolbar = findViewById(R.id.toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, binding.drawerLayout, toolbar, R.string.open_nav_drawer, R.string.nav_close_drawer);
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Find the view pager that will allow the user to swipe between fragments
        viewPager = findViewById(R.id.viewpager);

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
        // Set gravity for tab bar
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        binding.navView.setNavigationItemSelectedListener(this);
        if (Preferences.getInstance().getUserLoggedIn()) {
            View headerView = binding.navView.getHeaderView(0);
            TextView tvUserName = (TextView) headerView.findViewById(R.id.tvUserName);
            tvUserName.setText(Preferences.getInstance().getPrefUsername());
            TextView tvUserId = (TextView) headerView.findViewById(R.id.tvUserLoginId);
            tvUserId.setText(Preferences.getInstance().getPrefUserid());
            Menu menu = binding.navView.getMenu();
            MenuItem login = menu.findItem(R.id.nav_account);
            login.setTitle("LogOut");
        } else {
            View headerView = binding.navView.getHeaderView(0);
            TextView tvUserName = (TextView) headerView.findViewById(R.id.tvUserName);
            tvUserName.setText("Guest");
            TextView tvUserId = (TextView) headerView.findViewById(R.id.tvUserLoginId);
            tvUserId.setText("guest");
            Menu menu = binding.navView.getMenu();
            MenuItem login = menu.findItem(R.id.nav_account);
            login.setTitle("LogIn");
        }

        // Set the default fragment when starting the app
//        onNavigationItemSelected(binding.navView.getMenu().getItem(0).setChecked(true));

//      Set category fragment pager adapter
        CategoryFragmentPagerAdapter pagerAdapter =
                new CategoryFragmentPagerAdapter(getSupportFragmentManager(), this);
        // Set the pager adapter onto the view pager
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(AppConstants.HOME);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        // Handle navigation view item clicks here.
        int id = menuItem.getItemId();

        // Switch Fragments in a ViewPager on clicking items in Navigation Drawer
        if (id == R.id.nav_bookmark) {
            this.startActivity(new Intent(this, BookmarksActivity.class));
        } else if (id == R.id.nav_language) {
            Log.d("Nav", "language");
        } else if (id == R.id.nav_settings) {
            Log.d("Nav", "settings");
        } else if (id == R.id.nav_account) {
            if (Preferences.getInstance().getUserLoggedIn()) {
                Toast.makeText(this, "Successfully LoggedOut", Toast.LENGTH_SHORT).show();
                Preferences.getInstance().setUserLoggedIn(false);
                Intent i = new Intent(this, HomeActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                this.startActivity(i);
            } else {
                this.startActivity(new Intent(this, LoginActivity.class));
            }
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
