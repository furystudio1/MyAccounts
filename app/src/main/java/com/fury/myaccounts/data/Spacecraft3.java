package com.fury.myaccounts.data;

import io.realm.RealmObject;

/**
 * Created by fury on 9/14/2017.
 */
public class Spacecraft3 extends RealmObject {

    String Name;
    String User;
    String Password;
    String Email;
    String Mobile;
    String Daiitle;
    int NOE;
    int back;
    int Arm;

    @Override
    public String toString() {
        return "Spacecraft2{" +
                "NOE='" + NOE + '\'' +
                ", Name='" + Name + '\'' +
                ", User='" + User + '\'' +
                ", Password='" + Password + '\'' +
                ", Email='" + Email + '\'' +
                ", Mobile='" + Mobile + '\'' +
                ", Daiitle='" + Daiitle + '\'' +
                ", back=" + back +
                ", Arm=" + Arm +
                '}';
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getDaiitle() {
        return Daiitle;
    }

    public void setDaiitle(String daiitle) {
        Daiitle = daiitle;
    }

    public int getBack() {
        return back;
    }

    public void setBack(int back) {
        this.back = back;
    }

    public int getNOE() {
        return NOE;
    }

    public void setNOE(int NOE) {
        this.NOE = NOE;
    }

    public int getArm() {
        return Arm;
    }

    public void setArm(int arm) {
        Arm = arm;
    }
}
