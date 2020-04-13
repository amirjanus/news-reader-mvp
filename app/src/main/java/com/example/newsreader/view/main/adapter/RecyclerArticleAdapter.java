package com.example.newsreader.view.main.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.example.newsreader.R;
import com.example.newsreader.databinding.RecyclerArticleItemBinding;
import com.example.newsreader.utils.models.Article;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class RecyclerArticleAdapter extends RecyclerView.Adapter<RecyclerArticleAdapter.ArticleViewHolder> {
    
    /**
     * Implement this interface to get notified when user clicks on a item.
     */
    @FunctionalInterface
    public interface OnArticleClickListener {
        /**
         * Callback that will be called.
         *
         * @param articlePosition Position of the item in the adapter's data set.
         */
        void onArticleClick( int articlePosition );
    }
    
    private OnArticleClickListener mListener;
    
    private List<Article> mDataset = new ArrayList<>( 0 );
    
    @Inject
    public RecyclerArticleAdapter() {}
    
    /**
     * Called when RecyclerView needs a new ViewHolder of the given type to represent an item.
     *
     * @param parent   The ViewGroup into which the new View will be added.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     */
    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder( @NonNull ViewGroup parent, int viewType ) {
        RecyclerArticleItemBinding binding = RecyclerArticleItemBinding.inflate( LayoutInflater.from( parent.getContext() ), parent, false );
    
        return new ArticleViewHolder( binding, mListener );
    }
    
    /**
     * Called by RecyclerView to display the data at the specified position.
     *
     * @param holder   The ViewHolder which should be updated.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder( @NonNull ArticleViewHolder holder, int position ) {
        Article current = mDataset.get( position );
        
        // Set article text.
        holder.mBinding.textArticleTitle.setText( current.title );
        
        // Set article image.
        Glide.with( holder.mBinding.getRoot() )
                .load( current.urlToImage )
                .apply( new RequestOptions()
                        .placeholder( R.drawable.ic_image_black_24dp )
                        .error( R.drawable.ic_broken_image_black_24dp ) )
                .transition( DrawableTransitionOptions.withCrossFade() )
                .into( holder.mBinding.imageArticleImage );
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
     * Register a callback to be invoked when Recycler item is clicked.
     *
     * @param listener Callback to be invoked.
     */
    public void setOnArticleClickListener( OnArticleClickListener listener ) {
        mListener = listener;
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
    
    public class ArticleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        
        private OnArticleClickListener mListener;
        
        private RecyclerArticleItemBinding mBinding;
        
        public ArticleViewHolder( @NonNull RecyclerArticleItemBinding itemBinding, OnArticleClickListener listener ) {
            super( itemBinding.getRoot() );
            
            mListener = listener;
            mBinding = itemBinding;
            
            // Set view click listener.
            mBinding.getRoot().setOnClickListener( v -> onClick( v ) );
        }
        
        /**
         * Called when a view has been clicked.
         *
         * @param v The view that was clicked.
         */
        @Override
        public void onClick( View v ) {
            if ( mListener != null ) {
                int position = getAdapterPosition();
                
                mListener.onArticleClick( position );
            }
        }
        
    }
    
}
