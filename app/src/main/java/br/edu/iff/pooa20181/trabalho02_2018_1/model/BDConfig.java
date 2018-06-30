package br.edu.iff.pooa20181.trabalho02_2018_1.model;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class BDConfig extends Application{
    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(getApplicationContext());

        RealmConfiguration.Builder builderCandidato = new RealmConfiguration.Builder();
        builderCandidato.name("Trabalho2.realm");
        builderCandidato.schemaVersion(0);
        builderCandidato.deleteRealmIfMigrationNeeded();
        RealmConfiguration realmConfiguration = builderCandidato.build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

}
