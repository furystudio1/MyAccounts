package com.fury.myaccounts;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fury.myaccounts.data.RealmHelper;
import com.fury.myaccounts.data.Spacecraft;
import com.melnykov.fab.FloatingActionButton;
import com.scottyab.aescrypt.AESCrypt;

import java.security.GeneralSecurityException;

import es.dmoral.toasty.Toasty;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by fury on 9/20/2017.
 */
public class CreateCard extends Activity {

    SharedPreferences one_play_preferences;
    SharedPreferences.Editor one_play_editor;
    String PasswordAll,number,name,cvv,date,email,pass;
    int Color,Backgrund,bank,back;

    int count = 0;
    int ti = 0;
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createcard);

        RealmConfiguration config = new RealmConfiguration.Builder().name("myrealmcard.realm").build();
        realm = Realm.getInstance(config);

        one_play_preferences = getApplicationContext().getSharedPreferences("PROJECT_NAME", android.content.Context.MODE_PRIVATE);
        one_play_editor = one_play_preferences.edit();
        PasswordAll = one_play_preferences.getString("PFURY", "");
        Color = one_play_preferences.getInt("colorday", 1);
        Backgrund = one_play_preferences.getInt("Backgrund", 1);

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
            number = extras.getString("NumberCard_KEY");
            name = extras.getString("NameCard_KEY");
            cvv = extras.getString("ExpDate_KEY");
            date = extras.getString("CVV_KEY");
            email = extras.getString("Pass_KEY");
            pass = extras.getString("Email_KEY");
            bank = extras.getInt("Bank_KEY");
            back = extras.getInt("Back_KEY");

            ti = 1;

            ET_number_card_create_card.setText(number);
            ET_name_card_create_card.setText(name);
            ET_cvv_card_create_card.setText(cvv);
            ET_date_card_create_card.setText(date);
            ET_email_card_create_card.setText(email);
            ET_code_card_create_card.setText(pass);
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
            final int sdk = Build.VERSION.SDK_INT;
            if (sdk < Build.VERSION_CODES.JELLY_BEAN){
                close_create_card.setBackgroundDrawable(getResources().getDrawable(R.drawable.bt_password_bg1));
            }else {
                close_create_card.setBackground(getResources().getDrawable(R.drawable.bt_password_bg1));
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
            final int sdk = Build.VERSION.SDK_INT;
            if (sdk < Build.VERSION_CODES.JELLY_BEAN){
                close_create_card.setBackgroundDrawable(getResources().getDrawable(R.drawable.bt_password_bg2));
            }else {
                close_create_card.setBackground(getResources().getDrawable(R.drawable.bt_password_bg2));
            }
        }


        ET_number_card_create_card.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (count <= ET_number_card_create_card.getText().toString().length()
                        &&(ET_number_card_create_card.getText().toString().length()==4
                        ||ET_number_card_create_card.getText().toString().length()==9
                        ||ET_number_card_create_card.getText().toString().length()==14)){
                    ET_number_card_create_card.setText(ET_number_card_create_card.getText().toString()+" ");
                    int pos = ET_number_card_create_card.getText().length();
                    ET_number_card_create_card.setSelection(pos);
                }else if (count >= ET_number_card_create_card.getText().toString().length()
                        &&(ET_number_card_create_card.getText().toString().length()==4
                        ||ET_number_card_create_card.getText().toString().length()==9
                        ||ET_number_card_create_card.getText().toString().length()==14)){
                    ET_number_card_create_card.setText(ET_number_card_create_card.getText().toString().substring(0,ET_number_card_create_card.getText().toString().length()-1));
                    int pos = ET_number_card_create_card.getText().length();
                    ET_number_card_create_card.setSelection(pos);
                }
                count = ET_number_card_create_card.getText().toString().length();
            }
        });


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String numbercard = String.valueOf(ET_number_card_create_card.getText());
                String namecard = String.valueOf(ET_name_card_create_card.getText());
                String datecard = String.valueOf(ET_date_card_create_card.getText());
                String cvvcard = String.valueOf(ET_cvv_card_create_card.getText());
                String emailcard = String.valueOf(ET_email_card_create_card.getText());
                String codecard = String.valueOf(ET_code_card_create_card.getText());

                if (numbercard.length() < 0 ){
                    Toasty.warning(CreateCard.this, "Card Number can't be blank", Toast.LENGTH_SHORT, true).show();
                }else if (numbercard.length() != 19){
                    Toasty.warning(CreateCard.this, "Card Number can't be blank", Toast.LENGTH_SHORT, true).show();
                }else if (numbercard == null){
                    Toasty.warning(CreateCard.this, "Card Number can't be blank", Toast.LENGTH_SHORT, true).show();
                }else {

                    Spacecraft user = realm.where(Spacecraft.class).equalTo("NumberCard", numbercard).findFirst();

                    if (user != null) {
                        Toasty.warning(CreateCard.this, "Card number is already registered!", Toast.LENGTH_SHORT, true).show();
                    } else {

                        try {
                            numbercard = AESCrypt.encrypt(PasswordAll, numbercard);
                        } catch (GeneralSecurityException e) {
                            Toasty.error(CreateCard.this, (CharSequence) e, Toast.LENGTH_SHORT, true).show();
                        }

                        if (namecard.length() > 0 && namecard != null) {
                            try {
                                namecard = AESCrypt.encrypt(PasswordAll, namecard);
                            } catch (GeneralSecurityException e) {
                                Toasty.error(CreateCard.this, (CharSequence) e, Toast.LENGTH_SHORT, true).show();
                            }
                        }

                        if (datecard.length() > 0 && datecard != null) {
                            try {
                                datecard = AESCrypt.encrypt(PasswordAll, datecard);
                            } catch (GeneralSecurityException e) {
                                Toasty.error(CreateCard.this, (CharSequence) e, Toast.LENGTH_SHORT, true).show();
                            }
                        }

                        if (cvvcard.length() > 0 && cvvcard != null) {
                            try {
                                cvvcard = AESCrypt.encrypt(PasswordAll, cvvcard);
                            } catch (GeneralSecurityException e) {
                                Toasty.error(CreateCard.this, (CharSequence) e, Toast.LENGTH_SHORT, true).show();
                            }
                        }

                        if (emailcard.length() > 0 && emailcard != null) {
                            try {
                                emailcard = AESCrypt.encrypt(PasswordAll, emailcard);
                            } catch (GeneralSecurityException e) {
                                Toasty.error(CreateCard.this, (CharSequence) e, Toast.LENGTH_SHORT, true).show();
                            }
                        }

                        if (codecard.length() > 0 && codecard != null) {
                            try {
                                codecard = AESCrypt.encrypt(PasswordAll, codecard);
                            } catch (GeneralSecurityException e) {
                                Toasty.error(CreateCard.this, (CharSequence) e, Toast.LENGTH_SHORT, true).show();
                            }
                        }

                        int back = 0;

                        if (Backgrund == 1) {
                            Backgrund = 2;
                            one_play_editor.putInt("Backgrund", Backgrund);
                            one_play_editor.apply();
                            back = 1;
                        } else if (Backgrund == 2) {
                            Backgrund = 3;
                            one_play_editor.putInt("Backgrund", Backgrund);
                            one_play_editor.apply();
                            back = 2;
                        } else if (Backgrund == 3) {
                            Backgrund = 4;
                            one_play_editor.putInt("Backgrund", Backgrund);
                            one_play_editor.apply();
                            back = 3;
                        } else if (Backgrund == 4) {
                            Backgrund = 5;
                            one_play_editor.putInt("Backgrund", Backgrund);
                            one_play_editor.apply();
                            back = 4;
                        } else if (Backgrund == 5) {
                            Backgrund = 6;
                            one_play_editor.putInt("Backgrund", Backgrund);
                            one_play_editor.apply();
                            back = 5;
                        } else if (Backgrund == 6) {
                            Backgrund = 1;
                            one_play_editor.putInt("Backgrund", Backgrund);
                            one_play_editor.apply();
                            back = 6;
                        }

                        if (ti == 1) {
                            back = CreateCard.this.back;
                        }

                        (new DialogBank(CreateCard.this, back, Color, numbercard, namecard, cvvcard, emailcard, codecard, datecard)).showDialog();

                    }
                }
            }
        });


        close_create_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ti == 1){
                    try {
                        number = AESCrypt.encrypt(PasswordAll, number);
                    } catch (GeneralSecurityException e) {
                        Toasty.error(CreateCard.this, (CharSequence) e, Toast.LENGTH_SHORT, true).show();
                    }

                    if (name.length() > 0 && name != null) {
                        try {
                            name = AESCrypt.encrypt(PasswordAll, name);
                        } catch (GeneralSecurityException e) {
                            Toasty.error(CreateCard.this, (CharSequence) e, Toast.LENGTH_SHORT, true).show();
                        }
                    }

                    if (date.length() > 0 && date != null) {
                        try {
                            date = AESCrypt.encrypt(PasswordAll, date);
                        } catch (GeneralSecurityException e) {
                            Toasty.error(CreateCard.this, (CharSequence) e, Toast.LENGTH_SHORT, true).show();
                        }
                    }

                    if (cvv.length() > 0 && cvv != null) {
                        try {
                            cvv = AESCrypt.encrypt(PasswordAll, cvv);
                        } catch (GeneralSecurityException e) {
                            Toasty.error(CreateCard.this, (CharSequence) e, Toast.LENGTH_SHORT, true).show();
                        }
                    }

                    if (email.length() > 0 && email != null) {
                        try {
                            email = AESCrypt.encrypt(PasswordAll, email);
                        } catch (GeneralSecurityException e) {
                            Toasty.error(CreateCard.this, (CharSequence) e, Toast.LENGTH_SHORT, true).show();
                        }
                    }

                    if (pass.length() > 0 && pass != null) {
                        try {
                            pass = AESCrypt.encrypt(PasswordAll, pass);
                        } catch (GeneralSecurityException e) {
                            Toasty.error(CreateCard.this, (CharSequence) e, Toast.LENGTH_SHORT, true).show();
                        }
                    }

                    Spacecraft spacecraft = new Spacecraft();
                    spacecraft.setBack(back);
                    spacecraft.setCCV(cvv);
                    spacecraft.setBank(bank);
                    spacecraft.setDate(date);
                    spacecraft.setEmail(email);
                    spacecraft.setName(name);
                    spacecraft.setNumberCard(number);
                    spacecraft.setPass(pass);

                    RealmHelper realmHelper = new RealmHelper(realm);
                    realmHelper.save(spacecraft);

                    Intent CreateCard = new Intent(CreateCard.this, CreditCard.class);
                    startActivity(CreateCard);

                    finish();

                }else {

                    Intent CreateCard = new Intent(CreateCard.this, CreditCard.class);
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
                number = AESCrypt.encrypt(PasswordAll, number);
            } catch (GeneralSecurityException e) {
                Toasty.error(CreateCard.this, (CharSequence) e, Toast.LENGTH_SHORT, true).show();
            }

            if (name.length() > 0 && name != null) {
                try {
                    name = AESCrypt.encrypt(PasswordAll, name);
                } catch (GeneralSecurityException e) {
                    Toasty.error(CreateCard.this, (CharSequence) e, Toast.LENGTH_SHORT, true).show();
                }
            }

            if (date.length() > 0 && date != null) {
                try {
                    date = AESCrypt.encrypt(PasswordAll, date);
                } catch (GeneralSecurityException e) {
                    Toasty.error(CreateCard.this, (CharSequence) e, Toast.LENGTH_SHORT, true).show();
                }
            }

            if (cvv.length() > 0 && cvv != null) {
                try {
                    cvv = AESCrypt.encrypt(PasswordAll, cvv);
                } catch (GeneralSecurityException e) {
                    Toasty.error(CreateCard.this, (CharSequence) e, Toast.LENGTH_SHORT, true).show();
                }
            }

            if (email.length() > 0 && email != null) {
                try {
                    email = AESCrypt.encrypt(PasswordAll, email);
                } catch (GeneralSecurityException e) {
                    Toasty.error(CreateCard.this, (CharSequence) e, Toast.LENGTH_SHORT, true).show();
                }
            }

            if (pass.length() > 0 && pass != null) {
                try {
                    pass = AESCrypt.encrypt(PasswordAll, pass);
                } catch (GeneralSecurityException e) {
                    Toasty.error(CreateCard.this, (CharSequence) e, Toast.LENGTH_SHORT, true).show();
                }
            }

            Spacecraft spacecraft = new Spacecraft();
            spacecraft.setBack(back);
            spacecraft.setCCV(cvv);
            spacecraft.setBank(bank);
            spacecraft.setDate(date);
            spacecraft.setEmail(email);
            spacecraft.setName(name);
            spacecraft.setNumberCard(number);
            spacecraft.setPass(pass);

            RealmHelper realmHelper = new RealmHelper(realm);
            realmHelper.save(spacecraft);

            Intent CreateCard = new Intent(CreateCard.this, CreditCard.class);
            startActivity(CreateCard);

            finish();

        }else {

            Intent CreateCard = new Intent(CreateCard.this, CreditCard.class);
            startActivity(CreateCard);

            finish();
        }

    }
}
