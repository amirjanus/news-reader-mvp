package com.example.newsreader.view.main;

import com.example.newsreader.data.MainMvpModel;
import com.example.newsreader.data.constants.NewsSourceNames;
import com.example.newsreader.utils.models.Article;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class MainPresenter implements MainMvpPresenter {
    
    private static final String TAG = MainPresenter.class.getSimpleName();
    
    private MainMvpView mView;
    private MainMvpModel mModel;
    
    private CompositeDisposable mDisposable;
    
    @Inject
    public MainPresenter( MainMvpView view, MainMvpModel mainMvpModel ) {
        mView = view;
        mModel = mainMvpModel;
    }
    
    /**
     * Called when the view is ready to be used.
     */
    @Override
    public void ready() {
        fetchArticles();
    }
    
    /**
     * Called when the view should not be used anymore.
     */
    @Override
    public void stop() {
        dispose();
    }
    
    /**
     * Called when user clicks on item in Article RecyclerView.
     *
     * @param articleIndex Position index of selected article item.
     */
    @Override
    public void articleSelected( int articleIndex ) {
        mView.startArticleActivity( articleIndex );
    }
    
    /**
     * Get articles from database or api.
     */
    private void fetchArticles() {
        mDisposable = new CompositeDisposable();
    
        // Show loader and clean the view.
        mView.showProgressLoader( true );
        mView.showArticles( new ArrayList<>( 0 ) );
    
        // Try to get news data from ( local ) database.
        Disposable disposable = mModel.getLocalArticles( NewsSourceNames.BBC.getString(), getDate() )
                .subscribe(
                        articleList -> onGetLocalNewsSuccess( articleList ),
                        throwable -> getNewsFromApi() );
    
        mDisposable.add( disposable );
    }
    
    /**
     * Dispose of disposables.
     */
    private void dispose() {
        if ( mDisposable != null && !mDisposable.isDisposed() )
            mDisposable.dispose();
    }
    
    /**
     * Subtract 5 minutes from current Date.
     *
     * @return Date.
     */
    private Date getDate() {
        Calendar calendar = Calendar.getInstance( TimeZone.getTimeZone( "UTC" ) );
        calendar.add( Calendar.MINUTE, -5 );
        
        return calendar.getTime();
    }
    
    /**
     * Called when Articles are successfully returned from local database.
     */
    private void onGetLocalNewsSuccess( List<Article> articleList ) {
        if ( articleList.size() > 0 ) {
            // Articles were found in local database. Show them in view.
            mView.showProgressLoader( false );
            mView.showArticles( articleList );
        } else
            // No articles in local database. Try to get them from api.
            getNewsFromApi();
    }
    
    /**
     * Try to get news from NewsApi.
     */
    private void getNewsFromApi() {
        Disposable disposable = mModel.getRemoteArticles( NewsSourceNames.BBC.getString(), "top" )
                .subscribe(
                        articleList -> {
                            mView.showProgressLoader( false );
                            mView.showArticles( articleList );
                        },
                        throwable -> {
                            mView.showProgressLoader( false );
                            mView.showErrorMessage();
                        }
                );
        
        mDisposable.add( disposable );
    }
    
}
