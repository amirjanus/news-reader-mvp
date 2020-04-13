package com.example.newsreader.view.main;

import com.example.newsreader.utils.models.Article;

import java.util.List;

public interface MainMvpView {
    
    /**
     * Starts ArticleActivity.
     *
     * @param articleIndex Position index of selected article item.
     */
    void startArticleActivity( int articleIndex );
    
    /**
     * Display Articles list in view.
     *
     * @param articleList List of Articles to show.
     */
    void showArticles( List<Article> articleList );
    
    /**
     * Shows ProgressBar in view.
     *
     * @param show True to show ProgressBar, false to hide it.
     */
    void showProgressLoader( boolean show );
    
    /**
     * Show error message to user.
     */
    void showErrorMessage();
    
}
