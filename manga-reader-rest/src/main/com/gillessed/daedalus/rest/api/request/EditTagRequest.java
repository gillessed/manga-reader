package com.gillessed.daedalus.rest.api.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by gcole on 5/23/16.
 */
public class EditTagRequest {
    private final String tagId;
    private final String language;
    private final String value;

    @JsonCreator
    public EditTagRequest(
            @JsonProperty("tagId") String tagId,
            @JsonProperty("language") String language,
            @JsonProperty("value") String value) {
        this.tagId = tagId;
        this.language = language;
        this.value = value;
    }

    @JsonProperty
    public String getTagId() {
        return tagId;
    }

    @JsonProperty
    public String getLanguage() {
        return language;
    }

    @JsonProperty
    public String getValue() {
        return value;
    }
}
