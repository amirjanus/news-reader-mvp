package com.example.newsreader.view.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.newsreader.databinding.ActivityMainBinding;
import com.example.newsreader.utils.dialogs.ErrorDialog;
import com.example.newsreader.utils.models.Article;
import com.example.newsreader.view.article.ArticleActivity;
import com.example.newsreader.view.main.adapter.RecyclerArticleAdapter;

import java.util.List;

import javax.inject.Inject;

import dagger.Lazy;
import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity implements MainMvpView, RecyclerArticleAdapter.OnArticleClickListener {
    
    public static final String EXTRA_ARTICLE_INDEX = "EXTRA_ARTICLE_INDEX";
    
    @Inject
    Lazy<MainMvpPresenter> mPresenter;
    
    @Inject
    RecyclerArticleAdapter mAdapter;
    
    private ActivityMainBinding mBinding;
    
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        
        // Set view binding.
        mBinding = ActivityMainBinding.inflate( getLayoutInflater() );
        setContentView( mBinding.getRoot() );
        
        // Make view's content to appear behind the navigation bar.
        mBinding.getRoot().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION );
        
        initRecyclerArticle();
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        
        mPresenter.get().ready();
    }
    
    @Override
    protected void onPause() {
        super.onPause();
        
        mPresenter.get().stop();
    }
    
    /**
     * Starts ArticleActivity.
     *
     * @param articleIndex Position index of selected article item.
     */
    @Override
    public void startArticleActivity( int articleIndex ) {
        Intent intent = new Intent( MainActivity.this, ArticleActivity.class );
        intent.putExtra( EXTRA_ARTICLE_INDEX, articleIndex );
        
        startActivity( intent );
    }
    
    /**
     * Display Articles list in view.
     *
     * @param articleList List of Articles to show.
     */
    @Override
    public void showArticles( List<Article> articleList ) {
        mAdapter.setDataset( articleList );
    }
    
    /**
     * Shows ProgressBar in view.
     *
     * @param show True to show ProgressBar, false to hide it.
     */
    @Override
    public void showProgressLoader( boolean show ) {
        if ( show )
            mBinding.progressLoader.setVisibility( View.VISIBLE );
        else
            mBinding.progressLoader.setVisibility( View.GONE );
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
     * Called when user clicks on Article's RecyclerView item.
     *
     * @param articleIndex Position index of selected article item.
     */
    @Override
    public void onArticleClick( int articleIndex ) {
        mPresenter.get().articleSelected( articleIndex );
    }
    
    /**
     * Initialize RecyclerView to show Articles list.
     */
    private void initRecyclerArticle() {
        mBinding.recyclerArticle.setLayoutManager( new LinearLayoutManager( this ) );
        mBinding.recyclerArticle.setAdapter( mAdapter );
        
        mAdapter.setOnArticleClickListener( this );
    }
    
}
