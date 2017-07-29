/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carljmosca.vsite.ui;

import com.carljmosca.vsite.service.ItemService;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ExternalResource;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author moscac
 */
@SpringView(name = IntroductionView.VIEW_NAME)
public class IntroductionView extends VerticalLayout implements View {

    public static final String VIEW_NAME = "";
    public static final String VIEW_DESCRIPTION = "Introduction";

    @Autowired
    ItemService service;
    private final VerticalLayout mainLayout;
    private final VerticalLayout itemsLayout;

    public IntroductionView() {
        mainLayout = new VerticalLayout();
        itemsLayout = new VerticalLayout();
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        addComponent(mainLayout);
        HorizontalLayout headerLayout = new HorizontalLayout();
        mainLayout.addComponent(headerLayout);
        mainLayout.addComponent(itemsLayout);
        refresh("");
    }

    private void refresh(String search) {
        itemsLayout.removeAllComponents();
        service.findAll(ItemService.INTRODUCTION_ITEM_FILE, search).forEach((header) -> {
            VerticalLayout headerLayout = new VerticalLayout();
            headerLayout.addComponent(new Label(header.getName()));
            //itemsLayout.addComponent(headerLayout);
            itemsLayout.addComponent(new Panel(headerLayout));            
            
            if (!header.getImage().isEmpty()) {
                ExternalResource res = new ExternalResource(header.getImage());
                Image image = new Image(null, res);
                image.setWidth("281px");
                image.setHeight("418px");
                headerLayout.addComponent(image);
            }          
            VerticalLayout vl = new VerticalLayout();
            header.getItems().stream().map((detail) -> {
                Panel pnlDetail = new Panel(detail.getName());
                RichTextArea ta = new RichTextArea();                
                ta.setValue(detail.getDescription());
                pnlDetail.setContent(ta);
                itemsLayout.addComponent(pnlDetail);
                ta.setSizeFull();
                return ta;
            }).forEachOrdered((ta) -> {
                ta.setReadOnly(true);
            });
        });
    }

}
