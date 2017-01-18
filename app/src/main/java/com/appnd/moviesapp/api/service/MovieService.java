package com.appnd.moviesapp.api.service;


import com.appnd.moviesapp.api.dto.MovieList;

import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface MovieService {
    @GET("/4/list/{list_id}")
    Observable<MovieList> findMovieList(@Field("list_id") long listId,
                                        @Query("page") int pageNum,
                                        @Query("api_key") String apiKey);

}
