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
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.UI;
import de.akquinet.engineering.vaadin.HistoryApiNavigatorFactory;
import org.vaadin.teemusa.sidemenu.SideMenu;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author moscac
 */
@SpringUI
public class VSiteUI extends UI {

    @Autowired
    private SpringViewProvider springViewProvider;

    @Override
    protected void init(VaadinRequest request) {
        SideMenu sideMenu = new SideMenu();
        //sideMenu.setCaption("Test");
        //sideMenu.setDescription("Test Desc");
        setContent(sideMenu);
        Navigator navigator = HistoryApiNavigatorFactory.createHistoryApiNavigator(this, sideMenu);
        navigator.addProvider(springViewProvider);

        sideMenu.addNavigation(IntroductionView.VIEW_DESCRIPTION, IntroductionView.VIEW_NAME);
        sideMenu.addNavigation(TechnologiesView.VIEW_DESCRIPTION, TechnologiesView.VIEW_NAME);
        sideMenu.addNavigation(ProjectsView.VIEW_DESCRIPTION, ProjectsView.VIEW_NAME);
        sideMenu.addNavigation(ContactView.VIEW_DESCRIPTION, ContactView.VIEW_NAME);
    }

}
