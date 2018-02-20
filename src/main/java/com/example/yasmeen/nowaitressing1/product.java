package com.example.yasmeen.nowaitressing1;
public class product {
    private String id;
    private String name;
    private String price;
    private String description;
    private  String imageOfItem;
    private String price_medium;
    private String price_large;
    //Constructor

    public product(String id, String name, String price, String description,String imageOfItem,String price_medium,String price_large) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.imageOfItem = imageOfItem;
        this.price_medium=price_medium;
        this.price_large=price_large;
    }

    //Setter, getter

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
    public String getImageOfItem() {
        return imageOfItem;
    }

    public void setImageOfItem(String price) {
        this.price = imageOfItem;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getPrice_medium() {
        return price_medium;
    }

    public void setPrice_medium(String price_medium) {
        this.price_medium = price_medium;
    }
    public String getPrice_large() {
        return price_large;
    }

    public void setPrice_large(String price_large) {
        this.price_large = price_large;
    }

}

