package org.example.myshortenlink;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@PropertySource("classpath:application.properties")
public class UrlController {

    private final UrlService urlService;

    @Value("${custom.domain.name}")
    private String domainName;

    @Value("${server.port}")
    private String port;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("shorten")
    public ShortenUrlDto shorten(@RequestBody UrlDto request) {

        var id = urlService.shortenUrl(request.getUrl());
        var shortenUrl = domainName + ":" + port + "/my/" + id;

        ShortenUrlDto result = new ShortenUrlDto();
        result.setUrl(request.getUrl());
        result.setShortUrl(shortenUrl);

        return result;
    }

    @GetMapping("my/{id}")
    public ResponseEntity<Void> redirect(@PathVariable Long id) {
        var longUrl = urlService.getUrlById(id);

        if(longUrl == null){
            return ResponseEntity.notFound().build();
        }

        var headers = new HttpHeaders();
        headers.setLocation(URI.create(longUrl));
        headers.setCacheControl("must-revalidate, no-cache, no-store");

        return new ResponseEntity(headers, HttpStatus.FOUND);
    }

    @DeleteMapping("my/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        urlService.deleteUrlById(id);

        return ResponseEntity.ok().build();
    }

    @GetMapping("stat")
    public List<UrlStatDto> statistics() {
        return urlService.getStatistics();
    }

}
