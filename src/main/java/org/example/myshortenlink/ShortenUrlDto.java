package org.example.myshortenlink;

public class ShortenUrlDto extends UrlDto {

    protected String shortUrl;

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }
}
