package com.example.newsreader.data.constants;

/**
 * Holds names of news api sources.
 */
public enum NewsSourceNames {
    
    BBC( "bbc-news" );
    
    private String mSource;
    
    NewsSourceNames( String source ) {
        mSource = source;
    }
    
    /**
     * Returns news source name.
     *
     * @return News source name.
     */
    public String getString() {
        return mSource;
    }
    
}
