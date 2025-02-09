package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/cache")
public class CacheController {

    private static final int MAX_SIZE = 10;
    private final ConcurrentHashMap<String, String> cache = new ConcurrentHashMap<>();

    @PostMapping
    public synchronized String store(@RequestParam String key, String value) {
        if(cache.size() >= MAX_SIZE) {
            return "Cache limit reached.";
        }
        cache.put(key, value);
        return "Stored successfully.";
    }

    @GetMapping("/{key}")
    public synchronized String retrieve(@PathVariable String key) {
        return cache.getOrDefault(key, "Key not found.");
    }

    @DeleteMapping("/{key}")
    public synchronized String remove(@PathVariable String key) {
        if (cache.remove(key) != null) {
            return "Removed successfully.";
        }
        return "Key not found.";
    }
}
