package com.example.klearndemo.entity;

public class Item {

    private String desc;
    private String cover;

    public Item(String desc, String cover) {
        this.desc = desc;
        this.cover = cover;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    @Override
    public String toString() {
        return "Item{" +
                "desc='" + desc + '\'' +
                ", cover='" + cover + '\'' +
                '}';
    }
}
