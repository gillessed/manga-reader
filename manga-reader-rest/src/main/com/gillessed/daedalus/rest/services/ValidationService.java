package com.gillessed.daedalus.rest.services;

import com.gillessed.daedalus.rest.api.Id;
import com.gillessed.daedalus.rest.api.request.EditAuthorRequest;
import com.gillessed.daedalus.rest.api.request.EditTagRequest;
import com.gillessed.daedalus.rest.exception.ValidationException;
import com.gillessed.daedalus.rest.model.Author;
import com.gillessed.daedalus.rest.model.Languages;
import com.gillessed.daedalus.rest.model.Tag;

import java.util.Map;

/**
 * Created by gcole on 5/23/16.
 */
public class ValidationService {

    private final CatalogueService catalogueService;

    public ValidationService(Services services) {
        catalogueService = services.getCatalogueService();
    }

    public void verifyTag(Tag tag) throws ValidationException {
        if(tag.getId() == null) {
            throw new ValidationException("Tag id is null.");
        }
        if(tag.getId().isEmpty()) {
            throw new ValidationException("Tag id is empty.");
        }
        for(Map.Entry<String, String> entry : tag.getLanguageMap().entrySet()) {
            String language = entry.getKey();
            String value = entry.getValue();
            if(value == null) {
                throw new ValidationException("Value for " + language + " is null.");
            }
            if(value.isEmpty()) {
                throw new ValidationException("Value for " + language + " is empty.");
            }
        }
    }

    public void verifyEditTag(EditTagRequest editRequest) throws ValidationException {
        if(!catalogueService.containsTag(editRequest.getTagId())) {
            throw new ValidationException("Catalogue does not contain tag with id " + editRequest.getTagId());
        }
        if(editRequest.getLanguage() == null) {
            throw new ValidationException("Language is null.");
        }
        if(!Languages.ALL.contains(editRequest.getLanguage())) {
            throw new ValidationException("There is not language " + editRequest.getLanguage());
        }
        if(editRequest.getValue() == null) {
            throw new ValidationException("Value is null.");
        }
        if(editRequest.getValue().isEmpty()) {
            throw new ValidationException("Value is empty.");
        }
    }

    public void verifyTagId(Id id) throws ValidationException {
        if(!catalogueService.containsTag(id.getId())) {
            throw new ValidationException("Catalogue does not contain tag with id " + id.getId());
        }
    }

    public void verifyAuthor(Author author) throws ValidationException {
        if(author.getId() == null) {
            throw new ValidationException("Author id is null.");
        }
        if(author.getId().isEmpty()) {
            throw new ValidationException("Tag id is empty.");
        }
        for(Map.Entry<String, String> entry : author.getLanguageMap().entrySet()) {
            String language = entry.getKey();
            String value = entry.getValue();
            if(value == null) {
                throw new ValidationException("Value for " + language + " is null.");
            }
            if(value.isEmpty()) {
                throw new ValidationException("Value for " + language + " is empty.");
            }
        }
    }

    public void verifyEditAuthor(EditAuthorRequest editRequest) throws ValidationException {
        if(!catalogueService.containsTag(editRequest.getAuthorId())) {
            throw new ValidationException("Catalogue does not contain tag with id " + editRequest.getAuthorId());
        }
        if(editRequest.getLanguage() == null) {
            throw new ValidationException("Language is null.");
        }
        if(!Languages.ALL.contains(editRequest.getLanguage())) {
            throw new ValidationException("There is not language " + editRequest.getLanguage());
        }
        if(editRequest.getValue() == null) {
            throw new ValidationException("Value is null.");
        }
        if(editRequest.getValue().isEmpty()) {
            throw new ValidationException("Value is empty.");
        }
    }

    public void verifyAuthorId(Id id) throws ValidationException {
        if(!catalogueService.containsAuthor(id.getId())) {
            throw new ValidationException("Catalogue does not contain author with id " + id.getId());
        }
    }

    private void verifyLanguageMap(Map<String, String> languageMap) throws ValidationException {

    }
}
