package com.fury.myaccounts;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.anjlab.android.iab.v3.BillingProcessor;
import com.anjlab.android.iab.v3.TransactionDetails;

import java.util.Objects;

/**
 * Created by fury on 10/19/2017.
 */
public class Buy_Professional extends Activity implements BillingProcessor.IBillingHandler {

    int Color;
    SharedPreferences one_play_preferences;
    SharedPreferences.Editor one_play_editor;

    BillingProcessor bp;
    int key_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buy_off);

        one_play_preferences = getApplicationContext().getSharedPreferences("PROJECT_NAME", android.content.Context.MODE_PRIVATE);
        one_play_editor = one_play_preferences.edit();
        Color = one_play_preferences.getInt("colorday", 1);
        key_1 = one_play_preferences.getInt("keybuyall", 0);

        bp = new BillingProcessor(this, "code google", this);

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        ImageView image_back = (ImageView)findViewById(R.id.image_back);
        ImageView pic_remove_ads = (ImageView)findViewById(R.id.pic_remove_ads);
        ImageView pic_open_web_app = (ImageView)findViewById(R.id.pic_open_web_app);
        ImageView pic_unfinger = (ImageView)findViewById(R.id.pic_unfinger);
        ImageView pic_security = (ImageView)findViewById(R.id.pic_security);
        TextView text_buy_top = (TextView)findViewById(R.id.text_buy_top);
        TextView text_buy_ad = (TextView)findViewById(R.id.text_buy_ad);
        TextView text_buy_lock = (TextView)findViewById(R.id.text_buy_lock);
        TextView text_buy_finger = (TextView)findViewById(R.id.text_buy_finger);
        TextView text_buy_sec = (TextView)findViewById(R.id.text_buy_sec);
        Button buy = (Button) findViewById(R.id.buy);

        Typeface face2 = Typeface.createFromAsset(getAssets(), "Yantramanav-Light.ttf");
        text_buy_ad.setTypeface(face2);
        text_buy_lock.setTypeface(face2);
        text_buy_finger.setTypeface(face2);
        text_buy_sec.setTypeface(face2);

        if (Color == 2){
            image_back.setImageResource(R.drawable.buyoffdark);
            pic_remove_ads.setImageResource(R.drawable.remove_ad_dark);
            pic_open_web_app.setImageResource(R.drawable.unlockwebemaildark);
            pic_unfinger.setImageResource(R.drawable.unfingedark);
            pic_security.setImageResource(R.drawable.unlockdark);
            text_buy_ad.setTextColor(getResources().getColor(R.color.colorstatus2));
            text_buy_lock.setTextColor(getResources().getColor(R.color.colorstatus2));
            text_buy_finger.setTextColor(getResources().getColor(R.color.colorstatus2));
            text_buy_sec.setTextColor(getResources().getColor(R.color.colorstatus2));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.setStatusBarColor(ContextCompat.getColor(this,R.color.colorstatus2));
            }

        }else if (Color == 1){
            image_back.setImageResource(R.drawable.buyoffday);
            pic_remove_ads.setImageResource(R.drawable.remove_ad_day);
            pic_open_web_app.setImageResource(R.drawable.unlockwebemailday);
            pic_unfinger.setImageResource(R.drawable.unfingerday);
            pic_security.setImageResource(R.drawable.unlockday);
            text_buy_ad.setTextColor(getResources().getColor(R.color.colorstatus1));
            text_buy_lock.setTextColor(getResources().getColor(R.color.colorstatus1));
            text_buy_finger.setTextColor(getResources().getColor(R.color.colorstatus1));
            text_buy_sec.setTextColor(getResources().getColor(R.color.colorstatus1));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.setStatusBarColor(ContextCompat.getColor(this,R.color.colorstatus1));
            }
        }

        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (app_net.getInstance(Buy_Professional.this).isOnline()) {

                    String SKU = "BPOALL";

                    one_play_editor.putBoolean("buyEmail", true);
                    one_play_editor.putBoolean("buywebapp", true);
                    one_play_editor.putBoolean("FingerBuy", true);
                    one_play_editor.apply();

                    /*try {
                        bp.purchase(Buy_Professional.this, SKU);
                    } catch (Exception e) {
                        Toast.makeText(Buy_Professional.this, "Please wait and click again", Toast.LENGTH_LONG).show();
                    }*/

                } else {
                    Toast.makeText(Buy_Professional.this, "Not Connected", Toast.LENGTH_LONG).show();
                }
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
    protected void onDestroy() {
        if (bp != null) {
            bp.release();
        }
        super.onDestroy();
    }

    @Override
    public void onProductPurchased(@NonNull String productId, @Nullable TransactionDetails details) {
        if (Objects.equals(productId, "BPOALL")) {
            if (key_1 == 1) {
                one_play_editor.putInt("keybuyall", 1);
                one_play_editor.putBoolean("buyEmail", true);
                one_play_editor.putBoolean("buywebapp", true);
                one_play_editor.apply();
            } else {
                Toast.makeText(this,"You have bought!",Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(Buy_Professional.this, productId, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onPurchaseHistoryRestored() {

    }

    @Override
    public void onBillingError(int errorCode, @Nullable Throwable error) {
        Toast.makeText(Buy_Professional.this, "Buy unsuccessful!", Toast.LENGTH_SHORT).show();
        //FirebaseCrash.report(new Exception("errorCode Billing GooglePlay : " + errorCode));
    }

    @Override
    public void onBillingInitialized() {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent webview = new Intent(this, CreditCard.class);
        startActivity(webview);
        overridePendingTransition(0, 0);
        finish();

    }

}
