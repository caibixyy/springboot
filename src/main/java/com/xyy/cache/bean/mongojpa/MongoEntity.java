package com.xyy.cache.bean.mongojpa;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "col")
public class MongoEntity {
    private String title;
    private String description;
    private String by;
    private String url;
    private String tags;
    private Integer likes;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    @Override
    public String toString() {
        return "MongoEntity{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", by='" + by + '\'' +
                ", url='" + url + '\'' +
                ", tags='" + tags + '\'' +
                ", likes=" + likes +
                '}';
    }
}
