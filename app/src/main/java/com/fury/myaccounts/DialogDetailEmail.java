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

import com.fury.myaccounts.data.Spacecraft2;
import com.melnykov.fab.FloatingActionButton;
import com.scottyab.aescrypt.AESCrypt;

import java.security.GeneralSecurityException;

import es.dmoral.toasty.Toasty;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class DialogDetailEmail {

    private Dialog mDialog;
    private TextView nameDetail,numberDetail,dateDetail,cvvDetail,passwordDetail,emailDetail;
    private Context mDialogUniversalInfoActivity;
    FloatingActionButton mDialogOKButton,fabEdit,fabCopy,fabDelete;
    String User, Birthday, name, Password , Mobile, siteEmail, bank, back;
    Realm realm;
    int bac,bnk;

    public DialogDetailEmail(Context var1, String...detail) {
        this.mDialogUniversalInfoActivity = var1;
        User = detail[0];
        Password = detail[1];
        Birthday = detail[2];
        name = detail[3];
        Mobile = detail[4];
        siteEmail = detail[5];
        bank = detail[6];
        back = detail[7];
        bac = Integer.parseInt(back);
        bnk = Integer.parseInt(bank);
    }

    private void initDialogButtons() {
        this.mDialogOKButton.setOnClickListener(new OnClickListener() {
            public void onClick(View var1) {
                DialogDetailEmail.this.mDialog.dismiss();
            }
        });

        this.fabEdit.setOnClickListener(new OnClickListener() {
            public void onClick(View var1) {
                /////////////////////////edit////////////////////////////
                Intent n = new Intent(mDialogUniversalInfoActivity,CreateEmail.class);
                n.putExtra("UserEmail_KEY",User);
                n.putExtra("PasswordEmail_KEY",Password);
                n.putExtra("Birthday_KEY",Birthday);
                n.putExtra("MobileEmail_KEY",Mobile);
                n.putExtra("SiteEmail_KEY",siteEmail);
                n.putExtra("NameEmail_KEY",name);
                n.putExtra("EmailLOGO_KEY",bnk);
                n.putExtra("Back_KEY",bac);

                mDialogUniversalInfoActivity.startActivity(n);

                String m = null;
                try {
                    m = AESCrypt.encrypt("217218217218", User);
                } catch (GeneralSecurityException e) {
                }
                try {
                    final RealmResults<Spacecraft2> spacecrafts = realm.where(Spacecraft2.class).equalTo("User",m).findAll();
                    realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            spacecrafts.deleteFirstFromRealm();
                        }
                    });
                }catch (Exception e){
                }

                DialogDetailEmail.this.mDialog.dismiss();
                Email.act.finish();
            }
        });

        this.fabDelete.setOnClickListener(new OnClickListener() {
            public void onClick(View var1) {
                /////////////////////////delete////////////////////////////
                String n = null;
                try {
                    n = AESCrypt.encrypt("217218217218", User);
                } catch (GeneralSecurityException e) {
                }
                try {
                    final RealmResults<Spacecraft2> spacecrafts = realm.where(Spacecraft2.class).equalTo("User",n).findAll();
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
                DialogDetailEmail.this.mDialog.dismiss();
            }
        });

        this.fabCopy.setOnClickListener(new OnClickListener() {
            public void onClick(View var1) {
                ClipboardManager clipboard = (ClipboardManager) mDialogUniversalInfoActivity.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("All", "Email : " + User + "\n" + "Password : " + Password + "\n" + "Site : " + siteEmail + "\n" + "Name : " + name + "\n" + "Birthday : " + Birthday + "\n" + "Mobile phone : " + Mobile );
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
        this.mDialog.setContentView(R.layout.details2);
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

        RealmConfiguration config2 = new RealmConfiguration.Builder().name("myrealmemail.realm").build();
        realm = Realm.getInstance(config2);

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

        nameDetail.setText(User);
        numberDetail.setText(Password);
        dateDetail.setText(siteEmail);
        cvvDetail.setText(name);
        passwordDetail.setText(Birthday);
        emailDetail.setText(Mobile);

        initDialogButtons();
    }
}
