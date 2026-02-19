package org.example.myshortenlink;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class UrlRecord {

    @Id
    @GeneratedValue
    private Long id;

    private String url;

    private Long count;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastAccess;

    public UrlRecord() {
        count = 0L;
        lastAccess = new Date();
    }

    public UrlRecord(String url) {
        this();
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Date getLastAccess() {
        return lastAccess;
    }

    public void setLastAccess(Date lastAccess) {
        this.lastAccess = lastAccess;
    }

    public UrlStatDto toDto(){
        var dto = new UrlStatDto();
        dto.setId(id);
        dto.setCount(count);
        dto.setLastAccess(lastAccess);
        dto.setUrl(url);
        return dto;
    }
}
