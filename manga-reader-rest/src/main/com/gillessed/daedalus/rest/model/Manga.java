package com.gillessed.daedalus.rest.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gillessed.daedalus.rest.api.response.MangaResultEntry;

import java.util.List;
import java.util.Map;

/**
 * Created by gcole on 5/20/16.
 */
public class Manga {
    private final String id;
    private final String authorId;
    private final List<String> tagIds;
    private final Map<String, MangaResultEntry> entries;

    @JsonCreator
    public Manga(
            @JsonProperty("id") String id,
            @JsonProperty("authorId") String authorId,
            @JsonProperty("tagIds") List<String> tagIds,
            @JsonProperty("entries") Map<String, MangaResultEntry> entries) {
        this.id = id;
        this.authorId = authorId;
        this.tagIds = tagIds;
        this.entries = entries;
    }

    @JsonProperty
    public String getId() {
        return id;
    }

    @JsonProperty
    public String getAuthorId() {
        return authorId;
    }

    @JsonProperty
    public List<String> getTagIds() {
        return tagIds;
    }

    @JsonProperty
    public Map<String, MangaResultEntry> getEntries() {
        return entries;
    }
}
