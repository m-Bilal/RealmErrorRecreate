package com.bilal.realmerrorrecreate;

import android.util.Log;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

public class MyRealmModel extends RealmObject {

    @Ignore
    private static final String TAG = "MyRealmModel";

    @PrimaryKey
    public int id;
    public int minAge;
    public int maxAge;
    public String name;

    public static void saveModels() {
        Realm realm = Realm.getDefaultInstance();
        int minAge = 1;
        int maxAge = 10;
        String[] names = {"a", "b", "c", "d"};
        realm.beginTransaction();
        RealmResults<MyRealmModel> realmResults = realm.where(MyRealmModel.class).findAll();
        realmResults.deleteAllFromRealm();
        for(int i =0; i < names.length; i++, minAge++, maxAge++) {
            MyRealmModel myRealmModel = new MyRealmModel();
            myRealmModel.id = i;
            myRealmModel.maxAge = maxAge;
            myRealmModel.minAge = minAge;
            myRealmModel.name = names[i];
            realm.copyToRealm(myRealmModel);
        }
        realm.commitTransaction();
        realm.close();
    }

    public static RealmResults<MyRealmModel> getModels() {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<MyRealmModel> realmResults = realm.where(MyRealmModel.class).findAll();
        for (MyRealmModel i : realmResults) {
            Log.d(TAG, "id: " + i.id);
            Log.d(TAG, "minAge: " + i.minAge);
            Log.d(TAG, "maxAge: " + i.maxAge);
            Log.d(TAG, "name: " + i.name);
        }
        return realmResults;
    }
}
