package org.example.controller;

import org.example.service.CrawlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;

@RestController
@RequestMapping("/")
public class CrawlController {
    @Autowired
    CrawlService crawlService;

    @RequestMapping(value = "/school", method = RequestMethod.POST)
    public String saveSchoolData() throws IOException {
        HashMap<String, String> map = crawlService.getPageLinks("https://www.cbseschool.org/");
        System.out.println("Success");
        return "Success";
    }


}
