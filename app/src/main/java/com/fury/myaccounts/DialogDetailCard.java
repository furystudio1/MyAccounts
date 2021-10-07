package com.fury.myaccounts;

import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.fury.myaccounts.data.Spacecraft;
import com.melnykov.fab.FloatingActionButton;
import com.scottyab.aescrypt.AESCrypt;

import java.security.GeneralSecurityException;

import es.dmoral.toasty.Toasty;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class DialogDetailCard {

    private Dialog mDialog;
    private TextView nameDetail,numberDetail,dateDetail,cvvDetail,passwordDetail,emailDetail;
    private Context mDialogUniversalInfoActivity;
    FloatingActionButton mDialogOKButton,fabEdit,fabCopy,fabDelete;
    String name,number,date,cvv,email,pass,bank,back;
    Realm realm;
    int bac,bnk;

    public DialogDetailCard(Context var1,String...detail) {
        this.mDialogUniversalInfoActivity = var1;
        name = detail[0];
        number = detail[1];
        date = detail[2];
        cvv = detail[3];
        email = detail[4];
        pass = detail[5];
        bank = detail[6];
        back = detail[7];
        bac = Integer.parseInt(back);
        bnk = Integer.parseInt(bank);
    }

    private void initDialogButtons() {
        this.mDialogOKButton.setOnClickListener(new OnClickListener() {
            public void onClick(View var1) {
                DialogDetailCard.this.mDialog.dismiss();
            }
        });

        this.fabEdit.setOnClickListener(new OnClickListener() {
            public void onClick(View var1) {
                /////////////////////////edit////////////////////////////
                Intent n = new Intent(mDialogUniversalInfoActivity,CreateCard.class);
                n.putExtra("NumberCard_KEY",number);
                n.putExtra("NameCard_KEY",name);
                n.putExtra("ExpDate_KEY",date);
                n.putExtra("CVV_KEY",cvv);
                n.putExtra("Pass_KEY",pass);
                n.putExtra("Email_KEY",email);
                n.putExtra("Bank_KEY",bnk);
                n.putExtra("Back_KEY",bac);

                mDialogUniversalInfoActivity.startActivity(n);

                String m = null;
                try {
                    m = AESCrypt.encrypt("217218217218", number);
                } catch (GeneralSecurityException e) {
                }
                try {
                    final RealmResults<Spacecraft> spacecrafts = realm.where(Spacecraft.class).equalTo("NumberCard",m).findAll();
                    realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            spacecrafts.deleteFirstFromRealm();
                        }
                    });
                }catch (Exception e){
                }

                DialogDetailCard.this.mDialog.dismiss();
                CreditCard.act.finish();
            }
        });

        this.fabDelete.setOnClickListener(new OnClickListener() {
            public void onClick(View var1) {
                /////////////////////////delete////////////////////////////
                String n = null;
                try {
                    n = AESCrypt.encrypt("217218217218", number);
                } catch (GeneralSecurityException e) {
                }
                try {
                    final RealmResults<Spacecraft> spacecrafts = realm.where(Spacecraft.class).equalTo("NumberCard",n).findAll();
                    realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            spacecrafts.deleteFirstFromRealm();
                        }
                    });
                }catch (Exception e){
                    Toasty.error(mDialogUniversalInfoActivity, "No Deleted!", Toast.LENGTH_SHORT, true).show();
                }

                Toasty.success(mDialogUniversalInfoActivity, "Deleted", Toast.LENGTH_SHORT, true).show();
                DialogDetailCard.this.mDialog.dismiss();
            }
        });

        this.fabCopy.setOnClickListener(new OnClickListener() {
            public void onClick(View var1) {
                ClipboardManager clipboard = (ClipboardManager) mDialogUniversalInfoActivity.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("All", "Name : " + name + "\n" + "Card Number : " + number + "\n" + "Exp Date : " + date + "\n" + "CVV/CVC : " + cvv + "\n" + "Password : " + pass + "\n" + "Email : " + email);
                clipboard.setPrimaryClip(clip);
                Toasty.success(mDialogUniversalInfoActivity, "All Copied", Toast.LENGTH_SHORT, true).show();
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
        this.mDialog.setContentView(R.layout.details);
        this.mDialog.setCancelable(true);
        this.mDialog.show();
        mDialogOKButton = (FloatingActionButton) this.mDialog.findViewById(R.id.fabClose);
        fabEdit = (FloatingActionButton) this.mDialog.findViewById(R.id.fabEdit);
        fabCopy = (FloatingActionButton) this.mDialog.findViewById(R.id.fabCopy);
        fabDelete = (FloatingActionButton) this.mDialog.findViewById(R.id.fabDelete);

        AnimationDrawable frameAnimation;
        AnimationDrawable frameAnimation2;
        AnimationDrawable frameAnimation3;
        AnimationDrawable frameAnimation4;
        AnimationDrawable frameAnimation5;
        AnimationDrawable frameAnimation6;

        mDialogOKButton.show(true);
        fabEdit.show(true);
        fabCopy.show(true);
        fabDelete.show(true);

        RealmConfiguration config = new RealmConfiguration.Builder().name("myrealmcard.realm").build();
        realm = Realm.getInstance(config);

        nameDetail = (TextView) this.mDialog.findViewById(R.id.nameDetail);
        numberDetail = (TextView) this.mDialog.findViewById(R.id.numberDetail);
        dateDetail = (TextView) this.mDialog.findViewById(R.id.dateDetail);
        cvvDetail = (TextView) this.mDialog.findViewById(R.id.cvvDetail);
        passwordDetail = (TextView) this.mDialog.findViewById(R.id.passwordDetail);
        emailDetail = (TextView) this.mDialog.findViewById(R.id.emailDetail);

        frameAnimation = (AnimationDrawable) nameDetail.getBackground();
        frameAnimation2 = (AnimationDrawable) numberDetail.getBackground();
        frameAnimation3 = (AnimationDrawable) dateDetail.getBackground();
        frameAnimation4 = (AnimationDrawable) cvvDetail.getBackground();
        frameAnimation5 = (AnimationDrawable) passwordDetail.getBackground();
        frameAnimation6 = (AnimationDrawable) emailDetail.getBackground();

        frameAnimation.setEnterFadeDuration(4000);
        frameAnimation.setExitFadeDuration(4000);
        frameAnimation.start();
        frameAnimation2.setEnterFadeDuration(4000);
        frameAnimation2.setExitFadeDuration(4000);
        frameAnimation2.start();
        frameAnimation3.setEnterFadeDuration(4000);
        frameAnimation3.setExitFadeDuration(4000);
        frameAnimation3.start();
        frameAnimation4.setEnterFadeDuration(4000);
        frameAnimation4.setExitFadeDuration(4000);
        frameAnimation4.start();
        frameAnimation5.setEnterFadeDuration(4000);
        frameAnimation5.setExitFadeDuration(4000);
        frameAnimation5.start();
        frameAnimation6.setEnterFadeDuration(4000);
        frameAnimation6.setExitFadeDuration(4000);
        frameAnimation6.start();

        nameDetail.setText(name);
        numberDetail.setText(number);
        dateDetail.setText(date);
        cvvDetail.setText(cvv);
        passwordDetail.setText(pass);
        emailDetail.setText(email);

        initDialogButtons();
    }
}
