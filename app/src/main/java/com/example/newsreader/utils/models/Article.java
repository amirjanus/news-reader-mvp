package com.example.newsreader.utils.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Class holds NewsApi article data.
 */
public class Article implements Parcelable {
    
    /* Object ID. */
    public long id;
    
    /* Article's title. */
    public String title;
    
    /* Article's text. */
    public String description;
    
    /* Url to article's image. */
    public String urlToImage;
    
    public Article() {}
    
    public Article( long id, String title, String description, String urlToImage ) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.urlToImage = urlToImage;
    }
    
    protected Article( Parcel in ) {
        id = in.readInt();
        title = in.readString();
        description = in.readString();
        urlToImage = in.readString();
    }
    
    public static final Creator<Article> CREATOR = new Creator<Article>() {
        @Override
        public Article createFromParcel( Parcel in ) {
            return new Article( in );
        }
        
        @Override
        public Article[] newArray( int size ) {
            return new Article[size];
        }
    };
    
    /**
     * Describe the kinds of special objects contained in this Parcelable instance's marshaled representation.
     *
     * @return Bitmask indicating the set of special object types marshaled by this Parcelable object instance.
     */
    @Override
    public int describeContents() {
        return 0;
    }
    
    /**
     * Flatten this object in to a Parcel.
     *
     * @param dest  The Parcel in which the object should be written.
     * @param flags Additional flags about how the object should be written.
     */
    @Override
    public void writeToParcel( Parcel dest, int flags ) {
        dest.writeLong( id );
        dest.writeString( title );
        dest.writeString( description );
        dest.writeString( urlToImage );
    }
    
}
