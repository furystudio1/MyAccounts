package com.fury.myaccounts;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.anjlab.android.iab.v3.BillingProcessor;
import com.anjlab.android.iab.v3.TransactionDetails;
import com.fury.myaccounts.data.CustomAdapter;
import com.fury.myaccounts.data.RealmHelper;
import com.fury.myaccounts.data.Spacecraft;
import com.melnykov.fab.FloatingActionButton;

import java.util.Objects;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmConfiguration;

/**
 * Created by fury on 9/13/2017.
 */
public class CreditCard extends Activity implements BillingProcessor.IBillingHandler {

    Realm realm;
    RealmChangeListener realmChangeListener;
    CustomAdapter adapter ;

    SharedPreferences one_play_preferences;
    SharedPreferences.Editor one_play_editor;
    String PasswordAll;
    int Color;
    ListView lv;
    static Activity act;
    boolean buywebapp,buyEmail;
    BillingProcessor bp;
    int key_1,key_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page1);

        one_play_preferences = getApplicationContext().getSharedPreferences("PROJECT_NAME", android.content.Context.MODE_PRIVATE);
        one_play_editor = one_play_preferences.edit();
        PasswordAll = one_play_preferences.getString("PFURY", "");
        Color = one_play_preferences.getInt("colorday", 1);
        buywebapp = one_play_preferences.getBoolean("buywebapp", false);
        buyEmail = one_play_preferences.getBoolean("buyEmail", false);
        key_1 = one_play_preferences.getInt("keyemail", 0);
        key_2 = one_play_preferences.getInt("keyweb", 0);

        bp = new BillingProcessor(this, "code google", this);

        act = this;

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().name("myrealmcard.realm").build();
        realm = Realm.getInstance(config);
        final RealmHelper realmHelper = new RealmHelper(realm);
        realmHelper.retrievefromDB();

        ImageView ImNoCard = (ImageView) findViewById(R.id.ImNoCard);
        TextView textNoCard = (TextView) findViewById(R.id.textNoCard);
        TextView ttop = (TextView) findViewById(R.id.ttop);
        TextView tl = (TextView) findViewById(R.id.txprotop);
        ImageView setting = (ImageView) findViewById(R.id.setting);
        ImageView cardview = (ImageView) findViewById(R.id.cardview);
        ImageView emailview = (ImageView) findViewById(R.id.emailview);
        ImageView webview = (ImageView) findViewById(R.id.webview);
        ImageView protop = (ImageView) findViewById(R.id.protop);

        Typeface face = Typeface.createFromAsset(getAssets(), "Yantramanav-Light.ttf");

        textNoCard.setTypeface(face);
        ttop.setTypeface(face);
        tl.setTypeface(face);

        lv = (ListView) findViewById(R.id.lv);

        emailview.setImageResource(R.drawable.emailoff);
        webview.setImageResource(R.drawable.weboff);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.show();
        //fab.hide();
        fab.attachToListView(lv);

        Spacecraft user = realm.where(Spacecraft.class).findFirst();

        if (user != null) {
            ImNoCard.setVisibility(View.GONE);
            textNoCard.setVisibility(View.GONE);
        } else {
            ImNoCard.setVisibility(View.VISIBLE);
            textNoCard.setVisibility(View.VISIBLE);
            lv.setVisibility(View.GONE);
        }

        if (Color == 1){
            protop.setImageResource(R.drawable.materials_bg);
            fab.setColorNormal(getResources().getColor(R.color.colorstatus1));
            fab.setColorPressed(getResources().getColor(R.color.colorbtnPressed1));
            fab.setColorRipple(getResources().getColor(R.color.ripple1));
            setting.setImageResource(R.drawable.settingsday);
            cardview.setImageResource(R.drawable.creditcardday);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.setStatusBarColor(ContextCompat.getColor(this,R.color.colorstatus1));
            }
        }else if (Color == 2){
            protop.setImageResource(R.drawable.adspro);
            fab.setColorNormal(getResources().getColor(R.color.colorstatus2));
            fab.setColorPressed(getResources().getColor(R.color.colorbtnPressed2));
            fab.setColorRipple(getResources().getColor(R.color.ripple2));
            setting.setImageResource(R.drawable.settingsdark);
            cardview.setImageResource(R.drawable.creditcarddark);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.setStatusBarColor(ContextCompat.getColor(this,R.color.colorstatus2));
            }
        }

        adapter = new CustomAdapter(this,realmHelper.refresh());
        lv.setAdapter(adapter);

        realmChangeListener = new RealmChangeListener() {
            @Override
            public void onChange(Object o) {
                adapter = new CustomAdapter(CreditCard.this,realmHelper.refresh());
                lv.setAdapter(adapter);
            }
        };
        realm.addChangeListener(realmChangeListener);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent CreateCard = new Intent(CreditCard.this, CreateCard.class);
                startActivity(CreateCard);
                finish();
            }
        });


        emailview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (buyEmail){
                    Intent Email = new Intent(CreditCard.this, Email.class);
                    startActivity(Email);
                    overridePendingTransition(0, 0);
                    finish();
                }else {
                    (new DialogEmailbuy(CreditCard.this)).showDialog();
                }
            }
        });

        webview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (buywebapp){
                    Intent webview = new Intent(CreditCard.this, WebApp.class);
                    startActivity(webview);
                    overridePendingTransition(0, 0);
                    finish();
                }else {
                    (new DialogAppWeb(CreditCard.this)).showDialog();
                }
            }
        });

        protop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent webview = new Intent(CreditCard.this, Buy_Professional.class);
                startActivity(webview);
                overridePendingTransition(0, 0);
                finish();
            }
        });

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent settings = new Intent(CreditCard.this, Settings.class);
                startActivity(settings);
                overridePendingTransition(0, 0);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!bp.handleActivityResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    protected void onDestroy() {
        if (bp != null) {
            bp.release();
        }
        super.onDestroy();
        realm.removeChangeListener(realmChangeListener);
        realm.close();
    }

    @Override
    public void onProductPurchased(@NonNull String productId, @Nullable TransactionDetails details) {
        if (Objects.equals(productId, "BEMAILC")) {
            if (key_1 == 1) {
                one_play_editor.putInt("keyemail", 1);
                one_play_editor.putBoolean("buyEmail", true);
                one_play_editor.apply();
            } else {
                Toast.makeText(this,"You have bought!",Toast.LENGTH_SHORT).show();
            }
        } else if (Objects.equals(productId, "BWEBANDAPPC")) {
            if (key_2 == 1) {
                one_play_editor.putInt("keyweb", 1);
                one_play_editor.putBoolean("buywebapp", true);
                one_play_editor.apply();
            } else {
                Toast.makeText(this,"You have bought!",Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(CreditCard.this, productId, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onPurchaseHistoryRestored() {

    }

    @Override
    public void onBillingError(int errorCode, @Nullable Throwable error) {

    }

    @Override
    public void onBillingInitialized() {

    }
}
