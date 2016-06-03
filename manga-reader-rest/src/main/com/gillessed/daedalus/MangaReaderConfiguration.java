package com.gillessed.daedalus;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

/**
 * Created by gcole on 4/16/16.
 */
public class MangaReaderConfiguration extends Configuration {
    private String catalogue;

    @JsonProperty
    public String getCatalogue() {
        return catalogue;
    }

    @JsonProperty
    public void setCatalogue(String catalogue) {
        this.catalogue = catalogue;
    }
}
