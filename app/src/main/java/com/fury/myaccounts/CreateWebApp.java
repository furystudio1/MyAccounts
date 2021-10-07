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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.fury.myaccounts.data.RealmHelper3;
import com.fury.myaccounts.data.Spacecraft3;
import com.melnykov.fab.FloatingActionButton;
import com.scottyab.aescrypt.AESCrypt;

import java.security.GeneralSecurityException;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import es.dmoral.toasty.Toasty;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by fury on 9/20/2017.
 */
public class CreateWebApp extends Activity {

    SharedPreferences one_play_preferences;
    SharedPreferences.Editor one_play_editor;
    String PasswordAll,User,Password,Mobile,Name2,Email,Detail;
    int Color,Backgrund,back,noe;

    int count = 2;
    int ti = 0;
    Realm realm;
    int noo;

    FloatingActionButton fab;
    ImageView back_create_card,close_create_card;
    TextView text_number_card_create_card,text_name_card_create_card,text_date_card_create_card,text_cvv_card_create_card,text_email_card_create_card,text_code_card_create_card;
    EditText ET_number_card_create_card,ET_name_card_create_card,ET_date_card_create_card,ET_cvv_card_create_card,ET_email_card_create_card,ET_code_card_create_card;

    ScheduledThreadPoolExecutor mDialogDaemon_time, mDialogDaemon_coin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createwebapp);

        RealmConfiguration config2 = new RealmConfiguration.Builder().name("myrealmwebapp.realm").build();
        realm = Realm.getInstance(config2);

        one_play_preferences = getApplicationContext().getSharedPreferences("PROJECT_NAME", android.content.Context.MODE_PRIVATE);
        one_play_editor = one_play_preferences.edit();
        PasswordAll = one_play_preferences.getString("PFURY", "");
        Color = one_play_preferences.getInt("colorday", 1);
        Backgrund = one_play_preferences.getInt("Backgrund3", 1);

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        Intent i = this.getIntent();

         back_create_card = (ImageView)findViewById(R.id.back_create_card);
         close_create_card = (ImageView)findViewById(R.id.close_create_card);
        text_number_card_create_card = (TextView) findViewById(R.id.text_number_card_create_card);
        text_name_card_create_card = (TextView) findViewById(R.id.text_name_card_create_card);
        text_date_card_create_card = (TextView) findViewById(R.id.text_date_card_create_card);
        text_cvv_card_create_card = (TextView) findViewById(R.id.text_cvv_card_create_card);
        text_email_card_create_card = (TextView) findViewById(R.id.text_email_card_create_card);
        text_code_card_create_card = (TextView) findViewById(R.id.text_code_card_create_card);
        ET_number_card_create_card = (EditText) findViewById(R.id.ET_number_card_create_card);      //user
        ET_name_card_create_card = (EditText) findViewById(R.id.ET_name_card_create_card);          //pass
        ET_date_card_create_card = (EditText) findViewById(R.id.ET_date_card_create_card);          // app or web
        ET_cvv_card_create_card = (EditText) findViewById(R.id.ET_cvv_card_create_card);            //email
        ET_email_card_create_card = (EditText) findViewById(R.id.ET_email_card_create_card);        //mobilenumber
        ET_code_card_create_card = (EditText) findViewById(R.id.ET_code_card_create_card);          //detail
        final ProgressBar loading_spinner = (ProgressBar)findViewById(R.id.loading_spinner);
        fab = (FloatingActionButton) findViewById(R.id.fabSaveCreateCard);
        fab.show();

        Bundle extras = i.getExtras();
        if(extras != null){
            User = extras.getString("UserWebApp_KEY");
            Password = extras.getString("Password_KEY");
            Email = extras.getString("Email_KEY");
            Mobile = extras.getString("Mobile_KEY");
            Detail = extras.getString("Detail_KEY");
            Name2 = extras.getString("Name_KEY");
            noe = extras.getInt("NOE_KEY");
            back = extras.getInt("Back_KEY");

            ti = 1;

            ET_number_card_create_card.setText(User);
            ET_name_card_create_card.setText(Password);
            ET_cvv_card_create_card.setText(Email);
            ET_date_card_create_card.setText(Name2);
            ET_email_card_create_card.setText(Mobile);
            ET_code_card_create_card.setText(Detail);
        }else {


            (new DialogAppOrWeb(CreateWebApp.this, Color)).showDialog();

            text_number_card_create_card.setVisibility(View.INVISIBLE);
            close_create_card.setVisibility(View.INVISIBLE);
            text_name_card_create_card.setVisibility(View.INVISIBLE);
            text_date_card_create_card.setVisibility(View.INVISIBLE);
            text_cvv_card_create_card.setVisibility(View.INVISIBLE);
            text_email_card_create_card.setVisibility(View.INVISIBLE);
            text_code_card_create_card.setVisibility(View.INVISIBLE);
            ET_number_card_create_card.setVisibility(View.INVISIBLE);
            ET_name_card_create_card.setVisibility(View.INVISIBLE);
            ET_date_card_create_card.setVisibility(View.INVISIBLE);
            ET_cvv_card_create_card.setVisibility(View.INVISIBLE);
            ET_email_card_create_card.setVisibility(View.INVISIBLE);
            ET_code_card_create_card.setVisibility(View.INVISIBLE);

            if (mDialogDaemon_time != null) {
                try {
                    mDialogDaemon_time.shutdown();
                    mDialogDaemon_time = null;
                } catch (Exception e) {
                }
            }
            try {
                mDialogDaemon_time = new ScheduledThreadPoolExecutor(1);
            } catch (Exception e) {
            }
            try {
                mDialogDaemon_time.scheduleAtFixedRate(new Runnable() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                if (count == 0) {
                                    loading_spinner.setVisibility(View.INVISIBLE);
                                    text_number_card_create_card.setVisibility(View.VISIBLE);
                                    close_create_card.setVisibility(View.VISIBLE);
                                    text_name_card_create_card.setVisibility(View.VISIBLE);
                                    text_date_card_create_card.setVisibility(View.VISIBLE);
                                    text_cvv_card_create_card.setVisibility(View.VISIBLE);
                                    text_email_card_create_card.setVisibility(View.VISIBLE);
                                    text_code_card_create_card.setVisibility(View.VISIBLE);
                                    ET_number_card_create_card.setVisibility(View.VISIBLE);
                                    ET_name_card_create_card.setVisibility(View.VISIBLE);
                                    ET_date_card_create_card.setVisibility(View.VISIBLE);
                                    ET_cvv_card_create_card.setVisibility(View.VISIBLE);
                                    ET_email_card_create_card.setVisibility(View.VISIBLE);
                                    ET_code_card_create_card.setVisibility(View.VISIBLE);
                                    text_date_card_create_card.setText(" App Name");
                                    noo = 1;
                                    App();
                                    mDialogDaemon_time.shutdown();
                                    mDialogDaemon_time = null;
                                } else if (count == 1) {
                                    loading_spinner.setVisibility(View.INVISIBLE);
                                    text_number_card_create_card.setVisibility(View.VISIBLE);
                                    close_create_card.setVisibility(View.VISIBLE);
                                    text_name_card_create_card.setVisibility(View.VISIBLE);
                                    text_date_card_create_card.setVisibility(View.VISIBLE);
                                    text_cvv_card_create_card.setVisibility(View.VISIBLE);
                                    text_email_card_create_card.setVisibility(View.VISIBLE);
                                    text_code_card_create_card.setVisibility(View.VISIBLE);
                                    ET_number_card_create_card.setVisibility(View.VISIBLE);
                                    ET_name_card_create_card.setVisibility(View.VISIBLE);
                                    ET_date_card_create_card.setVisibility(View.VISIBLE);
                                    ET_cvv_card_create_card.setVisibility(View.VISIBLE);
                                    ET_email_card_create_card.setVisibility(View.VISIBLE);
                                    ET_code_card_create_card.setVisibility(View.VISIBLE);
                                    text_date_card_create_card.setText(" Site Name");
                                    noo = 2;
                                    Web();
                                    mDialogDaemon_time.shutdown();
                                    mDialogDaemon_time = null;
                                }

                            }
                        });
                    }
                }, 0L, 2000L, TimeUnit.MILLISECONDS);

            } catch (Exception e) {
                mDialogDaemon_time.shutdown();
                mDialogDaemon_time = null;
            }
        }



        if (Color == 1){
            ET_number_card_create_card.setTextColor(getResources().getColor(R.color.colorText1));
            ET_name_card_create_card.setTextColor(getResources().getColor(R.color.colorText1));
            ET_date_card_create_card.setTextColor(getResources().getColor(R.color.colorText1));
            ET_cvv_card_create_card.setTextColor(getResources().getColor(R.color.colorText1));
            ET_email_card_create_card.setTextColor(getResources().getColor(R.color.colorText1));
            ET_code_card_create_card.setTextColor(getResources().getColor(R.color.colorText1));
            back_create_card.setImageResource(R.drawable.backcreatecard);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.setStatusBarColor(ContextCompat.getColor(this,R.color.colorstatus1));
            }
        }else if (Color == 2){
            ET_number_card_create_card.setTextColor(getResources().getColor(R.color.colorText2));
            ET_name_card_create_card.setTextColor(getResources().getColor(R.color.colorText2));
            ET_date_card_create_card.setTextColor(getResources().getColor(R.color.colorText2));
            ET_cvv_card_create_card.setTextColor(getResources().getColor(R.color.colorText2));
            ET_email_card_create_card.setTextColor(getResources().getColor(R.color.colorText2));
            ET_code_card_create_card.setTextColor(getResources().getColor(R.color.colorText2));
            back_create_card.setImageResource(R.drawable.backcreatecard2);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.setStatusBarColor(ContextCompat.getColor(this,R.color.colorstatus2));
            }
        }


    }


    public void App(){
        Typeface face = Typeface.createFromAsset(getAssets(), "Yantramanav-Light.ttf");

        text_number_card_create_card.setTypeface(face);
        text_name_card_create_card.setTypeface(face);
        text_date_card_create_card.setTypeface(face);
        text_cvv_card_create_card.setTypeface(face);
        text_email_card_create_card.setTypeface(face);
        text_code_card_create_card.setTypeface(face);
        ET_number_card_create_card.setTypeface(face);
        ET_name_card_create_card.setTypeface(face);
        ET_date_card_create_card.setTypeface(face);
        ET_cvv_card_create_card.setTypeface(face);
        ET_email_card_create_card.setTypeface(face);
        ET_code_card_create_card.setTypeface(face);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userEmail = String.valueOf(ET_number_card_create_card.getText());
                String pass = String.valueOf(ET_name_card_create_card.getText());
                String name = String.valueOf(ET_date_card_create_card.getText());
                String email = String.valueOf(ET_cvv_card_create_card.getText());
                String mobile = String.valueOf(ET_email_card_create_card.getText());
                String detail = String.valueOf(ET_code_card_create_card.getText());

                if (userEmail.length() < 0 ){
                    Toasty.warning(CreateWebApp.this, "Username can't be blank", Toast.LENGTH_SHORT, true).show();
                }else if (userEmail == null){
                    Toasty.warning(CreateWebApp.this, "Username can't be blank", Toast.LENGTH_SHORT, true).show();
                }else {

                    Spacecraft3 user = realm.where(Spacecraft3.class).equalTo("User", userEmail).findFirst();

                    if (user != null) {
                        Toasty.warning(CreateWebApp.this, "Username is already registered!", Toast.LENGTH_SHORT, true).show();
                    } else {


                        try {
                            userEmail = AESCrypt.encrypt(PasswordAll, userEmail);
                        } catch (GeneralSecurityException e) {
                            Toasty.error(CreateWebApp.this, (CharSequence) e, Toast.LENGTH_SHORT, true).show();
                        }

                        if (pass.length() > 0 && pass != null) {
                            try {
                                pass = AESCrypt.encrypt(PasswordAll, pass);
                            } catch (GeneralSecurityException e) {
                                Toasty.error(CreateWebApp.this, (CharSequence) e, Toast.LENGTH_SHORT, true).show();
                            }
                        }

                        if (name.length() > 0 && name != null) {
                            try {
                                name = AESCrypt.encrypt(PasswordAll, name);
                            } catch (GeneralSecurityException e) {
                                Toasty.error(CreateWebApp.this, (CharSequence) e, Toast.LENGTH_SHORT, true).show();
                            }
                        }

                        if (email.length() > 0 && email != null) {
                            try {
                                email = AESCrypt.encrypt(PasswordAll, email);
                            } catch (GeneralSecurityException e) {
                                Toasty.error(CreateWebApp.this, (CharSequence) e, Toast.LENGTH_SHORT, true).show();
                            }
                        }

                        if (mobile.length() > 0 && mobile != null) {
                            try {
                                mobile = AESCrypt.encrypt(PasswordAll, mobile);
                            } catch (GeneralSecurityException e) {
                                Toasty.error(CreateWebApp.this, (CharSequence) e, Toast.LENGTH_SHORT, true).show();
                            }
                        }

                        if (detail.length() > 0 && detail != null) {
                            try {
                                detail = AESCrypt.encrypt(PasswordAll, detail);
                            } catch (GeneralSecurityException e) {
                                Toasty.error(CreateWebApp.this, (CharSequence) e, Toast.LENGTH_SHORT, true).show();
                            }
                        }

                        int back = 0;

                        if (Backgrund == 1){
                            Backgrund = 2;
                            one_play_editor.putInt("Backgrund3",Backgrund);
                            one_play_editor.apply();
                            back = 1;
                        }else if (Backgrund == 2){
                            Backgrund = 3;
                            one_play_editor.putInt("Backgrund3",Backgrund);
                            one_play_editor.apply();
                            back = 2;
                        }else if (Backgrund == 3){
                            Backgrund = 4;
                            one_play_editor.putInt("Backgrund3",Backgrund);
                            one_play_editor.apply();
                            back = 3;
                        }else if (Backgrund == 4){
                            Backgrund = 5;
                            one_play_editor.putInt("Backgrund3",Backgrund);
                            one_play_editor.apply();
                            back = 4;
                        }else if (Backgrund == 5){
                            Backgrund = 6;
                            one_play_editor.putInt("Backgrund3",Backgrund);
                            one_play_editor.apply();
                            back = 5;
                        }else if (Backgrund == 6){
                            Backgrund = 7;
                            one_play_editor.putInt("Backgrund3",Backgrund);
                            one_play_editor.apply();
                            back = 6;
                        }else if (Backgrund == 7){
                            Backgrund = 1;
                            one_play_editor.putInt("Backgrund3",Backgrund);
                            one_play_editor.apply();
                            back = 7;
                        }

                        if (ti == 1) {
                            back = CreateWebApp.this.back;
                        }

                        Spacecraft3 spacecraft = new Spacecraft3();
                        spacecraft.setName(name);
                        spacecraft.setUser(userEmail);
                        spacecraft.setPassword(pass);
                        spacecraft.setEmail(email);
                        spacecraft.setMobile(mobile);
                        spacecraft.setDaiitle(detail);
                        spacecraft.setNOE(count);
                        spacecraft.setBack(back);

                        RealmHelper3 realmHelper = new RealmHelper3(realm);
                        realmHelper.save(spacecraft);

                        Toasty.success(CreateWebApp.this, "App Info Saved", Toast.LENGTH_SHORT, true).show();

                        Intent CreateCard = new Intent(CreateWebApp.this, WebApp.class);
                        startActivity(CreateCard);

                    }
                }
            }
        });


        close_create_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ti == 1){
                    try {
                        User = AESCrypt.encrypt(PasswordAll, User);
                    } catch (GeneralSecurityException e) {
                        Toasty.error(CreateWebApp.this, (CharSequence) e, Toast.LENGTH_SHORT, true).show();
                    }

                    if (Password.length() > 0 && Password != null) {
                        try {
                            Password = AESCrypt.encrypt(PasswordAll, Password);
                        } catch (GeneralSecurityException e) {
                            Toasty.error(CreateWebApp.this, (CharSequence) e, Toast.LENGTH_SHORT, true).show();
                        }
                    }

                    if (Mobile.length() > 0 && Mobile != null) {
                        try {
                            Mobile = AESCrypt.encrypt(PasswordAll, Mobile);
                        } catch (GeneralSecurityException e) {
                            Toasty.error(CreateWebApp.this, (CharSequence) e, Toast.LENGTH_SHORT, true).show();
                        }
                    }

                    if (Email.length() > 0 && Email != null) {
                        try {
                            Email = AESCrypt.encrypt(PasswordAll, Email);
                        } catch (GeneralSecurityException e) {
                            Toasty.error(CreateWebApp.this, (CharSequence) e, Toast.LENGTH_SHORT, true).show();
                        }
                    }

                    if (Detail.length() > 0 && Detail != null) {
                        try {
                            Detail = AESCrypt.encrypt(PasswordAll, Detail);
                        } catch (GeneralSecurityException e) {
                            Toasty.error(CreateWebApp.this, (CharSequence) e, Toast.LENGTH_SHORT, true).show();
                        }
                    }

                    if (Name2.length() > 0 && Name2 != null) {
                        try {
                            Name2 = AESCrypt.encrypt(PasswordAll, Name2);
                        } catch (GeneralSecurityException e) {
                            Toasty.error(CreateWebApp.this, (CharSequence) e, Toast.LENGTH_SHORT, true).show();
                        }
                    }

                    Spacecraft3 spacecraft = new Spacecraft3();
                    spacecraft.setName(Name2);
                    spacecraft.setUser(User);
                    spacecraft.setPassword(Password);
                    spacecraft.setEmail(Email);
                    spacecraft.setMobile(Mobile);
                    spacecraft.setDaiitle(Detail);
                    spacecraft.setNOE(noe);
                    spacecraft.setBack(back);

                    RealmHelper3 realmHelper = new RealmHelper3(realm);
                    realmHelper.save(spacecraft);

                    Intent CreateCard = new Intent(CreateWebApp.this, WebApp.class);
                    startActivity(CreateCard);

                    finish();

                }else {

                    Intent CreateCard = new Intent(CreateWebApp.this, WebApp.class);
                    startActivity(CreateCard);

                    finish();
                }
            }
        });

    }

    public void Web(){
        Typeface face = Typeface.createFromAsset(getAssets(), "Yantramanav-Light.ttf");

        text_number_card_create_card.setTypeface(face);
        text_name_card_create_card.setTypeface(face);
        text_date_card_create_card.setTypeface(face);
        text_cvv_card_create_card.setTypeface(face);
        text_email_card_create_card.setTypeface(face);
        text_code_card_create_card.setTypeface(face);
        ET_number_card_create_card.setTypeface(face);
        ET_name_card_create_card.setTypeface(face);
        ET_date_card_create_card.setTypeface(face);
        ET_cvv_card_create_card.setTypeface(face);
        ET_email_card_create_card.setTypeface(face);
        ET_code_card_create_card.setTypeface(face);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userEmail = String.valueOf(ET_number_card_create_card.getText());
                String pass = String.valueOf(ET_name_card_create_card.getText());
                String name = String.valueOf(ET_date_card_create_card.getText());
                String email = String.valueOf(ET_cvv_card_create_card.getText());
                String mobile = String.valueOf(ET_email_card_create_card.getText());
                String detail = String.valueOf(ET_code_card_create_card.getText());

                if (userEmail.length() < 0 ){
                    Toasty.warning(CreateWebApp.this, "Username can't be blank", Toast.LENGTH_SHORT, true).show();
                }else if (userEmail == null){
                    Toasty.warning(CreateWebApp.this, "Username can't be blank", Toast.LENGTH_SHORT, true).show();
                }else {

                    Spacecraft3 user = realm.where(Spacecraft3.class).equalTo("User", userEmail).findFirst();

                    if (user != null) {
                        Toasty.warning(CreateWebApp.this, "Username is already registered!", Toast.LENGTH_SHORT, true).show();
                    } else {


                        try {
                            userEmail = AESCrypt.encrypt(PasswordAll, userEmail);
                        } catch (GeneralSecurityException e) {
                            Toasty.error(CreateWebApp.this, (CharSequence) e, Toast.LENGTH_SHORT, true).show();
                        }

                        if (pass.length() > 0 && pass != null) {
                            try {
                                pass = AESCrypt.encrypt(PasswordAll, pass);
                            } catch (GeneralSecurityException e) {
                                Toasty.error(CreateWebApp.this, (CharSequence) e, Toast.LENGTH_SHORT, true).show();
                            }
                        }

                        if (name.length() > 0 && name != null) {
                            try {
                                name = AESCrypt.encrypt(PasswordAll, name);
                            } catch (GeneralSecurityException e) {
                                Toasty.error(CreateWebApp.this, (CharSequence) e, Toast.LENGTH_SHORT, true).show();
                            }
                        }

                        if (email.length() > 0 && email != null) {
                            try {
                                email = AESCrypt.encrypt(PasswordAll, email);
                            } catch (GeneralSecurityException e) {
                                Toasty.error(CreateWebApp.this, (CharSequence) e, Toast.LENGTH_SHORT, true).show();
                            }
                        }

                        if (mobile.length() > 0 && mobile != null) {
                            try {
                                mobile = AESCrypt.encrypt(PasswordAll, mobile);
                            } catch (GeneralSecurityException e) {
                                Toasty.error(CreateWebApp.this, (CharSequence) e, Toast.LENGTH_SHORT, true).show();
                            }
                        }

                        if (detail.length() > 0 && detail != null) {
                            try {
                                detail = AESCrypt.encrypt(PasswordAll, detail);
                            } catch (GeneralSecurityException e) {
                                Toasty.error(CreateWebApp.this, (CharSequence) e, Toast.LENGTH_SHORT, true).show();
                            }
                        }

                        int back = 0;

                        if (Backgrund == 1){
                            Backgrund = 2;
                            one_play_editor.putInt("Backgrund3",Backgrund);
                            one_play_editor.apply();
                            back = 1;
                        }else if (Backgrund == 2){
                            Backgrund = 3;
                            one_play_editor.putInt("Backgrund3",Backgrund);
                            one_play_editor.apply();
                            back = 2;
                        }else if (Backgrund == 3){
                            Backgrund = 4;
                            one_play_editor.putInt("Backgrund3",Backgrund);
                            one_play_editor.apply();
                            back = 3;
                        }else if (Backgrund == 4){
                            Backgrund = 5;
                            one_play_editor.putInt("Backgrund3",Backgrund);
                            one_play_editor.apply();
                            back = 4;
                        }else if (Backgrund == 5){
                            Backgrund = 6;
                            one_play_editor.putInt("Backgrund3",Backgrund);
                            one_play_editor.apply();
                            back = 5;
                        }else if (Backgrund == 6){
                            Backgrund = 7;
                            one_play_editor.putInt("Backgrund3",Backgrund);
                            one_play_editor.apply();
                            back = 6;
                        }else if (Backgrund == 7){
                            Backgrund = 1;
                            one_play_editor.putInt("Backgrund3",Backgrund);
                            one_play_editor.apply();
                            back = 7;
                        }

                        if (ti == 1) {
                            back = CreateWebApp.this.back;
                        }
                        if (ti == 1) {
                            back = CreateWebApp.this.back;
                        }

                        Spacecraft3 spacecraft = new Spacecraft3();
                        spacecraft.setName(name);
                        spacecraft.setUser(userEmail);
                        spacecraft.setPassword(pass);
                        spacecraft.setEmail(email);
                        spacecraft.setMobile(mobile);
                        spacecraft.setDaiitle(detail);
                        spacecraft.setNOE(count);
                        spacecraft.setBack(back);

                        RealmHelper3 realmHelper = new RealmHelper3(realm);
                        realmHelper.save(spacecraft);

                        Toasty.success(CreateWebApp.this, "Site Info Saved", Toast.LENGTH_SHORT, true).show();

                        Intent CreateCard = new Intent(CreateWebApp.this, WebApp.class);
                        startActivity(CreateCard);
                    }
                }
            }
        });


        close_create_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ti == 1){
                    try {
                        User = AESCrypt.encrypt(PasswordAll, User);
                    } catch (GeneralSecurityException e) {
                        Toasty.error(CreateWebApp.this, (CharSequence) e, Toast.LENGTH_SHORT, true).show();
                    }

                    if (Password.length() > 0 && Password != null) {
                        try {
                            Password = AESCrypt.encrypt(PasswordAll, Password);
                        } catch (GeneralSecurityException e) {
                            Toasty.error(CreateWebApp.this, (CharSequence) e, Toast.LENGTH_SHORT, true).show();
                        }
                    }

                    if (Mobile.length() > 0 && Mobile != null) {
                        try {
                            Mobile = AESCrypt.encrypt(PasswordAll, Mobile);
                        } catch (GeneralSecurityException e) {
                            Toasty.error(CreateWebApp.this, (CharSequence) e, Toast.LENGTH_SHORT, true).show();
                        }
                    }

                    if (Email.length() > 0 && Email != null) {
                        try {
                            Email = AESCrypt.encrypt(PasswordAll, Email);
                        } catch (GeneralSecurityException e) {
                            Toasty.error(CreateWebApp.this, (CharSequence) e, Toast.LENGTH_SHORT, true).show();
                        }
                    }

                    if (Detail.length() > 0 && Detail != null) {
                        try {
                            Detail = AESCrypt.encrypt(PasswordAll, Detail);
                        } catch (GeneralSecurityException e) {
                            Toasty.error(CreateWebApp.this, (CharSequence) e, Toast.LENGTH_SHORT, true).show();
                        }
                    }

                    if (Name2.length() > 0 && Name2 != null) {
                        try {
                            Name2 = AESCrypt.encrypt(PasswordAll, Name2);
                        } catch (GeneralSecurityException e) {
                            Toasty.error(CreateWebApp.this, (CharSequence) e, Toast.LENGTH_SHORT, true).show();
                        }
                    }

                    Spacecraft3 spacecraft = new Spacecraft3();
                    spacecraft.setName(Name2);
                    spacecraft.setUser(User);
                    spacecraft.setPassword(Password);
                    spacecraft.setEmail(Email);
                    spacecraft.setMobile(Mobile);
                    spacecraft.setDaiitle(Detail);
                    spacecraft.setNOE(noe);
                    spacecraft.setBack(back);

                    RealmHelper3 realmHelper = new RealmHelper3(realm);
                    realmHelper.save(spacecraft);

                    Intent CreateCard = new Intent(CreateWebApp.this, WebApp.class);
                    startActivity(CreateCard);

                    finish();

                }else {

                    Intent CreateCard = new Intent(CreateWebApp.this, WebApp.class);
                    startActivity(CreateCard);

                    finish();
                }
            }
        });

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if (ti == 1){
            try {
                User = AESCrypt.encrypt(PasswordAll, User);
            } catch (GeneralSecurityException e) {
                Toasty.error(CreateWebApp.this, (CharSequence) e, Toast.LENGTH_SHORT, true).show();
            }

            if (Password.length() > 0 && Password != null) {
                try {
                    Password = AESCrypt.encrypt(PasswordAll, Password);
                } catch (GeneralSecurityException e) {
                    Toasty.error(CreateWebApp.this, (CharSequence) e, Toast.LENGTH_SHORT, true).show();
                }
            }

            if (Mobile.length() > 0 && Mobile != null) {
                try {
                    Mobile = AESCrypt.encrypt(PasswordAll, Mobile);
                } catch (GeneralSecurityException e) {
                    Toasty.error(CreateWebApp.this, (CharSequence) e, Toast.LENGTH_SHORT, true).show();
                }
            }

            if (Email.length() > 0 && Email != null) {
                try {
                    Email = AESCrypt.encrypt(PasswordAll, Email);
                } catch (GeneralSecurityException e) {
                    Toasty.error(CreateWebApp.this, (CharSequence) e, Toast.LENGTH_SHORT, true).show();
                }
            }

            if (Detail.length() > 0 && Detail != null) {
                try {
                    Detail = AESCrypt.encrypt(PasswordAll, Detail);
                } catch (GeneralSecurityException e) {
                    Toasty.error(CreateWebApp.this, (CharSequence) e, Toast.LENGTH_SHORT, true).show();
                }
            }

            if (Name2.length() > 0 && Name2 != null) {
                try {
                    Name2 = AESCrypt.encrypt(PasswordAll, Name2);
                } catch (GeneralSecurityException e) {
                    Toasty.error(CreateWebApp.this, (CharSequence) e, Toast.LENGTH_SHORT, true).show();
                }
            }

            Spacecraft3 spacecraft = new Spacecraft3();
            spacecraft.setName(Name2);
            spacecraft.setUser(User);
            spacecraft.setPassword(Password);
            spacecraft.setEmail(Email);
            spacecraft.setMobile(Mobile);
            spacecraft.setDaiitle(Detail);
            spacecraft.setNOE(noe);
            spacecraft.setBack(back);

            RealmHelper3 realmHelper = new RealmHelper3(realm);
            realmHelper.save(spacecraft);

            Intent CreateCard = new Intent(CreateWebApp.this, WebApp.class);
            startActivity(CreateCard);

            finish();

        }else {

            Intent CreateCard = new Intent(CreateWebApp.this, WebApp.class);
            startActivity(CreateCard);

            finish();
        }

    }






}
