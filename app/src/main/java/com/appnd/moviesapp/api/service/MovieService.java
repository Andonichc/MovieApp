package com.appnd.moviesapp.api.service;


import com.appnd.moviesapp.api.dto.MovieList;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface MovieService {
    @GET("/4/list/{list_id}")
    Observable<MovieList> findMovieList(@Path("list_id") long listId,
                                        @Query("page") int pageNum,
                                        @Query("api_key") String apiKey);

}
