package com.example.superordenata.realmprueba.models;


import com.example.superordenata.realmprueba.app.MyApplication;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Nota extends RealmObject {

    @PrimaryKey
    private int id;
    @Required
    private String title;
    private String note;
    @Required
    private Date date;

    public Nota() {
    }

    public Nota(String title, String note) {
        this.id = MyApplication.NotaID.incrementAndGet();
        this.title = title;
        this.note = note;
        this.date = new Date();
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getDate() {
        return date;
    }
}
