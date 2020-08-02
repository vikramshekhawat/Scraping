package org.example.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;

@Service
public interface CrawlService {
    HashMap<String, String> getPageLinks(String url) throws IOException;
    //void getArticles();
}
