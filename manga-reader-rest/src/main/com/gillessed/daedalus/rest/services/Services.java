package com.gillessed.daedalus.rest.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gillessed.daedalus.MangaReaderConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * Created by gcole on 5/20/16.
 */
public class Services {

    private final Logger logger = LoggerFactory.getLogger(Services.class);

    private final MangaReaderConfiguration configuration;
    private final ObjectMapper mapper;

    private final CatalogueService catalogueService;
    private final AuthService authService;
    private final ValidationService validationService;

    public Services(MangaReaderConfiguration configuration) {
        this.configuration = configuration;
        mapper = new ObjectMapper();
        try {
            catalogueService = mapper.readValue(new File(configuration.getCatalogue()), CatalogueService.class);
        } catch (Exception e) {
            throw new IllegalStateException("Error setting up Catalogue Service.", e);
        }
        authService = new AuthService();
        validationService = new ValidationService(this);
    }

    public void shutdown() {
        logger.info("Shutting down services.");
        try {
            logger.info("Serializing catalogue...");
            mapper.writeValue(new File(configuration.getCatalogue()), catalogueService);
        } catch (Exception e) {
            logger.warn("Error saving catalogue.", e);
        }
        logger.info("Shut down services.");
    }

    public CatalogueService getCatalogueService() {
        return catalogueService;
    }

    public AuthService getAuthService() {
        return authService;
    }

    public ValidationService getValidationService() {
        return validationService;
    }
}
