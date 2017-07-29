/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carljmosca.vsite.ui;

import com.carljmosca.vsite.service.ItemService;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author moscac
 */
@SpringView(name = ProjectsView.VIEW_NAME)
public class ProjectsView extends VerticalLayout implements View {

    public static final String VIEW_NAME = "PROJECTS";
    public static final String VIEW_DESCRIPTION = "Projects";

    @Autowired
    ItemService service;
    private final VerticalLayout mainLayout;
    private final VerticalLayout itemsLayout;

    public ProjectsView() {
        mainLayout = new VerticalLayout();
        itemsLayout = new VerticalLayout();
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        addComponent(mainLayout);
        HorizontalLayout headerLayout = new HorizontalLayout();
        mainLayout.addComponent(headerLayout);
        mainLayout.addComponent(itemsLayout);
//        TextField teSearch = new TextField("", (HasValue.ValueChangeEvent<String> searchEvent) -> {
//            refresh(searchEvent.getValue());
//        });
//        teSearch.setPlaceholder("search");
//        headerLayout.addComponent(teSearch);
        refresh("");
    }

    private void refresh(String search) {
        itemsLayout.removeAllComponents();
        service.findAll(ItemService.PROJECTS_ITEM_FILE, search).forEach((header) -> {
            Panel pnlHeader = new Panel();
            pnlHeader.setContent(new Label(header.getName()));
            itemsLayout.addComponent(pnlHeader);
            VerticalLayout vl = new VerticalLayout();
            header.getItems().stream().map((detail) -> {
                Panel pnlDetail = new Panel(detail.getName());
                TextArea ta = new TextArea();
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
