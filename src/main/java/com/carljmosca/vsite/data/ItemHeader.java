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
public class ItemHeader implements Serializable {

    private int index;
    private String name;
    private final List<ItemDetail> items;
    private String image;

    public ItemHeader() {
        items = new ArrayList<>();
        image = "";
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ItemDetail> getItems() {
        return items;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        if (image != null) {
            this.image = image;
        }
    }

}
