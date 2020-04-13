package com.example.newsreader.view.main;

import com.example.newsreader.view.base.BaseMvpPresenter;

public interface MainMvpPresenter extends BaseMvpPresenter {
    
    /**
     * Called when user clicks on item in Article RecyclerView.
     *
     * @param articleIndex Position index of selected article item.
     */
    void articleSelected( int articleIndex );
    
}
