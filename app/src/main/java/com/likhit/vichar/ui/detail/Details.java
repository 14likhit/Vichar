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

public class Details extends BaseActivity {

    private String title;
    private String urlImage;
    private String content;
    private String date;

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
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_bookmark) {
            item.setIcon(android.R.drawable.btn_star_big_on );
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
