package com.example.bitly.controller;

import com.example.bitly.logic.UrlManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/urlshortner")
public class UrlController {
    private final UrlManagementService urlManagementService;

    @Autowired
    public UrlController(UrlManagementService urlManagementService) {
        this.urlManagementService = urlManagementService;
    }

    /**
     * Get short url for the given long url.
     *
     * @param longUrl long url to be shortened.
     * @return short url, otherwise error message if input long url is empty.
     */
    @GetMapping
    @RequestMapping("/shortUrl")
    public String getShortUrlForLongURl(@RequestParam(name = "longUrl") String longUrl) {
        if (longUrl != null && longUrl.isEmpty()) {
            return "empty url";
        }
        String shortUrl = this.urlManagementService.getShortUrlForLongURl(longUrl);
        return shortUrl;
    }

    /**
     * Get long url for the given short url.
     *
     * @param shortUrl short url for previously stored long url.
     * @return long url, otherwise error message if short url is empty or long url does not exists.
     */
    @GetMapping
    @RequestMapping("/longUrl")
    public String getLongUrlForShortUrl(@RequestParam(name = "shortUrl") String shortUrl) {
        if (shortUrl != null && shortUrl.isEmpty()) {
            return "empty url";
        }
        String longUrl = this.urlManagementService.getLongUrlForShortUrl(shortUrl);
        if (longUrl == null) {
            return "Invalid Url";
        }
        return longUrl;
    }
}