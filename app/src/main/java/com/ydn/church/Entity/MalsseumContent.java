package com.ydn.church.Entity;

import java.util.ArrayList;

public class

MalsseumContent {

    private String title;

    private String subTitle;

    private String page;

    private String section;

    private String contents;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    @Override
    public String toString() {
        return "MalsseumContent{" +
                "title='" + title + '\'' +
                ", subTitle='" + subTitle + '\'' +
                ", page='" + page + '\'' +
                ", section='" + section + '\'' +
                ", contents='" + contents + '\'' +
                '}';
    }
    public String contentToString(){

        return page + "   " + section + "   " + contents;
    }
}
