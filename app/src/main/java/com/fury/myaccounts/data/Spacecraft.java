package com.fury.myaccounts.data;

import io.realm.RealmObject;

/**
 * Created by fury on 9/14/2017.
 */
public class Spacecraft extends RealmObject {

    @Override
    public String toString() {
        return "Spacecraft{" +
                "NumberCard='" + NumberCard + '\'' +
                ", CCV='" + CCV + '\'' +
                ", Date='" + Date + '\'' +
                ", Name='" + Name + '\'' +
                ", Pass='" + Pass + '\'' +
                ", Email='" + Email + '\'' +
                ", back=" + back +
                ", bank=" + bank +
                '}';
    }

    String NumberCard;
    String CCV;
    String Date;
    String Name;
    String Pass;
    String Email;
    int back;
    int bank;

    public int getBank() {
        return bank;
    }

    public void setBank(int bank) {
        this.bank = bank;
    }

    public int getBack() {
        return back;
    }

    public void setBack(int back) {
        this.back = back;
    }

    public String getNumberCard() {
        return NumberCard;
    }

    public void setNumberCard(String numberCard) {
        NumberCard = numberCard;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getCCV() {
        return CCV;
    }

    public void setCCV(String CCV) {
        this.CCV = CCV;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String pass) {
        Pass = pass;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
