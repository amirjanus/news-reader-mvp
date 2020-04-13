package com.example.newsreader.data.remote.retrofit;

import android.content.res.Resources;

import com.example.newsreader.R;
import com.example.newsreader.data.remote.newsapi.NewsApi;
import com.example.newsreader.utils.models.NewsSource;

import java.util.Calendar;
import java.util.TimeZone;

import javax.inject.Inject;

import io.reactivex.Single;
import retrofit2.Response;

public class RetrofitNewsApi implements NewsApi {
    
    private NewsApiService mService;
    
    private String mApiKey;
    
    @Inject
    public RetrofitNewsApi( NewsApiService newsApiService, Resources resources ) {
        mService = newsApiService;
        mApiKey = resources.getString( R.string.news_api_key );
    }
    
    /**
     * Get news articles from NewsApi.
     *
     * @param source Article source.
     * @param sortBy How to sort news list.
     * @return NewsData object with timestamp and articles list.
     */
    @Override
    public Single<NewsSource> getNews( String source, String sortBy ) {
        return Single.fromCallable( () -> mService.getNews( source, sortBy, mApiKey ).execute() )
                .map( response -> toNewsSource( response, source ) );
    }
    
    /**
     * Get NewsData from retrofit response.
     *
     * @param response Retrofit response.
     * @param newsSourceName Article source.
     * @return NewsData object from Retrofit response.
     */
    private NewsSource toNewsSource( Response<NewsSource> response, String newsSourceName ) {
        NewsSource newsSource = response.body();
        
        if ( newsSource != null ) {
            newsSource.date = Calendar.getInstance( TimeZone.getTimeZone( "UTC" ) ).getTime();
            newsSource.id = newsSourceName;
        }
        
        return newsSource;
    }
    
}
