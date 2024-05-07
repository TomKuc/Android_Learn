package com.example.firebaseapp;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class User extends BaseObservable {
    private String name;
    private String phoneNumber;
    private String group;

    public User() {

    }
    public User(String name, String phoneNumber, String group) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.group = group;
    }

    @Bindable
    public String getGroup() {
        return group;
    }

    @Bindable
    public String getName() {
        return name;
    }

    @Bindable
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setName(String userName) {
        this.name = userName;
        notifyPropertyChanged(BR.name);
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        notifyPropertyChanged(BR.phoneNumber);
    }

    public void setGroup(String group) {
        this.group = group;
        notifyPropertyChanged(BR.group);
    }
}
