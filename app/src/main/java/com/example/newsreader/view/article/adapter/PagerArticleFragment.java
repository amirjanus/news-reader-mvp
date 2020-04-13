package com.example.newsreader.view.article.adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.example.newsreader.R;
import com.example.newsreader.databinding.PagerArticleItemBinding;
import com.example.newsreader.utils.models.Article;

public class PagerArticleFragment extends Fragment {
    
    private static final String KEY_ARTICLE = "KEY_ARTICLE";
    
    private PagerArticleItemBinding mBinding;
    
    public PagerArticleFragment() {}
    
    public static PagerArticleFragment newInstance( Article article ) {
        PagerArticleFragment fragment = new PagerArticleFragment();
        
        Bundle args = new Bundle();
        args.putParcelable( KEY_ARTICLE, article );
        
        fragment.setArguments( args );
        
        return fragment;
    }
    
    @Nullable
    @Override
    public View onCreateView( @NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState ) {
        mBinding = PagerArticleItemBinding.inflate( inflater, container, false );
        
        return mBinding.getRoot();
    }
    
    @Override
    public void onViewCreated( @NonNull View view, @Nullable Bundle savedInstanceState ) {
        super.onViewCreated( view, savedInstanceState );
        
        // Get the Article that was padded to this Fragment.
        Bundle bundle = getArguments();
        
        if ( bundle != null ) {
            Article article = bundle.getParcelable( KEY_ARTICLE );
            
            if ( article != null )
                showArticleData( article );
        }
    }
    
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        
        mBinding = null;
    }
    
    /**
     * Show Article data in View.
     *
     * @param article Article to show in Fragment's view.
     */
    private void showArticleData( Article article ) {
        // Set article title.
        mBinding.textArticleTitle.setText( article.title );
        
        // Set article description.
        mBinding.textArticleDescription.setText( article.description );
        
        // Set article image.
        Glide.with( mBinding.getRoot() )
                .load( article.urlToImage )
                .apply( new RequestOptions()
                        .placeholder( R.drawable.ic_image_black_24dp )
                        .error( R.drawable.ic_broken_image_black_24dp ) )
                .transition( DrawableTransitionOptions.withCrossFade() )
                .into( mBinding.imageArticleImage );
    }
    
}
