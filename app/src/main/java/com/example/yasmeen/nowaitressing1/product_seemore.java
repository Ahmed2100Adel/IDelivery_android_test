package com.example.yasmeen.nowaitressing1;

import java.lang.ref.PhantomReference;
import java.net.URI;
import java.util.Queue;

/**
 * Created by NgocTri on 11/15/2015.
 */
public class product_seemore {
    private String id;
   private String url;
    private String name;
    private String size;
    private String quantity;
    private String price;
    private String comments;
    private String priceMedium;
    private String priceLarge;
   // private String info;
    private String moreInfo;
    //Constructor

    public product_seemore(String id,String name,String url,String quantity,String size,String price,String comments,String priceMedium,String priceLarge,String moreInfo) {
        this.id = id;
        this.name = name;
        this.url =url;
        this.name=name;
        this.quantity=quantity;
        this.size=size;
        this.price=price;
        this.comments=comments;
        this.priceMedium =priceMedium;
        this.priceLarge = priceLarge;
        this.priceMedium=priceMedium;
        this.priceLarge=priceLarge;
        this.moreInfo=moreInfo;
     //   this.info =info;
    }

    //Setter, getter

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getSize() {
        return size;
    }
    public void setSize(String size) {
        this.size=size;
    }

    public String getPrice() {
        return price;
    }
    public void setPrice (String Price){
        this.price=price;
    }

    public String getcomment() {
        return comments;
    }
    public void setcomment (String comments){
        this.comments=comments;
    }

    public String getPriceMedium() {
        return priceMedium;
    }

    public void setPriceMedium(String priceMedium) {
        this.priceMedium = priceMedium;
    }
    public String getPriceLarge() {
        return priceLarge;
    }

    public void setPriceLarge(String priceLarge) {
        this.priceLarge = priceLarge;
    }

    public String getMoreInfo() {
        return moreInfo;
    }

    public void setMoreInfo(String moreInfo) {
        this.moreInfo = moreInfo;
    }
    /*  public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity= quantity;
    }
 public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price= price;
    }
public String getComments() {
        return comments;
    }

    public void setcomments(String comments) {
        this.comments= comments;
    }

*/
}
