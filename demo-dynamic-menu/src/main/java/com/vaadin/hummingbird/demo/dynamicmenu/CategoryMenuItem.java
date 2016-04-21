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

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import com.vaadin.hummingbird.demo.dynamicmenu.backend.DataService;
import com.vaadin.hummingbird.demo.dynamicmenu.data.Product;
import com.vaadin.hummingbird.dom.Element;
import com.vaadin.hummingbird.html.HtmlContainer;

/**
 * A category menu item component.
 * 
 * @author Vaadin Ltd
 *
 */
public final class CategoryMenuItem extends MenuItemComponent {

    private static final String CLASS_EXPANDED = "expanded";

    private Map<Integer, MenuItemComponent> productItems = new HashMap<>();

    /**
     * Creates a new category component.
     * 
     * @param id
     *            category id
     * @param name
     *            category name
     */
    public CategoryMenuItem(int id, String name) {
        super("category", id, name);
        setClassName("category");
        setExpanded(false);
    }

    /**
     * Expands the item.
     */
    public void expand() {
        if (isExpanded()) {
            return;
        }

        setExpanded(true);

        // The link is always the first child, the sub menu is the second
        Element routerLinkElement = getElement().getChild(0);
        HtmlContainer ul = new HtmlContainer("ul");
        add(ul);

        String categoryId = routerLinkElement.getAttribute("href")
                .replace("category/", "");
        Stream<Product> products = DataService.get()
                .getProducts(Integer.parseInt(categoryId));
        products.forEach(p -> {
            MenuItemComponent product = new MenuItemComponent("product", p.getId(),
                    p.getProductName());
            ul.add(product);

            productItems.put(p.getId(), product);
        });
    }

    /**
     * Collapse the item.
     */
    public void collapse() {
        if (!isExpanded()) {
            return;
        }

        // First child is the link, remove everything else
        while (getElement().getChildCount() > 1) {
            getElement().removeChild(1);
        }
        setExpanded(false);
    }

    /**
     * Select the product by its {@code productId}.
     * 
     * @param productId
     *            product id
     */
    public void selectProduct(int productId) {
        if (productId != -1) {
            Optional<MenuItemComponent> productElement = getElementForProduct(
                    DataService.get().getProductById(productId));
            if (productElement.isPresent()) {
                productItems.values().forEach(item -> item.select(false));
                productElement.get().select(true);
            }
        }
    }

    private boolean isExpanded() {
        return getElement().getChildCount() > 1;
    }

    private void setExpanded(boolean expanded) {
        getElement().getClassList().set(CLASS_EXPANDED, expanded);
    }

    private Optional<MenuItemComponent> getElementForProduct(
            Optional<Product> product) {
        if (!product.isPresent()) {
            return Optional.empty();
        }

        return Optional.ofNullable(productItems.get(product.get().getId()));
    }

}