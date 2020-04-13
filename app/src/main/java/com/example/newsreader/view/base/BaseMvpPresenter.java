package com.example.newsreader.view.base;

public interface BaseMvpPresenter {
    
    /**
     * Called when the view is ready to be used.
     */
    void ready();
    
    /**
     * Called when the view should not be used anymore.
     */
    void stop();
    
}
