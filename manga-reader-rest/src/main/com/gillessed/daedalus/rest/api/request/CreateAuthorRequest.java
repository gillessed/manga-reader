package com.gillessed.daedalus.rest.api.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

/**
 * Created by gcole on 5/23/16.
 */
public class CreateAuthorRequest {
    private final Map<String, String> languageMap;

    @JsonCreator
    public CreateAuthorRequest(@JsonProperty("languageMap") Map<String, String> languageMap) {
        this.languageMap = languageMap;
    }

    @JsonProperty
    public Map<String, String> getLanguageMap() {
        return languageMap;
    }
}
