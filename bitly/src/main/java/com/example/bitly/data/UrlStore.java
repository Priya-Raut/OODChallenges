package com.example.bitly.data;

import java.util.HashMap;
import java.util.Map;

public class UrlStore {
    private Map<String, String> shortLongUrlMap;

    public UrlStore() {
        shortLongUrlMap = new HashMap<>();
    }

    public Map<String, String> getShortLongUrlMap() {
        return shortLongUrlMap;
    }

}
