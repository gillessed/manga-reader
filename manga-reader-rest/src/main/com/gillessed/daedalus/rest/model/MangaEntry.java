package com.gillessed.daedalus.rest.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by gcole on 5/20/16.
 */
public class MangaEntry {
    private final String title;
    private final int pageCount;
    private final List<String> pages;

    @JsonCreator
    public MangaEntry(
            @JsonProperty("title") String title,
            @JsonProperty("pageCount") int pageCount,
            @JsonProperty("pages") List<String> pages) {
        this.title = title;
        this.pageCount = pageCount;
        this.pages = pages;
    }

    @JsonProperty
    public String getTitle() {
        return title;
    }

    @JsonProperty
    public int getPageCount() {
        return pageCount;
    }

    @JsonProperty
    public List<String> getPages() {
        return pages;
    }
}
