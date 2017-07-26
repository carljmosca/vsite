/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carljmosca.vsite;

import com.carljmosca.vsite.ui.ContactView;
import com.carljmosca.vsite.ui.IntroductionView;
import com.carljmosca.vsite.ui.ProjectsView;
import com.carljmosca.vsite.ui.TechnologiesView;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringNavigator;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.UI;
import org.vaadin.teemusa.sidemenu.SideMenu;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author moscac
 */
@SpringUI
public class VSiteUI extends UI {

//    @Autowired
//    IntroductionView introductionView;
//    @Autowired
//    TechnologiesView technologiesView;
//    @Autowired
//    ContactView contactView;
    SideMenu sideMenu = new SideMenu();

    @Autowired
    SpringNavigator navigator;
    @Autowired
    private SpringViewProvider springViewProvider;

    @Override
    protected void init(VaadinRequest request) {
        setContent(sideMenu);
        //Navigator navigator = HistoryApiNavigatorFactory.createHistoryApiNavigator(this, sideMenu);
        //final VerticalLayout root = new VerticalLayout();
        //SpringNavigator navigator = new SpringNavigator(this, sideMenu);
        //navigator.addView(TechnologiesView.VIEW_NAME, TechnologiesView.class);
        navigator.init(this, sideMenu);
        navigator.addProvider(springViewProvider);
        //setContent(root);
        //setNavigator(navigator);
        //navigator.addProvider(springViewProvider);
        //navigator.addView(IntroductionView.VIEW_NAME, new IntroductionView());
        //navigator.addView(TechnologiesView.VIEW_NAME, technologiesView);
        //navigator.addView(ProjectsView.VIEW_NAME, new ProjectsView());
        //navigator.addView(ContactView.VIEW_NAME, contactView);
        sideMenu.addNavigation(IntroductionView.VIEW_DESCRIPTION, IntroductionView.VIEW_NAME);
        sideMenu.addNavigation(TechnologiesView.VIEW_DESCRIPTION, TechnologiesView.VIEW_NAME);
        sideMenu.addNavigation(ProjectsView.VIEW_DESCRIPTION, ProjectsView.VIEW_NAME);
        sideMenu.addNavigation(ContactView.VIEW_DESCRIPTION, ContactView.VIEW_NAME);
    }

}
