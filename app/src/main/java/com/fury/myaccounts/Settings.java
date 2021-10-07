package com.fury.myaccounts;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.anjlab.android.iab.v3.BillingProcessor;
import com.anjlab.android.iab.v3.TransactionDetails;
import com.scottyab.aescrypt.AESCrypt;
import com.squareup.picasso.Picasso;

import java.security.GeneralSecurityException;
import java.util.Objects;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import es.dmoral.toasty.Toasty;

/**
 * Created by fury on 10/20/2017.
 */
public class Settings extends Activity implements BillingProcessor.IBillingHandler {

    SharedPreferences one_play_preferences;
    SharedPreferences.Editor one_play_editor;
    String PasswordAll, passwordCode, emailCode, email;
    int Color;
    int mediumGrey;
    Boolean CHECKED_STATE_FINGER, CHECKED_STATE_PASS1, CHECKED_STATE_PASS2, FingerBuy;

    ScheduledThreadPoolExecutor mDialogDaemon_time;
    BillingProcessor bp;
    int key_1, key_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        one_play_preferences = getApplicationContext().getSharedPreferences("PROJECT_NAME", android.content.Context.MODE_PRIVATE);
        one_play_editor = one_play_preferences.edit();
        PasswordAll = one_play_preferences.getString("PFURY", "");
        Color = one_play_preferences.getInt("colorday", 1);
        passwordCode = one_play_preferences.getString("passwordCode", "");
        emailCode = one_play_preferences.getString("emailCode", "");
        email = one_play_preferences.getString("EMAIL_forgot", "");
        CHECKED_STATE_FINGER = one_play_preferences.getBoolean("finger", false);
        CHECKED_STATE_PASS1 = one_play_preferences.getBoolean("password", false);
        CHECKED_STATE_PASS2 = one_play_preferences.getBoolean("email", false);
        FingerBuy = one_play_preferences.getBoolean("FingerBuy", false);
        key_1 = one_play_preferences.getInt("keyads", 0);
        key_2 = one_play_preferences.getInt("keyfinger", 0);

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        try {
            email = AESCrypt.decrypt(PasswordAll, email);
        } catch (GeneralSecurityException e) {
            finish();
        }

        bp = new BillingProcessor(this, "code google", this);

        RelativeLayout email = (RelativeLayout) findViewById(R.id.email);
        RelativeLayout suport = (RelativeLayout) findViewById(R.id.suport);
        RelativeLayout About = (RelativeLayout) findViewById(R.id.About);
        RelativeLayout removead = (RelativeLayout) findViewById(R.id.removead);

        ImageView backtop = (ImageView) findViewById(R.id.backtop);
        ImageView h4 = (ImageView) findViewById(R.id.h4);
        ImageView h1 = (ImageView) findViewById(R.id.h1);
        ImageView h2 = (ImageView) findViewById(R.id.h2);
        ImageView h3 = (ImageView) findViewById(R.id.h3);
        ImageView h5 = (ImageView) findViewById(R.id.h5);
        ImageView h6 = (ImageView) findViewById(R.id.h6);
        ImageView h7 = (ImageView) findViewById(R.id.h7);
        ImageView back = (ImageView) findViewById(R.id.back);

        TextView text_email = (TextView) findViewById(R.id.text_email);
        final TextView text_edit_email = (TextView) findViewById(R.id.text_edit_email);
        TextView text_pass = (TextView) findViewById(R.id.text_pass);
        TextView text_edit_pass = (TextView) findViewById(R.id.text_edit_pass);
        TextView text_pass2 = (TextView) findViewById(R.id.text_pass2);
        TextView text_edit_pass2 = (TextView) findViewById(R.id.text_edit_pass2);
        TextView text_pass3 = (TextView) findViewById(R.id.text_pass3);
        TextView text_edit_pass3 = (TextView) findViewById(R.id.text_edit_pass3);
        TextView text_suport = (TextView) findViewById(R.id.text_suport);
        TextView text_About = (TextView) findViewById(R.id.text_About);
        TextView text_removead = (TextView) findViewById(R.id.text_removead);

        text_edit_email.setText(this.email);

        final SwitchCompat switch_pass = (SwitchCompat) findViewById(R.id.switch_pass);
        final SwitchCompat switch_pass2 = (SwitchCompat) findViewById(R.id.switch_pass2);
        final SwitchCompat switch_pass3 = (SwitchCompat) findViewById(R.id.switch_pass3);

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

                            Settings.this.email = one_play_preferences.getString("EMAIL_forgot", "");
                            try {
                                Settings.this.email = AESCrypt.decrypt(PasswordAll, Settings.this.email);
                            } catch (GeneralSecurityException e) {
                            }
                            text_edit_email.setText(Settings.this.email);

                        }
                    });
                }
            }, 0L, 1000L, TimeUnit.MILLISECONDS);

        } catch (Exception e) {
            mDialogDaemon_time.shutdown();
            mDialogDaemon_time = null;
        }


        if (Color == 1) {
            Picasso.with(this).load(R.drawable.materials_bg).into(backtop);
            Picasso.with(this).load(R.drawable.materials_bg).into(h4);
            Picasso.with(this).load(R.drawable.materials_bg).into(h1);
            Picasso.with(this).load(R.drawable.materials_bg).into(h2);
            Picasso.with(this).load(R.drawable.materials_bg).into(h3);
            Picasso.with(this).load(R.drawable.materials_bg).into(h5);
            Picasso.with(this).load(R.drawable.materials_bg).into(h6);
            Picasso.with(this).load(R.drawable.materials_bg).into(h7);
            text_edit_email.setBackgroundResource(R.drawable.et_password_bg6);
            text_edit_pass.setBackgroundResource(R.drawable.et_password_bg6);
            text_edit_pass2.setBackgroundResource(R.drawable.et_password_bg6);
            text_edit_pass3.setBackgroundResource(R.drawable.et_password_bg6);
            text_email.setTextColor(getResources().getColor(R.color.colorstatus1));
            text_pass.setTextColor(getResources().getColor(R.color.colorstatus1));
            text_pass2.setTextColor(getResources().getColor(R.color.colorstatus1));
            text_pass3.setTextColor(getResources().getColor(R.color.colorstatus1));
            text_suport.setTextColor(getResources().getColor(R.color.colorstatus1));
            text_About.setTextColor(getResources().getColor(R.color.colorstatus1));
            text_removead.setTextColor(getResources().getColor(R.color.colorstatus1));

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.setStatusBarColor(ContextCompat.getColor(this,R.color.colorstatus1));
            }
            mediumGrey = ContextCompat.getColor(this, R.color.colorstatus1);
            switch_pass.setThumbTintList(new ColorStateList(
                    new int[][]{
                            new int[]{android.R.attr.state_checked},
                            new int[]{}
                    },
                    new int[]{
                            mediumGrey,
                            ContextCompat.getColor(this, R.color.colorstatus1)
                    }
            ));
            mediumGrey = ContextCompat.getColor(this, R.color.colorstatus1);
            switch_pass2.setThumbTintList(new ColorStateList(
                    new int[][]{
                            new int[]{android.R.attr.state_checked},
                            new int[]{}
                    },
                    new int[]{
                            mediumGrey,
                            ContextCompat.getColor(this, R.color.colorstatus1)
                    }
            ));
            mediumGrey = ContextCompat.getColor(this, R.color.colorstatus1);
            switch_pass3.setThumbTintList(new ColorStateList(
                    new int[][]{
                            new int[]{android.R.attr.state_checked},
                            new int[]{}
                    },
                    new int[]{
                            mediumGrey,
                            ContextCompat.getColor(this, R.color.colorstatus1)
                    }
            ));

            mediumGrey = ContextCompat.getColor(this, R.color.colorstatus1);
            switch_pass.setTrackTintList(new ColorStateList(
                    new int[][]{
                            new int[]{android.R.attr.state_checked},
                            new int[]{}
                    },
                    new int[]{
                            mediumGrey,
                            ContextCompat.getColor(this, R.color.gray)
                    }
            ));
            mediumGrey = ContextCompat.getColor(this, R.color.colorstatus1);
            switch_pass2.setTrackTintList(new ColorStateList(
                    new int[][]{
                            new int[]{android.R.attr.state_checked},
                            new int[]{}
                    },
                    new int[]{
                            mediumGrey,
                            ContextCompat.getColor(this, R.color.gray)
                    }
            ));
            mediumGrey = ContextCompat.getColor(this, R.color.colorstatus1);
            switch_pass3.setTrackTintList(new ColorStateList(
                    new int[][]{
                            new int[]{android.R.attr.state_checked},
                            new int[]{}
                    },
                    new int[]{
                            mediumGrey,
                            ContextCompat.getColor(this, R.color.gray)
                    }
            ));

        } else if (Color == 2) {
            Picasso.with(this).load(R.drawable.adspro).into(backtop);
            Picasso.with(this).load(R.drawable.adspro).into(h4);
            Picasso.with(this).load(R.drawable.adspro).into(h1);
            Picasso.with(this).load(R.drawable.adspro).into(h2);
            Picasso.with(this).load(R.drawable.adspro).into(h3);
            Picasso.with(this).load(R.drawable.adspro).into(h5);
            Picasso.with(this).load(R.drawable.adspro).into(h6);
            Picasso.with(this).load(R.drawable.adspro).into(h7);
            text_edit_email.setBackgroundResource(R.drawable.et_password_bg5);
            text_edit_pass.setBackgroundResource(R.drawable.et_password_bg5);
            text_edit_pass2.setBackgroundResource(R.drawable.et_password_bg5);
            text_edit_pass3.setBackgroundResource(R.drawable.et_password_bg5);
            text_email.setTextColor(getResources().getColor(R.color.colorstatus2));
            text_pass.setTextColor(getResources().getColor(R.color.colorstatus2));
            text_pass2.setTextColor(getResources().getColor(R.color.colorstatus2));
            text_pass3.setTextColor(getResources().getColor(R.color.colorstatus2));
            text_suport.setTextColor(getResources().getColor(R.color.colorstatus2));
            text_About.setTextColor(getResources().getColor(R.color.colorstatus2));
            text_removead.setTextColor(getResources().getColor(R.color.colorstatus2));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.setStatusBarColor(ContextCompat.getColor(this,R.color.colorstatus2));
            }
            mediumGrey = ContextCompat.getColor(this, R.color.colorstatus2);
            switch_pass.setThumbTintList(new ColorStateList(
                    new int[][]{
                            new int[]{android.R.attr.state_checked},
                            new int[]{}
                    },
                    new int[]{
                            mediumGrey,
                            ContextCompat.getColor(this, R.color.colorstatus2)
                    }
            ));

            mediumGrey = ContextCompat.getColor(this, R.color.colorstatus2);
            switch_pass2.setThumbTintList(new ColorStateList(
                    new int[][]{
                            new int[]{android.R.attr.state_checked},
                            new int[]{}
                    },
                    new int[]{
                            mediumGrey,
                            ContextCompat.getColor(this, R.color.colorstatus2)
                    }
            ));

            mediumGrey = ContextCompat.getColor(this, R.color.colorstatus2);
            switch_pass3.setThumbTintList(new ColorStateList(
                    new int[][]{
                            new int[]{android.R.attr.state_checked},
                            new int[]{}
                    },
                    new int[]{
                            mediumGrey,
                            ContextCompat.getColor(this, R.color.colorstatus2)
                    }
            ));

            mediumGrey = ContextCompat.getColor(this, R.color.colorstatus2);
            switch_pass.setTrackTintList(new ColorStateList(
                    new int[][]{
                            new int[]{android.R.attr.state_checked},
                            new int[]{}
                    },
                    new int[]{
                            mediumGrey,
                            ContextCompat.getColor(this, R.color.gray)
                    }
            ));
            mediumGrey = ContextCompat.getColor(this, R.color.colorstatus2);
            switch_pass2.setTrackTintList(new ColorStateList(
                    new int[][]{
                            new int[]{android.R.attr.state_checked},
                            new int[]{}
                    },
                    new int[]{
                            mediumGrey,
                            ContextCompat.getColor(this, R.color.gray)
                    }
            ));
            mediumGrey = ContextCompat.getColor(this, R.color.colorstatus2);
            switch_pass3.setTrackTintList(new ColorStateList(
                    new int[][]{
                            new int[]{android.R.attr.state_checked},
                            new int[]{}
                    },
                    new int[]{
                            mediumGrey,
                            ContextCompat.getColor(this, R.color.gray)
                    }
            ));

        }

        switch_pass.setChecked(CHECKED_STATE_PASS1);
        switch_pass2.setChecked(CHECKED_STATE_PASS2);
        switch_pass3.setChecked(CHECKED_STATE_FINGER);


        switch_pass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (switch_pass.isChecked()) {

                    if (passwordCode == null) {
                        try {
                            passwordCode = AESCrypt.decrypt(PasswordAll, passwordCode);
                        } catch (GeneralSecurityException e) {
                        }
                        if (passwordCode.length() == 6) {
                            switch_pass.setChecked(true);
                            one_play_editor.putBoolean("password", true);
                            one_play_editor.apply();
                        } else {
                            switch_pass.setChecked(false);
                            (new Dialognewpass1(Settings.this)).showDialog();
                        }
                    } else {
                        switch_pass.setChecked(false);
                        (new Dialognewpass1(Settings.this)).showDialog();
                    }

                } else {

                    if (!CHECKED_STATE_PASS2) {
                        switch_pass.setChecked(true);
                        Toasty.warning(Settings.this, "Second password must be active!", Toast.LENGTH_SHORT, true).show();
                    } else {
                        switch_pass.setChecked(false);
                        one_play_editor.putBoolean("password", false);
                        one_play_editor.apply();
                    }

                }
            }
        });


        switch_pass2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (switch_pass.isChecked()) {
                    if (emailCode == null) {
                        switch_pass2.setChecked(true);
                        one_play_editor.putBoolean("email", true);
                        one_play_editor.apply();
                    } else {
                        switch_pass2.setChecked(false);
                        (new Dialognewpass2(Settings.this)).showDialog();
                    }
                } else {
                    if (!CHECKED_STATE_PASS1) {
                        switch_pass2.setChecked(false);
                        Toasty.warning(Settings.this, "First password must be enabled!", Toast.LENGTH_SHORT, true).show();
                    } else {
                        switch_pass2.setChecked(false);
                        one_play_editor.putBoolean("email", false);
                        one_play_editor.apply();
                    }
                }
            }
        });


        switch_pass3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (switch_pass3.isChecked()) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        FingerprintManager fingerprintManager = (FingerprintManager) getSystemService(Context.FINGERPRINT_SERVICE);
                        if (ActivityCompat.checkSelfPermission(Settings.this, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
                            // TODO: Consider calling
                            //    ActivityCompat#requestPermissions
                            // here to request the missing permissions, and then overriding
                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                            //                                          int[] grantResults)
                            // to handle the case where the user grants the permission. See the documentation
                            // for ActivityCompat#requestPermissions for more details.
                            return;
                        }
                        if (!fingerprintManager.isHardwareDetected()) {
                            Toasty.warning(Settings.this, "Unfortunately, your device does not support fingerprint!", Toast.LENGTH_SHORT, true).show();
                        }else {
                            if (FingerBuy){
                                if (CHECKED_STATE_PASS1){
                                    switch_pass3.setChecked(true);
                                    one_play_editor.putBoolean("finger", true);
                                    one_play_editor.apply();
                                }else {
                                    switch_pass3.setChecked(false);
                                    Toasty.warning(Settings.this, "First password must be enabled!", Toast.LENGTH_SHORT, true).show();
                                }
                            }else {
                                switch_pass3.setChecked(false);
                                (new Dialognewfinger(Settings.this)).showDialog();
                            }
                        }
                    }
                }else {
                    switch_pass3.setChecked(false);
                    one_play_editor.putBoolean("finger", false);
                    one_play_editor.apply();
                }
            }
        });


        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                (new Dialognewemail(Settings.this)).showDialog();
            }
        });

        text_edit_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                (new Dialognewpass1(Settings.this)).showDialog();
            }
        });

        text_edit_pass2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                (new Dialognewpass2(Settings.this)).showDialog();
            }
        });

        text_edit_pass3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (FingerBuy){
                    if (CHECKED_STATE_PASS1){
                        switch_pass3.setChecked(true);
                        one_play_editor.putBoolean("finger", true);
                        one_play_editor.apply();
                    }else {
                        Toasty.warning(Settings.this, "First password must be enabled!", Toast.LENGTH_SHORT, true).show();
                    }
                }else {
                    (new Dialognewfinger(Settings.this)).showDialog();
                }
            }
        });

        suport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //////////////////////
            }
        });

        About.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                (new DialognewAbout(Settings.this)).showDialog();
            }
        });

        removead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (app_net.getInstance(Settings.this).isOnline()) {

                    String SKU = "BRADS";

                    try {
                        bp.purchase(Settings.this, SKU);
                    } catch (Exception e) {
                        Toast.makeText(Settings.this, "Please wait and click again", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(Settings.this, "Not Connected", Toast.LENGTH_LONG).show();
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
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
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onDestroy() {
        if (bp != null) {
            bp.release();
        }
        super.onDestroy();
    }

    @Override
    public void onProductPurchased(@NonNull String productId, @Nullable TransactionDetails details) {
        if (Objects.equals(productId, "BRADS")) {
            if (key_1 == 1) {
                one_play_editor.putInt("keyads", 1);
                one_play_editor.apply();
            } else {
                Toast.makeText(this,"You have bought!",Toast.LENGTH_SHORT).show();
            }
        } else if (Objects.equals(productId, "BFINGERP")) {
            if (key_2 == 1) {
                one_play_editor.putInt("keyfinger", 1);
                one_play_editor.apply();
            } else {
                Toast.makeText(this,"You have bought!",Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(Settings.this, productId, Toast.LENGTH_SHORT).show();
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
