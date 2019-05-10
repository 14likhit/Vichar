package com.likhit.vichar.ui.home;

import com.likhit.vichar.base.BaseView;
import com.likhit.vichar.data.model.Article;

import java.util.List;

public interface HomeContract {

    interface Presenter {
        void getNewsArticle();
    }

    interface View extends BaseView {
        void onNewsReceived(List<Article> articles);
    }

}
