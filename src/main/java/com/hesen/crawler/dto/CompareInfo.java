package com.hesen.crawler.dto;

public class CompareInfo {
    private String websiteName;
    private Double maxPrice;
    private Double minPrice;
    private Double averagePrice;

    public String getWebsiteName() {
        return websiteName;
    }

    public void setWebsiteName(String websiteName) {
        this.websiteName = websiteName;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public Double getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(Double averagePrice) {
        this.averagePrice = averagePrice;
    }

    @Override
    public String toString() {
        return "CompareInfo{" +
                "websiteName='" + websiteName + '\'' +
                ", maxPrice=" + maxPrice +
                ", minPrice=" + minPrice +
                ", averagePrice=" + averagePrice +
                '}';
    }
}
