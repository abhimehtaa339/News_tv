package com.example.calculator.newstv;

public class catogeries_modal {
   private String catogery , img_url;

    public catogeries_modal(String catogery, String url) {
        this.catogery = catogery;
        this.img_url = url;
    }


    public String getCatogery() {
        return catogery;
    }

    public void setCatogery(String catogery) {
        this.catogery = catogery;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String url) {
        this.img_url = url;
    }
}
