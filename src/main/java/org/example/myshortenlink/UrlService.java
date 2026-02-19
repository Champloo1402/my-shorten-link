package org.example.myshortenlink;

import java.util.List;

public interface UrlService {

    String shortenUrl (String url);
    String getUrlById(Long id);
    List<UrlStatDto> getStatistics();
    void deleteUrlById(Long id);

}
