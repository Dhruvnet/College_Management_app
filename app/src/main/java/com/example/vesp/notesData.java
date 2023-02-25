package com.example.vesp;

public class notesData {
    private String name,url;

    public notesData() {
    }

    public notesData(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public notesData(Object name) {
    }

//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getUrl() {
//        return url;
//    }
//
//    public void setUrl(String url) {
//        this.url = url;
//    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }



}
