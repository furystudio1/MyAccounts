package com.fury.myaccounts;

import android.app.Dialog;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.fury.myaccounts.data.RealmHelper;
import com.fury.myaccounts.data.Spacecraft;
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
public class DialogBank {

    private Dialog mDialog;
    private CreateCard mDialogUniversalInfoActivity;
    CarouselPicker carouselPicker;
    FloatingActionButton fab;
    int bank;
    String number,name,cvv,email,code,date;
    int back,color;
    Realm realm;

    public DialogBank(CreateCard var1,int back ,int color ,String...item) {
        this.mDialogUniversalInfoActivity = var1;
        number = item[0];
        name = item[1];
        cvv = item[2];
        email = item[3];
        code = item[4];
        date = item[5];
        this.back = back;
        this.color = color;
    }

    private void initDialogButtons() {
        this.fab.setOnClickListener(new OnClickListener() {
            public void onClick(View var1) {

                Spacecraft spacecraft = new Spacecraft();
                spacecraft.setBack(back);
                spacecraft.setCCV(cvv);
                spacecraft.setBank(bank);
                spacecraft.setDate(date);
                spacecraft.setEmail(email);
                spacecraft.setName(name);
                spacecraft.setNumberCard(number);
                spacecraft.setPass(code);

                RealmHelper realmHelper = new RealmHelper(realm);
                realmHelper.save(spacecraft);

                Toasty.success(mDialogUniversalInfoActivity, "Credit Card Saved", Toast.LENGTH_SHORT, true).show();

                Intent CreateCard = new Intent(mDialogUniversalInfoActivity, CreditCard.class);
                mDialogUniversalInfoActivity.startActivity(CreateCard);

                mDialogUniversalInfoActivity.finish();
                DialogBank.this.mDialog.dismiss();
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
        this.mDialog.setContentView(R.layout.dialog_save_create_card);
        this.mDialog.setCancelable(true);
        this.mDialog.show();

        RealmConfiguration config = new RealmConfiguration.Builder().name("myrealmcard.realm").build();
        realm = Realm.getInstance(config);

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
        textItems.add(new CarouselPicker.TextItem("Visa", 20));//1
        textItems.add(new CarouselPicker.TextItem("MasterCard", 10));//2
        textItems.add(new CarouselPicker.TextItem("American\nExpress", 10));//3
        textItems.add(new CarouselPicker.TextItem("Discover", 14));//4
        textItems.add(new CarouselPicker.TextItem("PayPal", 16));//5
        textItems.add(new CarouselPicker.TextItem("Maestro", 16));//6
        textItems.add(new CarouselPicker.TextItem("Solo", 20));//7
        textItems.add(new CarouselPicker.TextItem("Diners", 16));//8
        textItems.add(new CarouselPicker.TextItem("None", 20));//9
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
