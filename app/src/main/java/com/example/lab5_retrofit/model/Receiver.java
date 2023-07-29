package com.example.lab5_retrofit.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Receiver {

    @SerializedName("data")
    private ArrayList<User> data;
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ArrayList<User> getData() {
        return data;
    }

    public void setData(ArrayList<User> data) {
        this.data = data;
    }
}
