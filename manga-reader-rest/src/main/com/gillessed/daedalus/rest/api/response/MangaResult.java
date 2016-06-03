package com.gillessed.daedalus.rest.api.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

/**
 * Created by gcole on 4/16/16.
 */
public class MangaResult {
    private final String id;
    private final Map<String, MangaResultEntry> languages;

    @JsonCreator
    public MangaResult(
            @JsonProperty("id") String id,
            @JsonProperty("languages") Map<String, MangaResultEntry> languages) {
        this.id = id;
        this.languages = languages;
    }

    @JsonProperty
    public String getId() {
        return id;
    }

    @JsonProperty
    public Map<String, MangaResultEntry> getLanguages() {
        return languages;
    }
}
