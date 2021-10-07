package com.fury.myaccounts.data;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.exceptions.RealmException;

/**
 * Created by fury on 9/14/2017.
 */
public class RealmHelper3 {

    Realm realm;
    RealmResults<Spacecraft3> spacecrafts;
    Boolean saved = null;

    public RealmHelper3(Realm realm){
        this.realm = realm;
    }

    //Write
    public Boolean save(final Spacecraft3 spacecraft){
        if (spacecraft == null){
            saved = false;
        }else {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    try {
                        realm.copyToRealm(spacecraft);
                        saved = true;
                    } catch (RealmException e) {
                        saved = false;
                    }
                }
            });
        }


        return saved;
    }

    //Read
    public void retrievefromDB(){
        spacecrafts = realm.where(Spacecraft3.class).findAll();
    }

    //Refresh
    public ArrayList<Spacecraft3> refresh(){
        ArrayList<Spacecraft3> lasted = new ArrayList<>();
        for (Spacecraft3 s : spacecrafts){
            lasted.add(s);
        }
        return lasted;
    }

}
