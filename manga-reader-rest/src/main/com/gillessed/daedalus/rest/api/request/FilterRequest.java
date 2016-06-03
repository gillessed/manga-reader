package com.gillessed.daedalus.rest.api.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by gcole on 5/22/16.
 */
public class FilterRequest {
    private final String substring;

    @JsonCreator
    public FilterRequest(@JsonProperty("substring") String substring) {
        this.substring = substring;
    }

    @JsonProperty
    public String getSubstring() {
        return substring;
    }
}
