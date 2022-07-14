package com.jobRecommendation.job.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Array;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Item {

    private String id;
    private String title;
    private String urls;
    private String company;
    private String company_logo;
    private String description;
    private Set<String> keywords;
    private boolean favorite;

    public Item() {
    }

    public Item(String id, String title, String company, String company_logo, String urls, String description, Set<String> keywords, boolean favorite) {
        this.id = id;
        this.title = title;
        this.company = company;
        this.urls = urls;
        this.company_logo = company_logo;
        this.description = description;
        this.keywords = keywords;
        this.favorite = favorite;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", urls='" + urls + '\'' +
                ", company='" + company + '\'' +
                ", company_logo='" + company_logo + '\'' +
                ", description='" + description + '\'' +
                ", keywords=" + keywords +
                ", favorite=" + favorite +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return favorite == item.favorite && Objects.equals(id, item.id) && Objects.equals(title, item.title) && Objects.equals(urls, item.urls) && Objects.equals(company, item.company) && Objects.equals(company_logo, item.company_logo) && Objects.equals(description, item.description) && Objects.equals(keywords, item.keywords);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, urls, company, company_logo, description, keywords, favorite);
    }

    public void setKeywords(Set<String> keywords) {
        this.keywords = keywords;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("url")
    public String getUrls() {
        return urls;
    }

    @JsonProperty("company_name")
    public String getCompany() {
        return company;
    }

    @JsonProperty("company_logo")
    public String getCompany_logo() {
        return company_logo;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    public Set<String> getKeywords() {
        return keywords;
    }

    public boolean isFavorite() {
        return favorite;
    }
}

