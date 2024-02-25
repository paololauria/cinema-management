package com.paololauria.cinema.services;
import com.paololauria.cinema.dtos.FilmDetailsDto;
import com.paololauria.cinema.dtos.OmdbMovieInfo;
import com.paololauria.cinema.dtos.OmdbSearchResult;
import com.paololauria.cinema.services.abstraction.OmdbApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
public class OmdbAPIService implements OmdbApiService {
    private final RestTemplate restTemplate;
    private final String omdbApiKey;
    private final String imgOmdbApiKey;
    private final String omdbApiUrl;
    private final String imgOmdbApiUrl;

    @Autowired
    public OmdbAPIService(
            RestTemplate restTemplate,
            @Value("${external-api.omdb.api-key}") String omdbApiKey,
            @Value("${external-api.img-omdb.api-key}") String imgOmdbApiKey,
            @Value("${external-api.omdb.url}") String omdbApiUrl,
            @Value("${external-api.img-omdb.url}") String imgOmdbApiUrl) {
        this.restTemplate = restTemplate;
        this.omdbApiKey = omdbApiKey;
        this.imgOmdbApiKey = imgOmdbApiKey;
        this.omdbApiUrl = omdbApiUrl;
        this.imgOmdbApiUrl = imgOmdbApiUrl;
    }
    @Override
    public String getMovieInfo(String movieTitle) {
        return performOmdbApiRequest("?t=" + movieTitle);
    }
    @Override
    public String getMovieImage(String imdbId) {
        return performOmdbImageApiRequest("?i=" + imdbId);
    }
    @Override
    public String performOmdbImageApiRequest(String query) {
        String url = imgOmdbApiUrl + query + "&apikey=" + imgOmdbApiKey;
        return restTemplate.getForObject(url, String.class);
    }
    @Override
    public String performOmdbApiRequest(String query) {
        String url = omdbApiUrl + query + "&apikey=" + omdbApiKey;
        return restTemplate.getForObject(url, String.class);
    }


    @Override
    public OmdbMovieInfo getMovieById(String imdbId) {
        return performOmdbApiRequest("?i=" + imdbId, OmdbMovieInfo.class);
    }
    @Override
    public OmdbMovieInfo getMovieByTitle(String title) {
        return performOmdbApiRequest("?t=" + title, OmdbMovieInfo.class);
    }


    @Override
    public List<OmdbMovieInfo> searchMoviesByTitle(String title) {
        String url = omdbApiUrl + "?s=" + title + "&apikey=" + omdbApiKey;
        ResponseEntity<OmdbSearchResult> responseEntity = restTemplate.getForEntity(url, OmdbSearchResult.class);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            OmdbSearchResult searchResults = responseEntity.getBody();
            if (searchResults != null && searchResults.getSearch() != null) {
                return searchResults.getSearch();
            }
        }

        return Collections.emptyList();
    }


    @Override
    public <T> T performOmdbApiRequest(String query, Class<T> responseType) {
        String url = omdbApiUrl + query + "&apikey=" + omdbApiKey;
        return restTemplate.getForObject(url, responseType);
    }
}

