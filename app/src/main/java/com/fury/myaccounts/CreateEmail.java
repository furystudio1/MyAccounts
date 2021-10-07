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
import android.widget.TextView;
import android.widget.Toast;

import com.fury.myaccounts.data.RealmHelper2;
import com.fury.myaccounts.data.Spacecraft2;
import com.melnykov.fab.FloatingActionButton;
import com.scottyab.aescrypt.AESCrypt;

import java.security.GeneralSecurityException;

import es.dmoral.toasty.Toasty;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by fury on 9/20/2017.
 */
public class CreateEmail extends Activity {

    SharedPreferences one_play_preferences;
    SharedPreferences.Editor one_play_editor;
    String PasswordAll,User,Password,Birthday,Mobile,siteEmail,Name2;
    int Color,Backgrund,Email,back;

    int count = 0;
    int ti = 0;
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createemail);

        RealmConfiguration config2 = new RealmConfiguration.Builder().name("myrealmemail.realm").build();
        realm = Realm.getInstance(config2);

        one_play_preferences = getApplicationContext().getSharedPreferences("PROJECT_NAME", android.content.Context.MODE_PRIVATE);
        one_play_editor = one_play_preferences.edit();
        PasswordAll = one_play_preferences.getString("PFURY", "");
        Color = one_play_preferences.getInt("colorday", 1);
        Backgrund = one_play_preferences.getInt("Backgrund2", 1);

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        Intent i = this.getIntent();

        ImageView back_create_card = (ImageView)findViewById(R.id.back_create_card);
        ImageView close_create_card = (ImageView)findViewById(R.id.close_create_card);
        TextView text_number_card_create_card = (TextView) findViewById(R.id.text_number_card_create_card);
        TextView text_name_card_create_card = (TextView) findViewById(R.id.text_name_card_create_card);
        TextView text_date_card_create_card = (TextView) findViewById(R.id.text_date_card_create_card);
        TextView text_cvv_card_create_card = (TextView) findViewById(R.id.text_cvv_card_create_card);
        TextView text_email_card_create_card = (TextView) findViewById(R.id.text_email_card_create_card);
        TextView text_code_card_create_card = (TextView) findViewById(R.id.text_code_card_create_card);
        final EditText ET_number_card_create_card = (EditText) findViewById(R.id.ET_number_card_create_card);
        final EditText ET_name_card_create_card = (EditText) findViewById(R.id.ET_name_card_create_card);
        final EditText ET_date_card_create_card = (EditText) findViewById(R.id.ET_date_card_create_card);
        final EditText ET_cvv_card_create_card = (EditText) findViewById(R.id.ET_cvv_card_create_card);
        final EditText ET_email_card_create_card = (EditText) findViewById(R.id.ET_email_card_create_card);
        final EditText ET_code_card_create_card = (EditText) findViewById(R.id.ET_code_card_create_card);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabSaveCreateCard);
        fab.show();

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

        Bundle extras = i.getExtras();
        if(extras != null){
            User = extras.getString("UserEmail_KEY");
            Password = extras.getString("PasswordEmail_KEY");
            Birthday = extras.getString("Birthday_KEY");
            Mobile = extras.getString("MobileEmail_KEY");
            siteEmail = extras.getString("SiteEmail_KEY");
            Name2 = extras.getString("NameEmail_KEY");
            Email = extras.getInt("EmailLOGO_KEY");
            back = extras.getInt("Back_KEY");

            ti = 1;

            ET_number_card_create_card.setText(User);
            ET_name_card_create_card.setText(Password);
            ET_cvv_card_create_card.setText(Birthday);
            ET_date_card_create_card.setText(Name2);
            ET_email_card_create_card.setText(Mobile);
            ET_code_card_create_card.setText(siteEmail);
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


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userEmail = String.valueOf(ET_number_card_create_card.getText());
                String pass = String.valueOf(ET_name_card_create_card.getText());
                String name = String.valueOf(ET_date_card_create_card.getText());
                String bir = String.valueOf(ET_cvv_card_create_card.getText());
                String mobile = String.valueOf(ET_email_card_create_card.getText());
                String site = String.valueOf(ET_code_card_create_card.getText());

                if (userEmail.length() < 0 ){
                    Toasty.warning(CreateEmail.this, "Email can't be blank", Toast.LENGTH_SHORT, true).show();
                }else if (userEmail == null){
                    Toasty.warning(CreateEmail.this, "Email can't be blank", Toast.LENGTH_SHORT, true).show();
                }else {

                    Spacecraft2 user = realm.where(Spacecraft2.class).equalTo("User", userEmail).findFirst();

                    if (user != null) {
                        Toasty.warning(CreateEmail.this, "Username is already registered!", Toast.LENGTH_SHORT, true).show();
                    } else {


                        try {
                            userEmail = AESCrypt.encrypt(PasswordAll, userEmail);
                        } catch (GeneralSecurityException e) {
                            Toasty.error(CreateEmail.this, (CharSequence) e, Toast.LENGTH_SHORT, true).show();
                        }

                        if (pass.length() > 0 && pass != null) {
                            try {
                                pass = AESCrypt.encrypt(PasswordAll, pass);
                            } catch (GeneralSecurityException e) {
                                Toasty.error(CreateEmail.this, (CharSequence) e, Toast.LENGTH_SHORT, true).show();
                            }
                        }

                        if (name.length() > 0 && name != null) {
                            try {
                                name = AESCrypt.encrypt(PasswordAll, name);
                            } catch (GeneralSecurityException e) {
                                Toasty.error(CreateEmail.this, (CharSequence) e, Toast.LENGTH_SHORT, true).show();
                            }
                        }

                        if (bir.length() > 0 && bir != null) {
                            try {
                                bir = AESCrypt.encrypt(PasswordAll, bir);
                            } catch (GeneralSecurityException e) {
                                Toasty.error(CreateEmail.this, (CharSequence) e, Toast.LENGTH_SHORT, true).show();
                            }
                        }

                        if (mobile.length() > 0 && mobile != null) {
                            try {
                                mobile = AESCrypt.encrypt(PasswordAll, mobile);
                            } catch (GeneralSecurityException e) {
                                Toasty.error(CreateEmail.this, (CharSequence) e, Toast.LENGTH_SHORT, true).show();
                            }
                        }

                        if (site.length() > 0 && site != null) {
                            try {
                                site = AESCrypt.encrypt(PasswordAll, site);
                            } catch (GeneralSecurityException e) {
                                Toasty.error(CreateEmail.this, (CharSequence) e, Toast.LENGTH_SHORT, true).show();
                            }
                        }

                        int back = 0;

                        if (Backgrund == 1) {
                            Backgrund = 2;
                            one_play_editor.putInt("Backgrund2", Backgrund);
                            one_play_editor.apply();
                            back = 1;
                        } else if (Backgrund == 2) {
                            Backgrund = 3;
                            one_play_editor.putInt("Backgrund2", Backgrund);
                            one_play_editor.apply();
                            back = 2;
                        } else if (Backgrund == 3) {
                            Backgrund = 1;
                            one_play_editor.putInt("Backgrund2", Backgrund);
                            one_play_editor.apply();
                            back = 3;
                        }

                        if (ti == 1) {
                            back = CreateEmail.this.back;
                        }

                        (new DialogEmail(CreateEmail.this, back, Color, userEmail, pass, name, bir, mobile, site)).showDialog();
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
                        Toasty.error(CreateEmail.this, (CharSequence) e, Toast.LENGTH_SHORT, true).show();
                    }

                    if (Password.length() > 0 && Password != null) {
                        try {
                            Password = AESCrypt.encrypt(PasswordAll, Password);
                        } catch (GeneralSecurityException e) {
                            Toasty.error(CreateEmail.this, (CharSequence) e, Toast.LENGTH_SHORT, true).show();
                        }
                    }

                    if (Mobile.length() > 0 && Mobile != null) {
                        try {
                            Mobile = AESCrypt.encrypt(PasswordAll, Mobile);
                        } catch (GeneralSecurityException e) {
                            Toasty.error(CreateEmail.this, (CharSequence) e, Toast.LENGTH_SHORT, true).show();
                        }
                    }

                    if (Birthday.length() > 0 && Birthday != null) {
                        try {
                            Birthday = AESCrypt.encrypt(PasswordAll, Birthday);
                        } catch (GeneralSecurityException e) {
                            Toasty.error(CreateEmail.this, (CharSequence) e, Toast.LENGTH_SHORT, true).show();
                        }
                    }

                    if (siteEmail.length() > 0 && siteEmail != null) {
                        try {
                            siteEmail = AESCrypt.encrypt(PasswordAll, siteEmail);
                        } catch (GeneralSecurityException e) {
                            Toasty.error(CreateEmail.this, (CharSequence) e, Toast.LENGTH_SHORT, true).show();
                        }
                    }

                    if (Name2.length() > 0 && Name2 != null) {
                        try {
                            Name2 = AESCrypt.encrypt(PasswordAll, Name2);
                        } catch (GeneralSecurityException e) {
                            Toasty.error(CreateEmail.this, (CharSequence) e, Toast.LENGTH_SHORT, true).show();
                        }
                    }

                    Spacecraft2 spacecraft = new Spacecraft2();
                    spacecraft.setBack(back);
                    spacecraft.setUser(User);
                    spacecraft.setBirthday(Birthday);
                    spacecraft.setMobile(Mobile);
                    spacecraft.setSiteEmail(siteEmail);
                    spacecraft.setPassword(Password);
                    spacecraft.setName(Name2);
                    spacecraft.setEmail(Email);

                    RealmHelper2 realmHelper = new RealmHelper2(realm);
                    realmHelper.save(spacecraft);

                    Intent CreateCard = new Intent(CreateEmail.this, Email.class);
                    startActivity(CreateCard);

                    finish();

                }else {

                    Intent CreateCard = new Intent(CreateEmail.this, Email.class);
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
                Toasty.error(CreateEmail.this, (CharSequence) e, Toast.LENGTH_SHORT, true).show();
            }

            if (Password.length() > 0 && Password != null) {
                try {
                    Password = AESCrypt.encrypt(PasswordAll, Password);
                } catch (GeneralSecurityException e) {
                    Toasty.error(CreateEmail.this, (CharSequence) e, Toast.LENGTH_SHORT, true).show();
                }
            }

            if (Mobile.length() > 0 && Mobile != null) {
                try {
                    Mobile = AESCrypt.encrypt(PasswordAll, Mobile);
                } catch (GeneralSecurityException e) {
                    Toasty.error(CreateEmail.this, (CharSequence) e, Toast.LENGTH_SHORT, true).show();
                }
            }

            if (Birthday.length() > 0 && Birthday != null) {
                try {
                    Birthday = AESCrypt.encrypt(PasswordAll, Birthday);
                } catch (GeneralSecurityException e) {
                    Toasty.error(CreateEmail.this, (CharSequence) e, Toast.LENGTH_SHORT, true).show();
                }
            }

            if (siteEmail.length() > 0 && siteEmail != null) {
                try {
                    siteEmail = AESCrypt.encrypt(PasswordAll, siteEmail);
                } catch (GeneralSecurityException e) {
                    Toasty.error(CreateEmail.this, (CharSequence) e, Toast.LENGTH_SHORT, true).show();
                }
            }

            if (Name2.length() > 0 && Name2 != null) {
                try {
                    Name2 = AESCrypt.encrypt(PasswordAll, Name2);
                } catch (GeneralSecurityException e) {
                    Toasty.error(CreateEmail.this, (CharSequence) e, Toast.LENGTH_SHORT, true).show();
                }
            }

            Spacecraft2 spacecraft = new Spacecraft2();
            spacecraft.setBack(back);
            spacecraft.setUser(User);
            spacecraft.setBirthday(Birthday);
            spacecraft.setMobile(Mobile);
            spacecraft.setSiteEmail(siteEmail);
            spacecraft.setPassword(Password);
            spacecraft.setSiteEmail(Name2);
            spacecraft.setEmail(Email);

            RealmHelper2 realmHelper = new RealmHelper2(realm);
            realmHelper.save(spacecraft);

            Intent CreateCard = new Intent(CreateEmail.this, Email.class);
            startActivity(CreateCard);

            finish();

        }else {

            Intent CreateCard = new Intent(CreateEmail.this, Email.class);
            startActivity(CreateCard);

            finish();
        }

    }






}
