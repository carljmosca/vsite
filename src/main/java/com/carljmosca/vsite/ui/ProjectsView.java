/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carljmosca.vsite.ui;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author moscac
 */
@SpringView(name = ProjectsView.VIEW_NAME)
public class ProjectsView extends VerticalLayout implements View {

    public static final String VIEW_NAME = "PROJECTS";
    public static final String VIEW_DESCRIPTION = "Projects";

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        VerticalLayout vl = new VerticalLayout();
        addComponent(vl);
    }

}
