package com.ydn.church.Entity;

import java.util.ArrayList;

public class

Malsseum {

    private String mainCategory;

    private ArrayList<String> category;

    public String getMainCategory() {
        return mainCategory;
    }

    public void setMainCategory(String mainCategory) {
        this.mainCategory = mainCategory;
    }

    public ArrayList<String> getCategory() {
        return category;
    }

    public void setCategory(ArrayList<String> category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Malsseum{" +
                "mainCategory='" + mainCategory + '\'' +
                ", category=" + category +
                '}';
    }


}
