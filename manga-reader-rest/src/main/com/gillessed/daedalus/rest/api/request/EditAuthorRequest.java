package com.gillessed.daedalus.rest.api.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by gcole on 5/23/16.
 */
public class EditAuthorRequest {
    private final String authorId;
    private final String language;
    private final String value;

    @JsonCreator
    public EditAuthorRequest(
            @JsonProperty("authorId") String authorId,
            @JsonProperty("language") String language,
            @JsonProperty("value") String value) {
        this.authorId = authorId;
        this.language = language;
        this.value = value;
    }

    @JsonProperty
    public String getAuthorId() {
        return authorId;
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
