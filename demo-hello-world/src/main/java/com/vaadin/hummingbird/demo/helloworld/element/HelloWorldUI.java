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
package com.vaadin.hummingbird.demo.helloworld.element;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.hummingbird.html.Button;
import com.vaadin.hummingbird.html.Div;
import com.vaadin.hummingbird.html.Input;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

/**
 * UI which demonstrates how a simple Hello World type application can be
 * created.
 */
public class HelloWorldUI extends UI {

    /**
     * The main servlet for the application.
     */
    @WebServlet(urlPatterns = "/*", name = "UIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = HelloWorldUI.class, productionMode = false)
    public static class Servlet extends VaadinServlet {
    }

    @Override
    protected void init(VaadinRequest request) {
        Input textInput = new Input();
        textInput.setId("inputId");
        textInput.setPlaceholder("Enter your name");

        Button button = new Button("Say hello");
        button.addClickListener(e -> {
            Div hello = new Div();
            hello.setText("Hello " + textInput.getValue());
            hello.setClassName("helloText");
            add(hello);
        });

        add(textInput, button);
    }

}