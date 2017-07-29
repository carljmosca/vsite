/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carljmosca.vsite.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author moscac
 */
public class ItemDetail implements Serializable {

    private String name;
    private String description;
    private final List<ItemReference> references;

    public ItemDetail() {
        this.references = new ArrayList<>();
    }
    
    public ItemDetail(String name, String description) {
        this();
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ItemReference> getReferences() {
        return references;
    }
    
}
