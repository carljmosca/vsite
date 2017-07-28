/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carljmosca.vsite.service;

import com.carljmosca.vsite.data.TechnologyDetail;
import com.carljmosca.vsite.data.TechnologyHeader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author moscac
 */
@Component
public class TechnologyService {

    @Value("${vsite.data.public}")
    private String directory;
    private final List<TechnologyHeader> list;

    public TechnologyService() {
        list = new ArrayList<>();
    }

    public List<TechnologyHeader> findAll(String search) {
        read(search);
        return list;
    }

    private void read(String search) {
        list.clear();

        JSONParser parser = new JSONParser();
        if (search == null) {
            search = "";
        }
        search = search.toLowerCase();
        try {
            JSONArray jsonArray = (JSONArray)parser.parse(new FileReader(
                    directory + "/technologies.json"));
            for (Object o : jsonArray) {
                JSONObject jsonObject = (JSONObject) o;
                TechnologyHeader tHeader = new TechnologyHeader();
                tHeader.setName((String) jsonObject.get("name"));
                JSONArray items = (JSONArray) jsonObject.get("items");
                if (items != null) {
                    for (Object o2 : items) {
                        JSONObject item = (JSONObject) o2;
                        String name = 
                                item.get("name") != null ? (String)item.get("name") : "";
                        String description = 
                                item.get("description") != null ? (String)item.get("description") : "";
                        TechnologyDetail tDetail = new TechnologyDetail(name, description);
                        if (search.isEmpty()
                                || name.toLowerCase().contains(search)
                                || description.toLowerCase().contains(search)) {
                            tHeader.getItems().add(tDetail);
                        }
                    }
                }
                if (!tHeader.getItems().isEmpty()) {
                    list.add(tHeader);
                }
            }
        } catch (IOException ex) {
        } catch (ParseException ex) {
            Logger.getLogger(
                    TechnologyService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int count() {
        return list.size();
    }

}
