package com.example.myapplications.Data;

public class Poem {

    private int id;

    private String name;

    private String time;

    private String title;

    private String jiaqin;


    public Poem() {

    }

    public Poem(int id, String name, String time , String title , String jiaqin) {

        this.id = id;
        this.name = name;
        this.time = time;
        this.title = title;
        this.jiaqin = jiaqin;

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

    public String getTime() {

        return time;

    }

    public void setTime(String time) {

        this.time = time;

    }
    public String getTitle() {

        return title;

    }

    public void setTitle(String title) {

        this.title = title;

    }
    public String getJiaQin() {

        return jiaqin;

    }

    public void setJiaQin(String jiaQin) {

        this.jiaqin = jiaQin;

    }
}