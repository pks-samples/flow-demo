/*
 * Copyright 2000-2016 Vaadin Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.vaadin.hummingbird.demo.dynamicmenu;

import com.vaadin.hummingbird.router.LocationChangeEvent;
import com.vaadin.ui.Template;
import com.vaadin.ui.UI;

/**
 * Layout showing the main menu above a sub view.
 *
 * @since
 * @author Vaadin Ltd
 */
public final class MainLayout extends Template {

    private final Menu menu;

    /**
     * Creates a new layout.
     */
    public MainLayout() {
        UI.getCurrent().getPage().addStyleSheet("css/site.css");

        menu = new Menu();
        getElement().appendChild(menu.getElement());
    }

    @Override
    public void onLocationChange(LocationChangeEvent locationChangeEvent) {
        menu.update(locationChangeEvent);
    }

}
