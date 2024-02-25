package com.paololauria.cinema.services.abstraction;

import com.paololauria.cinema.dtos.OmdbMovieInfo;

import java.util.List;

public interface OmdbApiService {
    String getMovieInfo(String movieTitle);
    String getMovieImage(String imdbId);
    String performOmdbApiRequest(String query);
    String performOmdbImageApiRequest(String query);
    List<OmdbMovieInfo> searchMoviesByTitle(String title);
    OmdbMovieInfo getMovieById(String imdbId);
    OmdbMovieInfo getMovieByTitle(String title);
    <T> T performOmdbApiRequest(String query, Class<T> responseType);
}
