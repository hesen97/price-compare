package com.hesen.crawler.entity;

public class Phone {
    private Integer phoneId;
    private String sku;
    private String imageUrl;
    private String detailUrl;
    private String title;
    private Double price;
    private Integer websiteId;

    public Integer getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(Integer phoneId) {
        this.phoneId = phoneId;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getWebsiteId() {
        return websiteId;
    }

    public void setWebsiteId(Integer websiteId) {
        this.websiteId = websiteId;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "phoneId=" + phoneId +
                ", sku='" + sku + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", detailUrl='" + detailUrl + '\'' +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", websiteId=" + websiteId +
                '}';
    }
}
