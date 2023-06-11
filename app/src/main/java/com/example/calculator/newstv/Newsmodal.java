package com.example.calculator.newstv;

public class Newsmodal {

    private String img  , heading , subheading , url , descp;

    public Newsmodal(String img, String heading, String subheading , String url , String descp) {
        this.img = img;
        this.heading = heading;
        this.subheading = subheading;
        this.url = url;
        this.descp = descp;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getSubheading() {
        return subheading;
    }

    public void setSubheading(String subheading) {
        this.subheading = subheading;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescp() {
        return descp;
    }

    public void setDescp(String descp) {
        this.descp = descp;
    }
}
