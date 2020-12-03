package com.hesen.crawler.enums;

public enum Website {
    TM(1, "天猫"), JD(2, "京东");

    private int websiteId;

    private String websiteName;

    public String getWebsiteName() {
        return websiteName;
    }

    private Website(int websiteId, String websiteName) {
        this.websiteId = websiteId;
        this.websiteName = websiteName;
    }

    public int getWebsiteId() {
        return this.websiteId;
    }
}
