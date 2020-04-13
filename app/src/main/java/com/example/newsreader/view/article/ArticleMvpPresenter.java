package com.example.newsreader.view.article;

import com.example.newsreader.view.base.BaseMvpPresenter;

public interface ArticleMvpPresenter extends BaseMvpPresenter {
    
    /**
     * Called when new page is set in ViewPager.
     *
     * @param index Position index of the new ViewPager page.
     */
    void articlePageChanged( int index );
    
    /**
     * Get articles from database.
     *
     * @param articleIndex Position index of current ViewPager page.
     */
    void fetchArticles( int articleIndex );
    
}
