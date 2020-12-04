package com.hesen.crawler.enums;

public enum Accuracy {
    UNDEFINE(0, -1.0), GENERAL_ACCURACY(1, 0.15),
    MEDIUM_ACCURACY(2, 0.1), HIGH_ACCURACY(3, 0.05);

    private int level;
    private double value;

    private Accuracy(int level, double value) {
        this.level = level;
        this.value = value;
    }

    public int getLevel() {
        return level;
    }

    public double getValue() {
        return value;
    }

    public static Accuracy getInstanceByLevel(int level) {
        for (Accuracy accuracy : Accuracy.values()) {
            if (level == accuracy.getLevel()) {
                return accuracy;
            }
        }
        return null;
    }
}
