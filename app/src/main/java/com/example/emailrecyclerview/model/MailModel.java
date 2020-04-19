package com.example.emailrecyclerview.model;

import java.util.Random;

public class MailModel {
    String fullname;
    String subject;
    String content;
    String time;
    boolean isfavorite;
    int color;

    public MailModel(String fullname, String subject, String content,String time) {
        this.fullname = fullname;
        this.subject = subject;
        this.content = content;
        this.isfavorite=false;
        this.time=time;
        //
        Random random=new Random();
        this.color=random.nextInt();
    }

    public MailModel(String fullname, String subject, String content, boolean isfavorite, String time) {
        this.fullname = fullname;
        this.subject = subject;
        this.content = content;
        this.time=time;
        this.isfavorite = isfavorite;
        Random random=new Random();
        this.color=random.nextInt();
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isIsfavorite() {
        return isfavorite;
    }

    public void setIsfavorite(boolean isfavorite) {
        this.isfavorite = isfavorite;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
