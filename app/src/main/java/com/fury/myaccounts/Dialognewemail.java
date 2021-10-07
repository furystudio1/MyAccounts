package com.fury.myaccounts;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.melnykov.fab.FloatingActionButton;
import com.scottyab.aescrypt.AESCrypt;

import java.security.GeneralSecurityException;
import java.util.Objects;

import es.dmoral.toasty.Toasty;

public class Dialognewemail {

    private Dialog mDialog;
    private TextView dialog_universal_info_title,dialog_universal_info_title2;
    EditText ET_oldpass,ET_newpass;
    private Context mDialogUniversalInfoActivity;
    FloatingActionButton fabSaveBank;
    String passwordCode, PasswordAll;
    SharedPreferences one_play_preferences;
    SharedPreferences.Editor one_play_editor;
    int i;

    public Dialognewemail(Context var1) {
        this.mDialogUniversalInfoActivity = var1;
    }

    private void initDialogButtons() {

        this.fabSaveBank.setOnClickListener(new OnClickListener() {
            public void onClick(View var1) {

                String m = String.valueOf(ET_oldpass.getText());
                if (Objects.equals(m, passwordCode)){
                    String pas = String.valueOf(ET_newpass.getText());
                    if (pas.length() > 0){
                            try {
                                pas = AESCrypt.encrypt(PasswordAll, pas);
                                one_play_editor.putString("EMAIL_forgot", pas);
                                one_play_editor.apply();

                                Dialognewemail.this.mDialog.dismiss();
                                Toasty.success(mDialogUniversalInfoActivity, "Email has changed", Toast.LENGTH_LONG, true).show();
                            }catch (GeneralSecurityException e){
                                Toasty.error(mDialogUniversalInfoActivity, (CharSequence) e, Toast.LENGTH_SHORT, true).show();
                            }
                    }else {
                        Toasty.error(mDialogUniversalInfoActivity, "Should a Email enter.", Toast.LENGTH_SHORT, true).show();
                    }
                }else {
                    Toasty.error(mDialogUniversalInfoActivity, "Old email is wrong!", Toast.LENGTH_SHORT, true).show();
                }

            }
        });
    }


    public void dismissDialog() {
        this.mDialog.dismiss();
    }

    public void showDialog() {
        if (this.mDialog == null) {
            this.mDialog = new Dialog(this.mDialogUniversalInfoActivity, R.style.CustomDialogTheme);
        }
        this.mDialog.setContentView(R.layout.dialog_new_email);
        this.mDialog.setCancelable(true);
        this.mDialog.show();

        one_play_preferences = mDialogUniversalInfoActivity.getApplicationContext().getSharedPreferences("PROJECT_NAME", Context.MODE_PRIVATE);
        one_play_editor = one_play_preferences.edit();
        passwordCode = one_play_preferences.getString("EMAIL_forgot", "");
        PasswordAll = one_play_preferences.getString("PFURY", "");
        i = one_play_preferences.getInt("colorday", 1);

        try {
            passwordCode = AESCrypt.decrypt(PasswordAll, passwordCode);
        }catch (GeneralSecurityException e){
        }

        dialog_universal_info_title = (TextView) this.mDialog.findViewById(R.id.dialog_universal_info_title);
        dialog_universal_info_title2 = (TextView) this.mDialog.findViewById(R.id.dialog_universal_info_title2);
        ET_oldpass = (EditText) this.mDialog.findViewById(R.id.ET_oldpass);
        ET_newpass = (EditText) this.mDialog.findViewById(R.id.ET_newpass);
        fabSaveBank = (FloatingActionButton) this.mDialog.findViewById(R.id.fabSaveBank);

        if (i == 1){
            dialog_universal_info_title.setTextColor(mDialogUniversalInfoActivity.getResources().getColor(R.color.colorstatus1));
            dialog_universal_info_title2.setTextColor(mDialogUniversalInfoActivity.getResources().getColor(R.color.colorstatus1));
            final int sdk = Build.VERSION.SDK_INT;
            if (sdk < Build.VERSION_CODES.JELLY_BEAN){
                ET_newpass.setBackgroundDrawable(mDialogUniversalInfoActivity.getResources().getDrawable(R.drawable.et_password_bg6));
            }else {
                ET_newpass.setBackground(mDialogUniversalInfoActivity.getResources().getDrawable(R.drawable.et_password_bg6));
            }
            fabSaveBank.setColorNormal(mDialogUniversalInfoActivity.getResources().getColor(R.color.colorstatus1));
            fabSaveBank.setColorPressed(mDialogUniversalInfoActivity.getResources().getColor(R.color.colorbtnPressed1));
            fabSaveBank.setColorRipple(mDialogUniversalInfoActivity.getResources().getColor(R.color.ripple1));
        }else if (i == 2){
            dialog_universal_info_title.setTextColor(mDialogUniversalInfoActivity.getResources().getColor(R.color.colorstatus2));
            dialog_universal_info_title2.setTextColor(mDialogUniversalInfoActivity.getResources().getColor(R.color.colorstatus2));
            final int sdk = Build.VERSION.SDK_INT;
            if (sdk < Build.VERSION_CODES.JELLY_BEAN){
                ET_newpass.setBackgroundDrawable(mDialogUniversalInfoActivity.getResources().getDrawable(R.drawable.et_password_bg5));
            }else {
                ET_newpass.setBackground(mDialogUniversalInfoActivity.getResources().getDrawable(R.drawable.et_password_bg5));
            }
            fabSaveBank.setColorNormal(mDialogUniversalInfoActivity.getResources().getColor(R.color.colorstatus2));
            fabSaveBank.setColorPressed(mDialogUniversalInfoActivity.getResources().getColor(R.color.colorbtnPressed2));
            fabSaveBank.setColorRipple(mDialogUniversalInfoActivity.getResources().getColor(R.color.ripple2));
        }

        initDialogButtons();
    }
}
