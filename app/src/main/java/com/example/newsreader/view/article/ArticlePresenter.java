package com.example.newsreader.view.article;

import com.example.newsreader.data.MainMvpModel;
import com.example.newsreader.data.constants.NewsSourceNames;
import com.example.newsreader.utils.models.Article;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

public class ArticlePresenter implements ArticleMvpPresenter {
    
    private ArticleMvpView mView;
    private MainMvpModel mModel;
    
    private Disposable mDisposable;
    
    private List<Article> mArticleList = new ArrayList<>( 0 );
    
    private boolean mIsGetArticlesDone;
    
    @Inject
    public ArticlePresenter( ArticleMvpView newsMvpView, MainMvpModel mainMvpModel ) {
        mView = newsMvpView;
        mModel = mainMvpModel;
    }
    
    /**
     * Called when the view is ready to be used.
     */
    @Override
    public void ready() {}
    
    /**
     * Called when the view should not be used anymore.
     */
    @Override
    public void stop() {
        dispose();
    }
    
    /**
     * Get articles from database.
     *
     * @param articleIndex Position index of current ViewPager page.
     */
    @Override
    public void fetchArticles( int articleIndex ) {
        // Don't request articles from local database if they were already fetched.
        if ( mIsGetArticlesDone )
            return;
        
        // Get articles from database.
        mDisposable = mModel.getLocalArticles( NewsSourceNames.BBC.getString() )
                .subscribe(
                        articleList -> {
                            mArticleList = articleList;
                            
                            mView.showArticles( articleList, articleIndex );
                            
                            mIsGetArticlesDone = true;
                        },
                        throwable -> {
                            mView.showErrorMessage();
                            
                            mIsGetArticlesDone = true;
                        } );
    }
    
    /**
     * Called when new page is set in ViewPager.
     *
     * @param index Position index of the new ViewPager page.
     */
    @Override
    public void articlePageChanged( int index ) {
        mView.showToolbarTitle( mArticleList.get( index ).title );
    }
    
    /**
     * Dispose of disposables.
     */
    private void dispose() {
        if ( mDisposable != null && !mDisposable.isDisposed() )
            mDisposable.dispose();
    }
    
}
