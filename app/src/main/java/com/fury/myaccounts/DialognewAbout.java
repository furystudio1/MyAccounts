package com.fury.myaccounts;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class DialognewAbout {

    private Dialog mDialog;
    private ImageView telegram,email,instagram;
    private Context mDialogUniversalInfoActivity;
    SharedPreferences one_play_preferences;
    SharedPreferences.Editor one_play_editor;
    int i;

    public DialognewAbout(Context var1) {
        this.mDialogUniversalInfoActivity = var1;
    }

    private void initDialogButtons() {

        this.telegram.setOnClickListener(new OnClickListener() {
            public void onClick(View var1) {
                Uri uri = Uri.parse("http://t.me/Fury_studio");
                Intent inestagram = new Intent(Intent.ACTION_VIEW, uri);
                inestagram.setPackage("org.telegram.messenger");
                try {
                    mDialogUniversalInfoActivity.startActivity(inestagram);
                } catch (ActivityNotFoundException e) {
                    mDialogUniversalInfoActivity.startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://t.me/Fury_studio")));
                }

            }
        });
        this.email.setOnClickListener(new OnClickListener() {
            public void onClick(View var1) {
                Intent email = new Intent(Intent.ACTION_SEND);
                email.setType("text/html");
                email.putExtra(Intent.EXTRA_EMAIL, "furystudio1@gmail.com");
                email.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                email.putExtra(Intent.EXTRA_TEXT, "I'm email body.");
                mDialogUniversalInfoActivity.startActivity(email);

            }
        });
        this.instagram.setOnClickListener(new OnClickListener() {
            public void onClick(View var1) {
                Uri uri = Uri.parse("http://instagram.com/_u/fury_studio_ir");
                Intent inestagram = new Intent(Intent.ACTION_VIEW, uri);
                inestagram.setPackage("com.instagram.android");
                try {
                    mDialogUniversalInfoActivity.startActivity(inestagram);
                } catch (ActivityNotFoundException e) {
                    mDialogUniversalInfoActivity.startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://instagram.com/fury_studio_ir")));
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
        this.mDialog.setContentView(R.layout.dialog_new_about);
        this.mDialog.setCancelable(true);
        this.mDialog.show();

        telegram = (ImageView) this.mDialog.findViewById(R.id.telegram);
        email = (ImageView) this.mDialog.findViewById(R.id.email);
        instagram = (ImageView) this.mDialog.findViewById(R.id.instagram);

        initDialogButtons();
    }
}
