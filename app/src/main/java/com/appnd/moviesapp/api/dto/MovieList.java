package com.appnd.moviesapp.api.dto;

import java.util.List;

/**
 * Dto class that holds a MovieList
 * (although the call gives us a lot more of information we'll just keep the essential
 * for this example to simplify things).
 */
public class MovieList {

    private long id;

    private List<Movie> results;

    public List<Movie> getResults() {
        return results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
