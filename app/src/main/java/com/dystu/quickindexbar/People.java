package com.dystu.quickindexbar;

/**
 * Created by Administrator on 2015/5/6.
 */
public class People implements Comparable<People> {

    private String name;

    private int imageId;

    private String header;


    public People() {
    }

    public People(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }


    @Override
    public int compareTo(People another) {
        return (HanziToPinyin.getInstance()
                .get(this.getName().substring(0, 1)).get(0).target.substring(0, 1).toUpperCase())
                .compareTo(HanziToPinyin.getInstance()
                        .get(another.getName().substring(0, 1)).get(0).target.substring(0, 1).toUpperCase());
    }
}
