package com.fury.myaccounts;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Dialognewfinger {

    private Dialog mDialog;
    private TextView dialog_universal_info_title,dialog_universal_info_title2;
    private Settings mDialogUniversalInfoActivity;
    Button bt_cancel,bt_buy;
    String PasswordAll;
    SharedPreferences one_play_preferences;
    SharedPreferences.Editor one_play_editor;
    int i;

    public Dialognewfinger(Settings var1) {
        this.mDialogUniversalInfoActivity = var1;
    }

    private void initDialogButtons() {

        this.bt_cancel.setOnClickListener(new OnClickListener() {
            public void onClick(View var1) {

                Dialognewfinger.this.mDialog.dismiss();

            }
        });
        this.bt_buy.setOnClickListener(new OnClickListener() {
            public void onClick(View var1) {
                /////////////////buy////////////////
                if (app_net.getInstance(mDialogUniversalInfoActivity).isOnline()) {

                    String SKU = "BPOALL";

                    try {
                        mDialogUniversalInfoActivity.bp.purchase(mDialogUniversalInfoActivity, SKU);
                    } catch (Exception e) {
                        Toast.makeText(mDialogUniversalInfoActivity, "Please wait and click again", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(mDialogUniversalInfoActivity, "Not Connected", Toast.LENGTH_LONG).show();
                }
                Dialognewfinger.this.mDialog.dismiss();

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
        this.mDialog.setContentView(R.layout.dialog_new_finger);
        this.mDialog.setCancelable(true);
        this.mDialog.show();

        one_play_preferences = mDialogUniversalInfoActivity.getApplicationContext().getSharedPreferences("PROJECT_NAME", Context.MODE_PRIVATE);
        one_play_editor = one_play_preferences.edit();
        PasswordAll = one_play_preferences.getString("PFURY", "");
        i = one_play_preferences.getInt("colorday", 1);

        dialog_universal_info_title = (TextView) this.mDialog.findViewById(R.id.dialog_universal_info_title);
        dialog_universal_info_title2 = (TextView) this.mDialog.findViewById(R.id.dialog_universal_info_title2);
        bt_buy = (Button) this.mDialog.findViewById(R.id.bt_buy);
        bt_cancel = (Button) this.mDialog.findViewById(R.id.bt_cancel);

        if (i == 1){
            dialog_universal_info_title.setTextColor(mDialogUniversalInfoActivity.getResources().getColor(R.color.colorstatus1));
            dialog_universal_info_title2.setTextColor(mDialogUniversalInfoActivity.getResources().getColor(R.color.colorstatus1));
            final int sdk = Build.VERSION.SDK_INT;
            if (sdk < Build.VERSION_CODES.JELLY_BEAN){
                bt_buy.setBackgroundDrawable(mDialogUniversalInfoActivity.getResources().getDrawable(R.drawable.et_password_bg6));
            }else {
                bt_buy.setBackground(mDialogUniversalInfoActivity.getResources().getDrawable(R.drawable.et_password_bg6));
            }
        }else if (i == 2){
            dialog_universal_info_title.setTextColor(mDialogUniversalInfoActivity.getResources().getColor(R.color.colorstatus2));
            dialog_universal_info_title2.setTextColor(mDialogUniversalInfoActivity.getResources().getColor(R.color.colorstatus2));
            final int sdk = Build.VERSION.SDK_INT;
            if (sdk < Build.VERSION_CODES.JELLY_BEAN){
                bt_buy.setBackgroundDrawable(mDialogUniversalInfoActivity.getResources().getDrawable(R.drawable.et_password_bg5));
            }else {
                bt_buy.setBackground(mDialogUniversalInfoActivity.getResources().getDrawable(R.drawable.et_password_bg5));
            }
        }

        initDialogButtons();
    }
}
