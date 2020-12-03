package com.hesen.crawler.entity;

public class Item {
    private Integer itemId;
    private String sku;
    private String itemTitle;
    private Integer brandId;
    private String descUrl;
    private String image;

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getDescUrl() {
        return descUrl;
    }

    public void setDescUrl(String descUrl) {
        this.descUrl = descUrl;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemId=" + itemId +
                ", sku='" + sku + '\'' +
                ", itemTitle='" + itemTitle + '\'' +
                ", brandId=" + brandId +
                ", descUrl='" + descUrl + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
