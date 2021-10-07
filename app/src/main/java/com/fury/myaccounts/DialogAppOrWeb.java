package com.fury.myaccounts;

import android.app.Dialog;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;

import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import in.goodiebag.carouselpicker.CarouselPicker;

/**
 * Created by fury on 9/21/2017.
 */
public class DialogAppOrWeb {

    private Dialog mDialog;
    private CreateWebApp mDialogUniversalInfoActivity;
    CarouselPicker carouselPicker;
    FloatingActionButton fab;
    int bank;
    int color;

    public DialogAppOrWeb(CreateWebApp var1, int color) {
        this.mDialogUniversalInfoActivity = var1;
        this.color = color;
    }

    private void initDialogButtons() {
        this.fab.setOnClickListener(new OnClickListener() {
            public void onClick(View var1) {

                mDialogUniversalInfoActivity.count = bank;

                DialogAppOrWeb.this.mDialog.dismiss();
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
        this.mDialog.setContentView(R.layout.dialog_app_or_web);
        this.mDialog.setCancelable(true);
        this.mDialog.show();

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
        textItems.add(new CarouselPicker.TextItem("App", 20));//0
        textItems.add(new CarouselPicker.TextItem("Site", 20));//1
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
