package com.example.lab05;

public class Image {
    private final String name;
    private final int imageResource;

    public Image(String name, int imageResource) {
        this.name = name;
        this.imageResource = imageResource;
    }

    public String getName() {
        return this.name;
    }

    public int getImageResource() {
        return this.imageResource;
    }
}
