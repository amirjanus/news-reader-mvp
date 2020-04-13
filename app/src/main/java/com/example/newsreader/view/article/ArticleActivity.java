package com.example.newsreader.view.article;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.DialogFragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.newsreader.databinding.ActivityArticleBinding;
import com.example.newsreader.utils.dialogs.ErrorDialog;
import com.example.newsreader.utils.models.Article;
import com.example.newsreader.view.article.adapter.PagerArticleAdapter;
import com.example.newsreader.view.main.MainActivity;

import java.util.List;

import javax.inject.Inject;

import dagger.Lazy;
import dagger.android.support.DaggerAppCompatActivity;

public class ArticleActivity extends DaggerAppCompatActivity implements ArticleMvpView {
    
    private static final String KEY_CURRENT_PAGE = "KEY_CURRENT_PAGE";
    
    @Inject
    Lazy<ArticleMvpPresenter> mPresenter;
    
    @Inject
    PagerArticleAdapter mPagerArticleAdapter;
    
    private ViewPager2.OnPageChangeCallback mOnPageChangeCallback;
    
    private ActivityArticleBinding mBinding;
    
    private int mCurrentPage = 0;
    
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        
        // Set view binding.
        mBinding = ActivityArticleBinding.inflate( getLayoutInflater() );
        setContentView( mBinding.getRoot() );
        
        // Make view's content to appear behind the navigation bar.
        mBinding.getRoot().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION );
        
        // Get Article index from Intent that started this Activity.
        mCurrentPage = getIntent().getIntExtra( MainActivity.EXTRA_ARTICLE_INDEX, 0 );
        
        initPagerArticles();
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        
        mPresenter.get().ready();
        
        mPresenter.get().fetchArticles( mCurrentPage );
    }
    
    @Override
    protected void onPause() {
        super.onPause();
        
        mPresenter.get().stop();
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
    
        mBinding.pagerArticles.unregisterOnPageChangeCallback( mOnPageChangeCallback );
    }
    
    @Override
    protected void onSaveInstanceState( @NonNull Bundle outState ) {
        mCurrentPage = mBinding.pagerArticles.getCurrentItem();
        
        outState.putInt( KEY_CURRENT_PAGE, mCurrentPage );
        
        super.onSaveInstanceState( outState );
    }
    
    @Override
    protected void onRestoreInstanceState( Bundle savedInstanceState ) {
        super.onRestoreInstanceState( savedInstanceState );
        
        mCurrentPage = savedInstanceState.getInt( KEY_CURRENT_PAGE );
    }
    
    /**
     * Display Articles list in view.
     *
     * @param articleList List of Articles to show.
     * @param index       Position index of Article to show as current item.
     */
    @Override
    public void showArticles( List<Article> articleList, int index ) {
        mPagerArticleAdapter.setDataset( articleList );
    
        mBinding.pagerArticles.setCurrentItem( index, false );
    }
    
    /**
     * Show error message to user.
     */
    @Override
    public void showErrorMessage() {
        DialogFragment dialogFragment = ErrorDialog.newInstance();
        dialogFragment.show( getSupportFragmentManager(), "ErrorDialog" );
    }
    
    /**
     * Display title in app's toolbar.
     *
     * @param title String to show in toolbar.
     */
    @Override
    public void showToolbarTitle( String title ) {
        ActionBar actionBar = getSupportActionBar();
        
        if ( actionBar != null )
            actionBar.setTitle( title );
    }
    
    /**
     * Initialize articles ViewPager.
     */
    private void initPagerArticles() {
        mOnPageChangeCallback = new ViewPager2.OnPageChangeCallback() {
            /**
             * This method will be invoked when a new page becomes selected.
             *
             * @param position Position index of the new selected page.
             */
            @Override
            public void onPageSelected( int position ) {
                mPresenter.get().articlePageChanged( position );
            }
        };
    
        mBinding.pagerArticles.registerOnPageChangeCallback( mOnPageChangeCallback );
        
        mBinding.pagerArticles.setAdapter( mPagerArticleAdapter );
    }
    
}
