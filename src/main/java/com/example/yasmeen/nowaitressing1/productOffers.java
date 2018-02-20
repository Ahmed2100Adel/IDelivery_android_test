package com.example.yasmeen.nowaitressing1;

/**
 * Created by yasmeen on 1/31/2018.
 */

public class productOffers {
    private String id;
    private String name;
    private String url;
    private String numsOfOrders;
    private String inDays;
    private String howMuchMinus;
    private String howMuchPercentage;
    private String serialNumber;

    public productOffers(String id, String name, String url, String numsOfOrders, String inDays, String howMuchMinus, String howMuchPercentage, String serialNumber) {

        this.id = id;
        this.name = name;
        this.url = url;
        this.numsOfOrders = numsOfOrders;
        this.inDays = inDays;
        this.howMuchMinus = howMuchMinus;
        this.howMuchPercentage = howMuchPercentage;
        this.serialNumber = serialNumber;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setNumsOfOrders(String numsOfOrders) {
        this.numsOfOrders = numsOfOrders;
    }

    public void setInDays(String inDays) {
        this.inDays = inDays;
    }

    public void setHowMuchMinus(String howMuchMinus) {
        this.howMuchMinus = howMuchMinus;
    }

    public void setHowMuchPercentage(String howMuchPercentage) {
        this.howMuchPercentage = howMuchPercentage;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getId() {

        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getNumsOfOrders() {
        return numsOfOrders;
    }

    public String getInDays() {
        return inDays;
    }

    public String getHowMuchMinus() {
        return howMuchMinus;
    }

    public String getHowMuchPercentage() {
        return howMuchPercentage;
    }

    public String getSerialNumber() {
        return serialNumber;
    }


}
