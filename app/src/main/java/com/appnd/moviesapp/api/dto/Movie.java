package com.appnd.moviesapp.api.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Dto used to hold the information related to a movie
 * (simplified from the real information returned by the api to simplify things for this example).
 */
public class Movie {

    private long id;

    @SerializedName("poster_path")
    private String image;

    @SerializedName("original_title")
    private String title;

    private String overview;

    @SerializedName("release_date")
    private String date;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getYear(){
        return date.substring(0,4);
    }
}
