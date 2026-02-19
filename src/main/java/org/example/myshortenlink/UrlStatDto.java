package org.example.myshortenlink;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.util.Date;

public class UrlStatDto extends UrlDto {

    private Long id;

    private Long count;
    private Date lastAccess;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
