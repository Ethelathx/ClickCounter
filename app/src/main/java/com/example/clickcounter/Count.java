package com.example.clickcounter;

import java.io.Serializable;

public class Count implements Serializable {
    private int _id;
    private String title;
    private int counts;

    public Count(int _id, String title, int counts) {
        this._id = _id;
        this.title = title;
        this.counts = counts;
    }


    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCounts() {
        return counts;
    }

    public void setCounts(int counts) {
        this.counts = counts;
    }
}
