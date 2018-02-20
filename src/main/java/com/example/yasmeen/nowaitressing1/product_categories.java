package com.example.yasmeen.nowaitressing1;

/**
 * Created by NgocTri on 11/15/2015.
 */
public class product_categories {
    private int id;
    private String name;
    private String Url;

    //Constructor

    public product_categories(int id, String name, String Url) {
        this.id = id;
        this.name = name;
        this.Url = Url;
    }

    //Setter, getter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getUrl() {
        return Url;
    }

    public void setUrl(String Url) {
        this.Url = Url;
    }
}
