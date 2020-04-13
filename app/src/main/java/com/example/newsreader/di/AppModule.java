package com.example.newsreader.di;

import android.content.Context;
import android.util.Log;

import com.example.newsreader.BuildConfig;
import com.example.newsreader.NewsReaderApplication;
import com.example.newsreader.data.MainMvpModel;
import com.example.newsreader.data.MainModel;
import com.example.newsreader.data.local.interfaces.NewsLocalDb;
import com.example.newsreader.data.local.realm.RealmNewsLocalDb;
import com.example.newsreader.data.local.realm.RmDatabaseModule;
import com.example.newsreader.data.remote.newsapi.NewsApi;
import com.example.newsreader.data.remote.retrofit.NewsApiService;
import com.example.newsreader.data.remote.retrofit.RetrofitNewsApi;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import io.realm.RealmConfiguration;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public abstract class AppModule {
    
    /**
     * Provide android context.
     *
     * @param application Android application.
     * @return Android context.
     */
    @Singleton
    @Binds
    abstract Context bindContext( NewsReaderApplication application );
    
    /**
     * Provide NewsApi interface for fetching news from remote api.
     *
     * @param context Android context.
     * @return NewsApi interface.
     */
    @Singleton
    @Provides
    static NewsApi provideNewsApi( Context context ) {
        HttpLoggingInterceptor interceptor = createHttpLoggingInterceptor();
        
        OkHttpClient.Builder httpClient = createOkHttpClientBuilder( interceptor );
        
        Retrofit retrofit = createRetrofit( httpClient );
        
        return new RetrofitNewsApi( retrofit.create( NewsApiService.class ), context.getResources() );
    }
    
    /**
     * Provide NewsLocalDb interface from accessing local database.
     *
     * @return NewsLocalDb interface.
     */
    @Singleton
    @Provides
    static NewsLocalDb provideLocalDb() {
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name( "newsDatabase.realm" )
                .modules( new RmDatabaseModule() )
                .deleteRealmIfMigrationNeeded()
                .build();
        
        return new RealmNewsLocalDb( configuration );
    }
    
    /**
     * Provide MainMvpModel interface for accessing local and remote data sources ( repository ).
     *
     * @param newsLocalDb NewsLocalDb interface.
     * @param newsApi     NewsApi interface.
     * @return MainMvpModel interface.
     */
    @Singleton
    @Provides
    static MainMvpModel provideDatabaseModel( NewsLocalDb newsLocalDb, NewsApi newsApi ) {
        return new MainModel( newsLocalDb, newsApi );
    }
    
    /**
     * Create logging interceptor for Retrofit.
     *
     * @return HttpLoggingInterceptor object.
     */
    private static HttpLoggingInterceptor createHttpLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor( message -> Log.d( "OkHttp", message ) );
        
        if ( BuildConfig.DEBUG )
            interceptor.setLevel( HttpLoggingInterceptor.Level.BASIC );
        else
            interceptor.setLevel( HttpLoggingInterceptor.Level.NONE );
        
        return interceptor;
    }
    
    /**
     * Create OkHttp client builder for Retrofit.
     *
     * @return OkHttpClient.Builder object.
     */
    private static OkHttpClient.Builder createOkHttpClientBuilder( HttpLoggingInterceptor interceptor ) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor( interceptor );
        
        return httpClient;
    }
    
    /**
     * Create Retrofit.
     *
     * @return Retrofit object.
     */
    private static Retrofit createRetrofit( OkHttpClient.Builder httpClient ) {
        return new Retrofit.Builder()
                .baseUrl( NewsApiService.baseUrl )
                .addConverterFactory( GsonConverterFactory.create() )
                .client( httpClient.build() )
                .build();
    }
    
}
