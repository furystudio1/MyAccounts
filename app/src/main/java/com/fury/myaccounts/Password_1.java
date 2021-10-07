package com.fury.myaccounts;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.pro100svitlo.fingerprintAuthHelper.FahErrorType;
import com.pro100svitlo.fingerprintAuthHelper.FahListener;
import com.pro100svitlo.fingerprintAuthHelper.FingerprintAuthHelper;
import com.scottyab.aescrypt.AESCrypt;

import java.security.GeneralSecurityException;
import java.util.Calendar;
import java.util.Objects;

import es.dmoral.toasty.Toasty;

public class Password_1 extends AppCompatActivity implements FahListener {

    SharedPreferences one_play_preferences;
    SharedPreferences.Editor one_play_editor;
    boolean one, finger, email, password;
    String passwordCode, emailCode, PasswordAll;
    int i, day, fingerOn;
    private FingerprintAuthHelper mFAH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password_1);

        one_play_preferences = getApplicationContext().getSharedPreferences("PROJECT_NAME", android.content.Context.MODE_PRIVATE);
        one_play_editor = one_play_preferences.edit();
        one = one_play_preferences.getBoolean("one", true);
        finger = one_play_preferences.getBoolean("finger", false);
        email = one_play_preferences.getBoolean("email", false);
        password = one_play_preferences.getBoolean("password", true);
        passwordCode = one_play_preferences.getString("passwordCode", "");
        emailCode = one_play_preferences.getString("emailCode", "");
        PasswordAll = one_play_preferences.getString("PFURY", "");

        if (one) {
            Intent help = new Intent(this, VP.class);
            startActivity(help);
            finish();
        }

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        Typeface face = Typeface.createFromAsset(getAssets(), "Yantramanav-Thin.ttf");

        try {
            passwordCode = AESCrypt.decrypt(PasswordAll, passwordCode);
        } catch (GeneralSecurityException e) {
            finish();
        }
        try {
            emailCode = AESCrypt.decrypt(PasswordAll, emailCode);
        } catch (GeneralSecurityException e) {
        }

        mFAH = new FingerprintAuthHelper
                .Builder(this, this) //(Context inscance of Activity, FahListener)
                .build();

        ImageView back = (ImageView) findViewById(R.id.back);
        final ImageView fingericon = (ImageView) findViewById(R.id.fingericon);
        final TextView texthelp = (TextView) findViewById(R.id.texthelp);
        final TextView textbt = (TextView) findViewById(R.id.textbt);
        final TextView textforgot = (TextView) findViewById(R.id.textforgot);
        final TextClock textClock = (TextClock) findViewById(R.id.textClock);
        final Button enter = (Button) findViewById(R.id.enter);
        final EditText ET_password = (EditText) findViewById(R.id.ET_password);

        textClock.setTypeface(face);
        texthelp.setTypeface(face);
        textbt.setTypeface(face);
        textforgot.setTypeface(face);

        Calendar c = Calendar.getInstance();
        int Hr24 = c.get(Calendar.HOUR_OF_DAY);



        if (Hr24 >= 6 & Hr24 < 18) {
            one_play_editor.putInt("colorday", 1);
            one_play_editor.apply();
            back.setImageResource(R.drawable.back1);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorbtn4));
            }
            day = 1;
            if (finger) {
                texthelp.setVisibility(View.GONE);
                enter.setVisibility(View.GONE);
                ET_password.setVisibility(View.GONE);
                textforgot.setVisibility(View.GONE);
                textbt.setText("enter password");
                fingericon.setVisibility(View.VISIBLE);

            } else {
                fingerOn = 2;
                fingericon.setVisibility(View.GONE);
                enter.setBackgroundResource(R.drawable.button1);
                enter.setTextColor(getResources().getColorStateList(R.color.textcolor1));
                ET_password.setBackgroundResource(R.drawable.et_password_bg1);
                if (password) {

                    textforgot.setVisibility(View.GONE);
                    texthelp.setText("Enter your password");
                    textbt.setText("Forgot password?");
                } else if (email) {
                    i = 2;
                    texthelp.setText("Enter your Second password");
                    textbt.setText("Forgot password?");
                    textforgot.setVisibility(View.GONE);
                }
            }
        } else {
            one_play_editor.putInt("colorday", 2);
            one_play_editor.apply();
            back.setImageResource(R.drawable.back2);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorbtn3));
            }
            day = 2;
            if (finger) {
                fingerOn = 1;
                texthelp.setVisibility(View.GONE);
                textforgot.setVisibility(View.GONE);
                enter.setVisibility(View.GONE);
                ET_password.setVisibility(View.GONE);
                textbt.setText("enter password");
                fingericon.setVisibility(View.VISIBLE);
            } else {
                fingerOn = 2;
                fingericon.setVisibility(View.GONE);
                enter.setBackgroundResource(R.drawable.button2);
                enter.setTextColor(getResources().getColorStateList(R.color.textcolor2));
                ET_password.setBackgroundResource(R.drawable.et_password_bg2);
                if (password) {

                    textforgot.setVisibility(View.GONE);
                    texthelp.setText("Enter your password");
                    textbt.setText("Forgot password?");
                } else if (email) {
                    i = 2;
                    texthelp.setText("Enter your Second password");
                    textbt.setText("Forgot password?");
                    textforgot.setVisibility(View.GONE);
                }
            }
        }

        i = 1;


        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pas = String.valueOf(ET_password.getText());
                if (fingerOn == 3) {

                    /////////////////////////////

                } else if (i == 1) {
                    if (Objects.equals(passwordCode, pas)) {
                        i = 2;
                        if (email) {
                            ET_password.setText("");
                            ET_password.setInputType(InputType.TYPE_CLASS_TEXT);
                            texthelp.setText("Enter Second password");
                        } else {
                            Intent Credit = new Intent(Password_1.this, CreditCard.class);
                            startActivity(Credit);
                            finish();
                        }
                    } else {
                        Toasty.error(Password_1.this, "Wrong password!", Toast.LENGTH_SHORT, true).show();
                    }
                } else if (i == 2) {
                    if (Objects.equals(emailCode, pas)) {
                        Intent Credit = new Intent(Password_1.this, CreditCard.class);
                        startActivity(Credit);
                        finish();
                    } else {
                        Toasty.error(Password_1.this, "Wrong Second password!", Toast.LENGTH_SHORT, true).show();
                    }
                }
            }
        });


        textbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fingerOn == 1) {
                    fingerOn = 2;
                    textforgot.setVisibility(View.GONE);
                    texthelp.setText("Enter your password");
                    textbt.setText("Forgot password?");
                    fingericon.setVisibility(View.GONE);
                    ET_password.setVisibility(View.VISIBLE);
                    enter.setVisibility(View.VISIBLE);

                    if (day == 1) {
                        enter.setBackgroundResource(R.drawable.button1);
                        enter.setTextColor(getResources().getColorStateList(R.color.textcolor1));
                        ET_password.setBackgroundResource(R.drawable.et_password_bg1);
                    } else if (day == 2) {
                        enter.setBackgroundResource(R.drawable.button2);
                        enter.setTextColor(getResources().getColorStateList(R.color.textcolor2));
                        ET_password.setBackgroundResource(R.drawable.et_password_bg2);
                    }
                } else if (fingerOn == 2) {
                    textforgot.setVisibility(View.VISIBLE);
                    texthelp.setVisibility(View.GONE);
                    textbt.setVisibility(View.GONE);
                    ET_password.setVisibility(View.GONE);
                    enter.setText("Submit");
                    fingerOn = 3;
                }
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        mFAH.stopListening();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mFAH.startListening();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFAH.onDestroy();
    }


    @Override
    public void onFingerprintStatus(boolean authSuccessful, int errorType, CharSequence errorMess) {
        // authSuccessful - boolean that shows auth status
        // errorType - if auth was failed, you can catch error type
        // errorMess - if auth was failed, errorMess will tell you (and user) the reason

        if (authSuccessful){
            // do some stuff here in case auth was successful
            if (finger) {
                Intent Credit = new Intent(Password_1.this, CreditCard.class);
                startActivity(Credit);
                finish();
            }
        } else if (mFAH != null){
            // do some stuff here in case auth failed
            switch (errorType){
                case FahErrorType.General.LOCK_SCREEN_DISABLED:
                case FahErrorType.General.NO_FINGERPRINTS:
                    if (finger) {
                        Toasty.warning(Password_1.this, "There are no finger prints registered on this device. Please register your finger from settings.", Toast.LENGTH_LONG, true).show();
                        mFAH.showSecuritySettingsDialog();
                    }
                    break;
                case FahErrorType.Auth.AUTH_NOT_RECOGNIZED:
                    //do some stuff here
                    if (finger) {
                    Toasty.info(Password_1.this, "Cannot recognize your finger print. Please try again.", Toast.LENGTH_SHORT, true).show();
                }
                    break;
                case FahErrorType.Auth.AUTH_TO_MANY_TRIES:
                    //do some stuff here
                    break;
            }
        }
    }

    @Override
    public void onFingerprintListening(boolean listening, long milliseconds) {
        // listening - status of fingerprint listen process
        // milliseconds - timeout value, will be > 0, if listening = false & errorType = AUTH_TO_MANY_TRIES

        if (listening){
            //add some code here
        } else {
            //add some code here
        }
        if (milliseconds > 0) {
            //if u need, u can show timeout for user
            Toasty.error(Password_1.this, "Try again in " + milliseconds + " seconds", Toast.LENGTH_LONG, true).show();
        }
    }

}
