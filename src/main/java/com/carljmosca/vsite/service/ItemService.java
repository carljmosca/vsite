/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carljmosca.vsite.service;

import com.carljmosca.vsite.data.ItemDetail;
import com.carljmosca.vsite.data.ItemHeader;
import com.carljmosca.vsite.data.ItemReference;
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
public class ItemService {

    @Value("${vsite.data.public}")
    private String directory;
    private final List<ItemHeader> list;

    public final static String INTRODUCTION_ITEM_FILE = "introduction.json";
    public final static String TECHNOLOGIES_ITEM_FILE = "technologies.json";
    public final static String PROJECTS_ITEM_FILE = "projects.json";

    public ItemService() {
        list = new ArrayList<>();
    }

    public List<ItemHeader> findAll(String fileName, String search) {
        read(fileName, search);
        return list;
    }

    private void read(String fileName, String search) {
        list.clear();

        JSONParser parser = new JSONParser();
        if (search == null) {
            search = "";
        }
        search = search.toLowerCase();
        try {
            JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(
                    directory + "/" + fileName));
            for (Object o : jsonArray) {
                JSONObject jsonObject = (JSONObject) o;
                ItemHeader tHeader = new ItemHeader();
                tHeader.setName((String) jsonObject.get("name"));
                tHeader.setImage((String) jsonObject.get("image"));
                JSONArray items = (JSONArray) jsonObject.get("items");
                if (items != null) {
                    for (Object o2 : items) {
                        JSONObject item = (JSONObject) o2;
                        String name
                                = item.get("name") != null ? (String) item.get("name") : "";
                        String description
                                = item.get("description") != null ? (String) item.get("description") : "";
                        ItemDetail tDetail = new ItemDetail(name, description);
                        if (search.isEmpty()
                                || name.toLowerCase().contains(search)
                                || description.toLowerCase().contains(search)) {
                            tHeader.getItems().add(tDetail);
                        }
                        JSONArray references = (JSONArray)jsonObject.get("references");
                        if (references != null) {
                            for (Object o3 : references) {
                                JSONObject reference = (JSONObject)o3;
                                ItemReference itemReference = new ItemReference();
                                itemReference.setDescription((String)reference.get("description"));
                                itemReference.setUrl((String)reference.get("url"));
                                tDetail.getReferences().add(itemReference);
                            }
                        }
                    }
                }
                if (!tHeader.getItems().isEmpty()) {
                    list.add(tHeader);
                }
            }
        } catch (IOException
                | ParseException ex) {
            Logger.getLogger(ItemService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int count() {
        return list.size();
    }

}
