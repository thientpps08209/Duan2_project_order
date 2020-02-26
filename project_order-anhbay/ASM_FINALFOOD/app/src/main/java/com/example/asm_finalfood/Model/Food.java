package com.example.asm_finalfood.Model;

import com.google.gson.annotations.SerializedName;

public class Food {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;

    @SerializedName("price")
    private String price;

    @SerializedName("create_day")
    private String create_day;

    @SerializedName("description")
    private String description;

    @SerializedName("picture")
    private String picture;
    @SerializedName("love")
    private Boolean love;
    @SerializedName("value")
    private String value;
    @SerializedName("message")
    private String massage;

    public Food(int id, String name, String price, String create_day, String picture, Boolean love, String value, String massage) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.create_day = create_day;
        this.picture = picture;
        this.love = love;
        this.value = value;
        this.massage = massage;
    }

    public Food(String name, String price, String create_day, String picture, Boolean love, String value, String massage) {
        this.name = name;
        this.price = price;
        this.create_day = create_day;
        this.picture = picture;
        this.love = love;
        this.value = value;
        this.massage = massage;
    }

    public Food() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCreate_day() {
        return create_day;
    }

    public void setCreate_day(String create_day) {
        this.create_day = create_day;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Boolean getLove() {
        return love;
    }

    public void setLove(Boolean love) {
        this.love = love;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
