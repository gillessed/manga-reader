package com.gillessed.daedalus.rest.services;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gillessed.daedalus.rest.model.Author;
import com.gillessed.daedalus.rest.model.Manga;
import com.gillessed.daedalus.rest.model.Tag;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

/**
 * Created by gcole on 5/20/16.
 */
public class CatalogueService {
    private final ConcurrentMap<String, Manga> mangaCatalogue;
    private final ConcurrentMap<String, Author> authorCatalogue;
    private final ConcurrentMap<String, Tag> tagCatalogue;

    @JsonCreator
    public CatalogueService(
            @JsonProperty("mangaCatalogue") Map<String, Manga> mangaCatalogue,
            @JsonProperty("authorCatalogue") Map<String, Author> authorCatalogue,
            @JsonProperty("tagCatalogue") Map<String, Tag> tagCatalogue) {
        this.mangaCatalogue = new ConcurrentHashMap<>(mangaCatalogue);
        this.authorCatalogue = new ConcurrentHashMap<>(authorCatalogue);
        this.tagCatalogue = new ConcurrentHashMap<>(tagCatalogue);
    }

    @JsonProperty
    public Map<String, Manga> getMangaCatalogue() {
        return Collections.unmodifiableMap(mangaCatalogue);
    }

    @JsonProperty
    public ConcurrentMap<String, Author> getAuthorCatalogue() {
        return authorCatalogue;
    }

    @JsonProperty
    public ConcurrentMap<String, Tag> getTagCatalogue() {
        return tagCatalogue;
    }

    /////////////////////////
    // Catalogue Modifiers //
    /////////////////////////

    public void addManga(Manga manga) {
        mangaCatalogue.put(manga.getId(), manga);
    }

    public void addTag(Tag tag) {
        tagCatalogue.put(tag.getId(), tag);
    }

    public void editTag(String tagId, String language, String value) {
        Tag oldTag = tagCatalogue.get(tagId);
        Map<String, String> languageMap = new HashMap<>(oldTag.getLanguageMap());
        languageMap.put(language, value);
        Tag newTag = new Tag(oldTag.getId(), languageMap);
        tagCatalogue.put(newTag.getId(), newTag);
    }

    public void deleteTag(String tagId) {
        tagCatalogue.remove(tagId);
    }

    public void addAuthor(Author author) {
        authorCatalogue.put(author.getId(), author);
    }

    public void editAuthor(String authorId, String language, String value) {
        Author oldAuthor = authorCatalogue.get(authorId);
        Map<String, String> languageMap = new HashMap<>(oldAuthor.getLanguageMap());
        languageMap.put(language, value);
        Author newAuthor = new Author(oldAuthor.getId(), languageMap);
        authorCatalogue.put(newAuthor.getId(), newAuthor);
    }

    public void deleteAuthor(String authorId) {
        authorCatalogue.remove(authorId);
    }

    ///////////////////////
    // Catalogue Getters //
    ///////////////////////

    public boolean containsTag(String tagId) {
        return tagCatalogue.containsKey(tagId);
    }

    public List<Tag> getTags(String substring) {
        return tagCatalogue.values().stream()
                .filter((Tag tag) -> tag.matches(substring))
                .collect(Collectors.toList());
    }

    public boolean containsAuthor(String authorId) {
        return authorCatalogue.containsKey(authorId);
    }

    public List<Author> getAuthors(String substring) {
        return authorCatalogue.values().stream()
                .filter((Author author) -> author.matches(substring))
                .collect(Collectors.toList());
    }
}
