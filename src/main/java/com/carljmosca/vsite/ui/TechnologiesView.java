/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carljmosca.vsite.ui;

import com.carljmosca.vsite.data.TechnologyDetail;
import com.carljmosca.vsite.data.TechnologyHeader;
import com.carljmosca.vsite.service.TechnologyService;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author moscac
 */
@SpringView(name = TechnologiesView.VIEW_NAME)
public class TechnologiesView extends VerticalLayout implements View {

    public static final String VIEW_NAME = "TECHNOLOGIES";
    public static final String VIEW_DESCRIPTION = "Technologies";
    @Autowired
    TechnologyService service;
    private final VerticalLayout mainLayout;
    
    public TechnologiesView() {
        mainLayout = new VerticalLayout();
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        mainLayout.removeAllComponents();
        addComponent(mainLayout);
        for (TechnologyHeader header : service.findAll()) {
            Panel pnlHeader = new Panel();
            pnlHeader.setContent(new Label(header.getName()));
            mainLayout.addComponent(pnlHeader);
            //HorizontalLayout hl = new HorizontalLayout();
            //hl.addComponent(new Label(header.getName()));
            VerticalLayout vl = new VerticalLayout();
            //hl.addComponent(vl);
            for (TechnologyDetail detail : header.getItems()) {
                Panel pnlDetail = new Panel(detail.getName());
                TextArea ta = new TextArea(); //"",  detail.getDescription());
                ta.setValue(detail.getDescription());
                pnlDetail.setContent(ta);
                mainLayout.addComponent(pnlDetail);
                ta.setSizeFull();
                ta.setReadOnly(true);
                //vl.addComponent(ta);
            }
            //mainLayout.addComponent(hl);
        }        
    }

}
