package com.alpesh1.news_admin;

public class UserModel {

    public String key,title,date,des,content;

    public UserModel(String s) {

    }

//    public UserModel(String key, String title, String date, String des, String content) {
//        this.key = key;
//        this.title = title;
//        this.date = date;
//        this.des = des;
//        this.content = content;
//        this.img = img;
//    }


    public UserModel(String key, String title, String date, String des, String content) {
        this.key = key;
        this.title = title;
        this.date = date;
        this.des = des;
        this.content = content;

    }
}
