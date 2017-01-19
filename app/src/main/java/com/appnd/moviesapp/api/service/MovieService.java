package com.appnd.moviesapp.api.service;


import com.appnd.moviesapp.api.dto.MovieList;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface MovieService {
    @GET("/3/movie/popular")
    Observable<MovieList> findMovieList(@Query("page") int pageNum,
                                        @Query("api_key") String apiKey);

    @GET("/3/search/movie")
    Observable<MovieList> searchMovies(@Query("query") String searchQuery,
                                       @Query("page") int pageNum,
                                       @Query("api_key") String apiKey);
}
