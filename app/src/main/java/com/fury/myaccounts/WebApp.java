package com.fury.myaccounts;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.fury.myaccounts.data.CustomAdapter3;
import com.fury.myaccounts.data.RealmHelper3;
import com.fury.myaccounts.data.Spacecraft3;
import com.melnykov.fab.FloatingActionButton;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmConfiguration;

/**
 * Created by fury on 9/25/2017.
 */
public class WebApp extends Activity {

    Realm realm;
    RealmChangeListener realmChangeListener;
    CustomAdapter3 adapter ;

    SharedPreferences one_play_preferences;
    SharedPreferences.Editor one_play_editor;
    String PasswordAll;
    int Color;
    static Activity act;

    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.page3);

        one_play_preferences = getApplicationContext().getSharedPreferences("PROJECT_NAME", android.content.Context.MODE_PRIVATE);
        one_play_editor = one_play_preferences.edit();
        PasswordAll = one_play_preferences.getString("PFURY", "");
        Color = one_play_preferences.getInt("colorday", 1);

        act = this;

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        Realm.init(this);
        RealmConfiguration config2 = new RealmConfiguration.Builder().name("myrealmwebapp.realm").build();
        realm = Realm.getInstance(config2);
        final RealmHelper3 realmHelper = new RealmHelper3(realm);
        realmHelper.retrievefromDB();

        ImageView ImNoCard = (ImageView) findViewById(R.id.ImNoCard);
        TextView textNoCard = (TextView) findViewById(R.id.textNoCard);
        TextView ttop = (TextView) findViewById(R.id.ttop);
        ImageView setting = (ImageView) findViewById(R.id.setting);
        ImageView cardview = (ImageView) findViewById(R.id.cardview);
        ImageView emailview = (ImageView) findViewById(R.id.emailview);
        ImageView webview = (ImageView) findViewById(R.id.webview);

        Typeface face = Typeface.createFromAsset(getAssets(), "Yantramanav-Light.ttf");

        textNoCard.setTypeface(face);
        ttop.setTypeface(face);

        lv = (ListView) findViewById(R.id.lv);

        cardview.setImageResource(R.drawable.creditcardoff);
        emailview.setImageResource(R.drawable.emailoff);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.show();
        //fab.hide();
        fab.attachToListView(lv);

        Spacecraft3 user = realm.where(Spacecraft3.class).findFirst();

        if (user != null) {
            ImNoCard.setVisibility(View.GONE);
            textNoCard.setVisibility(View.GONE);
        } else {
            ImNoCard.setVisibility(View.VISIBLE);
            textNoCard.setVisibility(View.VISIBLE);
            lv.setVisibility(View.GONE);
        }

        if (Color == 1){
            fab.setColorNormal(getResources().getColor(R.color.colorstatus1));
            fab.setColorPressed(getResources().getColor(R.color.colorbtnPressed1));
            fab.setColorRipple(getResources().getColor(R.color.ripple1));
            setting.setImageResource(R.drawable.settingsday);
            webview.setImageResource(R.drawable.webday);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.setStatusBarColor(ContextCompat.getColor(this,R.color.colorstatus1));
            }
        }else if (Color == 2){
            fab.setColorNormal(getResources().getColor(R.color.colorstatus2));
            fab.setColorPressed(getResources().getColor(R.color.colorbtnPressed2));
            fab.setColorRipple(getResources().getColor(R.color.ripple2));
            setting.setImageResource(R.drawable.settingsdark);
            webview.setImageResource(R.drawable.webdark);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.setStatusBarColor(ContextCompat.getColor(this,R.color.colorstatus2));
            }
        }


        adapter = new CustomAdapter3(this,realmHelper.refresh());
        lv.setAdapter(adapter);

        realmChangeListener = new RealmChangeListener() {
            @Override
            public void onChange(Object o) {
                adapter = new CustomAdapter3(WebApp.this,realmHelper.refresh());
                lv.setAdapter(adapter);
            }
        };
        realm.addChangeListener(realmChangeListener);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent CreateCard = new Intent(WebApp.this, CreateWebApp.class);
                startActivity(CreateCard);
                finish();
            }
        });

        cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent CreateCard = new Intent(WebApp.this, CreditCard.class);
                startActivity(CreateCard);
                overridePendingTransition(0, 0);
                finish();
            }
        });

        emailview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent CreateCard = new Intent(WebApp.this, Email.class);
                startActivity(CreateCard);
                overridePendingTransition(0, 0);
                finish();
            }
        });

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent settings = new Intent(WebApp.this, Settings.class);
                startActivity(settings);
                overridePendingTransition(0, 0);
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.removeChangeListener(realmChangeListener);
        realm.close();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent CreateCard = new Intent(WebApp.this, CreditCard.class);
        startActivity(CreateCard);
        overridePendingTransition(0, 0);
        finish();
    }
}
