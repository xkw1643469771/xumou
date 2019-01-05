package com.xumou.sys.html.po;

import java.util.List;

public class Catalog {

    private List<Item> parent;

    private List<Item> items;

    public List<Item> getParent() {
        return parent;
    }

    public void setParent(List<Item> parent) {
        this.parent = parent;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}