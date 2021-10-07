package com.fury.myaccounts.data;

import io.realm.RealmObject;

/**
 * Created by fury on 9/14/2017.
 */
public class Spacecraft2 extends RealmObject {

    String Name2;
    String User;
    String Password;
    String Birthday;
    String Mobile;
    String siteEmail;
    int bac;
    int email2;

    @Override
    public String toString() {
        return "Spacecraft2{" +
                "Name2='" + Name2 + '\'' +
                ", User='" + User + '\'' +
                ", Password='" + Password + '\'' +
                ", Birthday='" + Birthday + '\'' +
                ", Mobile='" + Mobile + '\'' +
                ", siteEmail='" + siteEmail + '\'' +
                ", bac=" + bac +
                ", email2=" + email2 +
                '}';
    }

    public String getName() {
        return Name2;
    }

    public void setName(String name2) {
        Name2 = name2;
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

    public String getBirthday() {
        return Birthday;
    }

    public void setBirthday(String birthday) {
        Birthday = birthday;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public int getBack() {
        return bac;
    }

    public void setBack(int bac) {
        this.bac = bac;
    }

    public int getEmail() {
        return email2;
    }

    public void setEmail(int email2) {
        this.email2 = email2;
    }

    public String getSiteEmail() {
        return siteEmail;
    }

    public void setSiteEmail(String siteEmail) {
        this.siteEmail = siteEmail;
    }
}
