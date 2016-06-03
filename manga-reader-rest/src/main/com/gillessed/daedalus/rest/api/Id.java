package com.gillessed.daedalus.rest.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by gcole on 5/23/16.
 */
public class Id {
    private final String id;

    @JsonCreator
    public Id(@JsonProperty("id") String id) {
        this.id = id;
    }

    @JsonProperty
    public String getId() {
        return id;
    }
}
