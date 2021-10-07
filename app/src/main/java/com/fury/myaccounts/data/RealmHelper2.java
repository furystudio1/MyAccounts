package com.fury.myaccounts.data;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.exceptions.RealmException;

/**
 * Created by fury on 9/14/2017.
 */
public class RealmHelper2 {

    Realm realm;
    RealmResults<Spacecraft2> spacecrafts;
    Boolean saved = null;

    public RealmHelper2(Realm realm){
        this.realm = realm;
    }

    //Write
    public Boolean save(final Spacecraft2 spacecraft){
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
        spacecrafts = realm.where(Spacecraft2.class).findAll();
    }

    //Refresh
    public ArrayList<Spacecraft2> refresh(){
        ArrayList<Spacecraft2> lasted = new ArrayList<>();
        for (Spacecraft2 s : spacecrafts){
            lasted.add(s);
        }
        return lasted;
    }

}
