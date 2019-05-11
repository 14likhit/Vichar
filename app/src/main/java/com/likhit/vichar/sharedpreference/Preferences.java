package com.likhit.vichar.sharedpreference;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.likhit.vichar.data.model.Article;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Preferences {

    private static final String PREF_APP_NAME = "app_pref_name_6598";

    private static final String PREF_BOOKMARK_ARTICLE = "BOOKMARK_ARTICLE";

    private static Preferences mInstance;
    private SharedPreferences sharedPreferences;

    public static void initialize(Context context) {
        if (mInstance == null) {
            mInstance = new Preferences(context);
        }
    }

    private Preferences(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_APP_NAME, Context.MODE_PRIVATE);
    }

    public static Preferences getInstance() {
        if (mInstance == null) {
            throw new IllegalStateException("Call initialize() once before using the Shared Preferences");
        }
        return mInstance;
    }

    public void bookMarkArticle(Article article) {
        List<Article> favourites = getBookmarkedArticle();
        if (favourites == null) {
            favourites = new ArrayList<Article>();
        }
        favourites.add(article);
        sharedPreferences.edit().putString(PREF_BOOKMARK_ARTICLE, new Gson().toJson(favourites)).apply();
    }


    public ArrayList<Article> getBookmarkedArticle() {
        if (sharedPreferences.contains(PREF_BOOKMARK_ARTICLE)) {
            Article[] bookmarkItems = new Gson().fromJson(sharedPreferences.getString(PREF_BOOKMARK_ARTICLE, ""), Article[].class);
            List<Article> bookmarkArticles = Arrays.asList(bookmarkItems);
            return new ArrayList<Article>(bookmarkArticles);
        } else {
            return null;
        }
    }

}
