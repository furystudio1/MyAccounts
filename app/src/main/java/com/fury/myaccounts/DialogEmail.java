package com.fury.myaccounts;

import android.app.Dialog;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.fury.myaccounts.data.RealmHelper2;
import com.fury.myaccounts.data.Spacecraft2;
import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;
import in.goodiebag.carouselpicker.CarouselPicker;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by fury on 9/21/2017.
 */
public class DialogEmail {

    private Dialog mDialog;
    private CreateEmail mDialogUniversalInfoActivity;
    CarouselPicker carouselPicker;
    FloatingActionButton fab;
    int bank;
    String User,Password,Birthday,Mobile,siteEmail,Name2;
    int back,color;
    Realm realm;

    public DialogEmail(CreateEmail var1, int back , int color , String...item) {
        this.mDialogUniversalInfoActivity = var1;
        User = item[0];
        Password = item[1];
        Name2 = item[2];
        Birthday = item[3];
        Mobile = item[4];
        siteEmail = item[5];
        this.back = back;
        this.color = color;
    }

    private void initDialogButtons() {
        this.fab.setOnClickListener(new OnClickListener() {
            public void onClick(View var1) {

                Spacecraft2 spacecraft = new Spacecraft2();
                spacecraft.setBack(back);
                spacecraft.setUser(User);
                spacecraft.setPassword(Password);
                spacecraft.setName(Name2);
                spacecraft.setBirthday(Birthday);
                spacecraft.setMobile(Mobile);
                spacecraft.setSiteEmail(siteEmail);
                spacecraft.setEmail(bank);

                RealmHelper2 realmHelper = new RealmHelper2(realm);
                realmHelper.save(spacecraft);

                Toasty.success(mDialogUniversalInfoActivity, "Email Saved", Toast.LENGTH_SHORT, true).show();

                Intent Email = new Intent(mDialogUniversalInfoActivity, Email.class);
                mDialogUniversalInfoActivity.startActivity(Email);

                mDialogUniversalInfoActivity.finish();
                DialogEmail.this.mDialog.dismiss();
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
        this.mDialog.setContentView(R.layout.dialog_save_create_email);
        this.mDialog.setCancelable(true);
        this.mDialog.show();

        RealmConfiguration config2 = new RealmConfiguration.Builder().name("myrealmemail.realm").build();
        realm = Realm.getInstance(config2);

         carouselPicker = (CarouselPicker) this.mDialog.findViewById(R.id.carousel);

        fab = (FloatingActionButton) this.mDialog.findViewById(R.id.fabSaveBank);
        fab.show();

        if (color == 1){
            fab.setColorNormal(mDialogUniversalInfoActivity.getResources().getColor(R.color.colorstatus1));
            fab.setColorPressed(mDialogUniversalInfoActivity.getResources().getColor(R.color.colorbtnPressed1));
            fab.setColorRipple(mDialogUniversalInfoActivity.getResources().getColor(R.color.ripple1));
        }else {
            fab.setColorNormal(mDialogUniversalInfoActivity.getResources().getColor(R.color.colorstatus2));
            fab.setColorPressed(mDialogUniversalInfoActivity.getResources().getColor(R.color.colorbtnPressed2));
            fab.setColorRipple(mDialogUniversalInfoActivity.getResources().getColor(R.color.ripple2));
        }

        List<CarouselPicker.PickerItem> textItems = new ArrayList<>();
//20 here represents the textSize in dp, change it to the value you want.
        textItems.add(new CarouselPicker.TextItem("None", 20));//0
        textItems.add(new CarouselPicker.TextItem("Gmail", 20));//1
        textItems.add(new CarouselPicker.TextItem("Yahoo", 20));//2
        textItems.add(new CarouselPicker.TextItem("Hotmail", 15));//3
        textItems.add(new CarouselPicker.TextItem("Outlook", 15));//4
        textItems.add(new CarouselPicker.TextItem("icloud", 18));//5
        textItems.add(new CarouselPicker.TextItem("GMX", 20));//6
        textItems.add(new CarouselPicker.TextItem("ZOHO", 20));//7
        textItems.add(new CarouselPicker.TextItem("Yandex", 15));//8
        textItems.add(new CarouselPicker.TextItem("Nextcloud", 14));//9
        textItems.add(new CarouselPicker.TextItem("cPanel", 17));//10
        textItems.add(new CarouselPicker.TextItem("Aol.", 20));//11
        textItems.add(new CarouselPicker.TextItem("None", 20));//12
        CarouselPicker.CarouselViewAdapter textAdapter = new CarouselPicker.CarouselViewAdapter(mDialogUniversalInfoActivity, textItems, 0);
        carouselPicker.setAdapter(textAdapter);
        carouselPicker.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //position of the selected item
                bank = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        initDialogButtons();
    }

}
