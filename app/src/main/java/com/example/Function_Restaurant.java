package com.example.huangyoude.sa;

public class Function_Restaurant {
    private String name;
    private String image;
    private String description;

    public Function_Restaurant(String name, String image, String description) {
        this.name = name;
        this.image = image;
        this.description = description;
    }
    public Function_Restaurant(){

    }
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {

        this.description = description;
    }
}
