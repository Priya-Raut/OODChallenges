package com.example.bitly.logic;

import com.example.bitly.data.UrlStore;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UrlManagementService {
    private UrlStore urlStore;
    private static final String DOMAIN_NAME = "https://www.dummybitly.com/";
    private static final int HASH_LENGTH = 7;

    public UrlManagementService() {
        urlStore = new UrlStore();
    }

    /**
     * Generate short url for given long url.
     *
     * @param longUrl long url.
     * @return short url.
     */
    private String generateShortUrl(String longUrl) {
        UUID uniqueId = UUID.randomUUID();
        String shortUrlHash = uniqueId.toString().substring(0, HASH_LENGTH);
        String shortUrl = DOMAIN_NAME.concat(shortUrlHash);
        return shortUrl;
    }

    /**
     * Check if short url is unique.
     *
     * @param shortUrlHash short url.
     * @return true if url is unique, false otherwise
     */
    private boolean isShortUrlHashUnique(String shortUrlHash) {
        if (urlStore.getShortLongUrlMap().containsKey(shortUrlHash)) {
            return false;
        }
        return true;
    }

    /**
     * Store mapping of short url and long url.
     *
     * @param shortUrl short url
     * @param longUrl long url
     */
    private void storeShortURlLongURlMapping(String shortUrl, String longUrl) {
        urlStore.getShortLongUrlMap().put(shortUrl, longUrl);
    }

    /**
     * Get short url for the given long url.
     *
     * @param longUrl long url to be shortened.
     * @return short url, otherwise error message if input long url is empty.
     */
    public String getShortUrlForLongURl(String longUrl) {
        String shortUrl = generateShortUrl(longUrl);
        if (isShortUrlHashUnique(shortUrl)) {
            storeShortURlLongURlMapping(shortUrl, longUrl);
        } else {
            do {
                shortUrl = generateShortUrl(longUrl);
            } while ((!isShortUrlHashUnique(shortUrl)));
            storeShortURlLongURlMapping(shortUrl, longUrl);
        }
        return shortUrl;
    }

    /**
     * Get long url for the given short url.
     *
     * @param shortUrl short url for previously stored long url.
     * @return long url, otherwise error message if short url is empty or long url does not exists.
     */
    public String getLongUrlForShortUrl(String shortUrl) {
        if (urlStore.getShortLongUrlMap().containsKey(shortUrl)) {
            return urlStore.getShortLongUrlMap().get(shortUrl);
        }
        return null;//no long url exists for the short url
    }
}
