package com.example.SearchService.controller;

import com.example.SearchService.dto.ProductTagsDto;
import com.example.SearchService.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
public class SearchController {
    @Autowired
    SearchService searchService;

    @PostMapping("/search/addTagCache")
    public void addTagCache(@RequestBody ProductTagsDto dto) {
        searchService.addTagCache(dto.productId, dto.tags);
    }

    @PostMapping("/search/removeTagCache")
    public void removeTagCache(@RequestBody ProductTagsDto dto) {
        searchService.removeTagCache(dto.productId, dto.tags);
    }

    @GetMapping("/search/tags/{tag}/productIds")
    public List<Long> getTagProductIds(@PathVariable String tag) {
        return searchService.getProductIdsByTag(tag);
    }
}
