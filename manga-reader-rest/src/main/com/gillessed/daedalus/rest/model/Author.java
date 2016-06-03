package com.gillessed.daedalus.rest.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

/**
 * Created by gcole on 5/20/16.
 */
public class Author {
    private final String id;
    private final Map<String, String> languageMap;

    @JsonCreator
    public Author(
            @JsonProperty("id") String id,
            @JsonProperty("languageMap") Map<String, String> languageMap) {
        this.id = id;
        this.languageMap = languageMap;
    }

    @JsonProperty
    public String getId() {
        return id;
    }

    @JsonProperty
    public Map<String, String> getLanguageMap() {
        return languageMap;
    }

    public boolean matches(String substring) {
        if (substring == null || substring.isEmpty()) {
            return true;
        }
        for(String value : languageMap.values()) {
            if(value.toLowerCase().contains(substring.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
}
