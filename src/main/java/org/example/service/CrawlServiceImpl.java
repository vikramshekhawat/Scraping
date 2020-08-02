package org.example.service;

import org.example.entity.School;
import org.example.repo.SchoolRepo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class CrawlServiceImpl implements CrawlService {

    @Autowired
    SchoolRepo schoolRepo;


    private HashMap<String, String> map;
    private List<List<School>> schools;

    public CrawlServiceImpl() {
        map = new HashMap<String, String>();
        schools = new ArrayList<>();
    }

    public HashMap<String, String> getPageLinks(String url) throws IOException {
        Document document = Jsoup.connect(url).get();
        System.out.println(document.title());
        Element school_by_state = document.select("div.browseschools").get(0);
        Elements school = school_by_state.select("ul > li");
        for (Element el : school) {
            String link = el.select("a").attr("href");
            String name = el.select("li > a").text();
            map.put(name, link);
        }
        saveSchoolData(map);

        return map;
    }


    public void saveSchoolData(HashMap<String, String> map) throws IOException {
        //int i = 2;
        for (Map.Entry<String, String> data : map.entrySet()) {
            Document document = Jsoup.connect(data.getValue()).get();
            //print the title of this html page
            System.out.println(document.title());
            //select the element
            Element link = document.getElementById("responsivetable");
            Elements school = link.select("tbody > tr ");

            for (Element el : school) {
                School obj = new School();
                //obj.setId(i);
                obj.setName(el.select("td > a").html());
                obj.setAddress(el.select("td").last().text());
                if (!obj.getName().isEmpty()) {

                    schoolRepo.save(obj);

                }
            }

        }

    }

}