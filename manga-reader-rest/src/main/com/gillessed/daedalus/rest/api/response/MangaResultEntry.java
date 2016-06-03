package com.gillessed.daedalus.rest.api.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by gcole on 5/20/16.
 */
public class MangaResultEntry {
    private final String title;
    private final String author;
    private final List<String> tags;
    private final int pageCount;
    private final List<String> pages;

    @JsonCreator
    public MangaResultEntry(
            @JsonProperty("title") String title,
            @JsonProperty("author") String author,
            @JsonProperty("tags") List<String> tags,
            @JsonProperty("pageCount") int pageCount,
            @JsonProperty("pages") List<String> pages) {
        this.title = title;
        this.author = author;
        this.tags = tags;
        this.pageCount = pageCount;
        this.pages = pages;
    }

    @JsonProperty
    public String getTitle() {
        return title;
    }

    @JsonProperty
    public String getAuthor() {
        return author;
    }

    @JsonProperty
    public List<String> getTags() {
        return tags;
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
