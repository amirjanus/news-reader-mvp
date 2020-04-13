package com.example.newsreader.data.remote.retrofit;

import com.example.newsreader.utils.models.NewsSource;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * NewsApi written as Java interface so that it can be used by Retrofit.
 */
public interface NewsApiService {
    
    /* NewsApi base url. */
    String baseUrl = "https://newsapi.org/";
    
    /* Path for articles. */
    String path = "v1/articles";
    
    /**
     * Get news articles from NewsApi.
     *
     * @param source Article source.
     * @param sortBy How to sort articles.
     * @param apiKey NewsApi key.
     * @return NewsData object with timestamp and articles list.
     */
    @GET( path )
    Call<NewsSource> getNews(
            @Query( "source" ) String source,
            @Query( "sortBy" ) String sortBy,
            @Query( "apiKey" ) String apiKey
    );
    
}
