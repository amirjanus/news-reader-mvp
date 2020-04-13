package com.example.newsreader.view.article.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.newsreader.utils.models.Article;

import java.util.ArrayList;
import java.util.List;

public class PagerArticleAdapter extends FragmentStateAdapter {
    
    private List<Article> mDataset = new ArrayList<>( 0 );
    
    public PagerArticleAdapter( @NonNull FragmentActivity fragmentActivity ) {
        super( fragmentActivity );
    }
    
    /**
     * Provide a new Fragment associated with the specified position.
     *
     * @param position The position of the item within the adapter's data set.
     */
    @NonNull
    @Override
    public Fragment createFragment( int position ) {
        return PagerArticleFragment.newInstance( mDataset.get( position ) );
    }
    
    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
    
    /**
     * Set this adapters dataset.
     *
     * @param articleList List to set as this adapter's dataset.
     */
    public void setDataset( List<Article> articleList ) {
        mDataset = articleList;
    
        notifyDataSetChanged();
    }
    
}
