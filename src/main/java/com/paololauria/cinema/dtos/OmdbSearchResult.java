package com.paololauria.cinema.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public class OmdbSearchResult {
    @JsonProperty("Search")
    private List<OmdbMovieInfo> search;

    public List<OmdbMovieInfo> getSearch() {
        return search;
    }

    public void setSearch(List<OmdbMovieInfo> search) {
        this.search = search;
    }
}