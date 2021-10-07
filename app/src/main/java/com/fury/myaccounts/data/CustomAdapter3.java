package com.fury.myaccounts.data;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fury.myaccounts.DialogDetailWebApp;
import com.fury.myaccounts.R;
import com.scottyab.aescrypt.AESCrypt;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Objects;

import es.dmoral.toasty.Toasty;

/**
 * Created by fury on 9/14/2017.
 */
public class CustomAdapter3 extends BaseAdapter {

    Context c;
    ArrayList<Spacecraft3> spacecrafts;

    SharedPreferences one_play_preferences;
    SharedPreferences.Editor one_play_editor;
    String PasswordAll, Name, User, Password, Email, Mobile, Daiitle;
    int NOE;

    public CustomAdapter3(Context c, ArrayList<Spacecraft3> spacecrafts) {
        this.c = c;
        this.spacecrafts = spacecrafts;
    }

    @Override
    public int getCount() {
        return spacecrafts.size();
    }

    @Override
    public Object getItem(int i) {
        return spacecrafts.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(c).inflate(R.layout.webapp, viewGroup, false);
        }

        one_play_preferences = c.getSharedPreferences("PROJECT_NAME", Context.MODE_PRIVATE);
        one_play_editor = one_play_preferences.edit();
        PasswordAll = one_play_preferences.getString("PFURY", "");

        ImageView imageApp = (ImageView) view.findViewById(R.id.imagecard);
        ImageView icon = (ImageView) view.findViewById(R.id.icon);
        final TextView User2 = (TextView) view.findViewById(R.id.User);
        final TextView textapp = (TextView) view.findViewById(R.id.textapp);
        final TextView website = (TextView) view.findViewById(R.id.website);
        final TextView Password2 = (TextView) view.findViewById(R.id.Password);
        final RelativeLayout back_gr = (RelativeLayout) view.findViewById(R.id.back_gr);
        final TextView textbk2 = (TextView) view.findViewById(R.id.textbk2);
        final TextView textbk3 = (TextView) view.findViewById(R.id.textbk3);
        final TextView textbk4 = (TextView) view.findViewById(R.id.textbk4);
        final TextView textbk6 = (TextView) view.findViewById(R.id.textbk6);
        final TextView textbk7 = (TextView) view.findViewById(R.id.textbk7);
        Spacecraft3 s = (Spacecraft3) this.getItem(i);


        if (s.getUser().length() > 0 && s.getUser() != null) {
            try {
                User = AESCrypt.decrypt(PasswordAll, s.getUser());
            } catch (GeneralSecurityException e) {
                //handle error - could be due to incorrect password or tampered encryptedMsg
            }
        } else {
            User = "";
        }

        if (s.getPassword().length() > 0 && s.getPassword() != null) {
            try {
                Password = AESCrypt.decrypt(PasswordAll, s.getPassword());
            } catch (GeneralSecurityException e) {
                //handle error - could be due to incorrect password or tampered encryptedMsg
            }
        } else {
            Password = "";
        }

        if (s.getName().length() > 0 && s.getName() != null) {
            try {
                Name = AESCrypt.decrypt(PasswordAll, s.getName());
            } catch (GeneralSecurityException e) {
                //handle error - could be due to incorrect password or tampered encryptedMsg
            }
        } else {
            Name = "";
        }

        if (s.getEmail().length() > 0 && s.getEmail() != null) {
            try {
                Email = AESCrypt.decrypt(PasswordAll, s.getEmail());
            } catch (GeneralSecurityException e) {
                //handle error - could be due to incorrect password or tampered encryptedMsg
            }
        } else {
            Email = "";
        }

        if (s.getMobile().length() > 0 && s.getMobile() != null) {
            try {
                Mobile = AESCrypt.decrypt(PasswordAll, s.getMobile());
            } catch (GeneralSecurityException e) {
                //handle error - could be due to incorrect password or tampered encryptedMsg
            }
        } else {
            Mobile = "";
        }

        if (s.getDaiitle().length() > 0 && s.getDaiitle() != null) {
            try {
                Daiitle = AESCrypt.decrypt(PasswordAll, s.getDaiitle());
            } catch (GeneralSecurityException e) {
                //handle error - could be due to incorrect password or tampered encryptedMsg
            }
        } else {
            Daiitle = "";
        }


        int back = s.getBack();
        NOE = s.getNOE();
        String bn = String.valueOf(NOE);
        String ba = String.valueOf(back);

        if (NOE == 0) {

            //App

            website.setVisibility(View.INVISIBLE);

            User2.setText(User);
            Password2.setText(Password);
            textbk2.setText(Daiitle);
            textbk3.setText(bn);
            textbk4.setText(ba);
            textbk6.setText(Email);
            textbk7.setText(Mobile);

            if (Objects.equals(Name, "instagram")) {
                Picasso.with(c).load(R.drawable.insta_back).into(imageApp);
                Picasso.with(c).load(R.drawable.insta_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp1));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp1));
            } else if (Objects.equals(Name, "insta")) {
                Picasso.with(c).load(R.drawable.insta_back).into(imageApp);
                Picasso.with(c).load(R.drawable.insta_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp1));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp1));
            } else if (Objects.equals(Name, "Instagram")) {
                Picasso.with(c).load(R.drawable.insta_back).into(imageApp);
                Picasso.with(c).load(R.drawable.insta_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp1));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp1));
            } else if (Objects.equals(Name, "INSTAGRAM")) {
                Picasso.with(c).load(R.drawable.insta_back).into(imageApp);
                Picasso.with(c).load(R.drawable.insta_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp1));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp1));
            } else if (Objects.equals(Name, "ig")) {
                Picasso.with(c).load(R.drawable.insta_back).into(imageApp);
                Picasso.with(c).load(R.drawable.insta_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp1));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp1));
            } else if (Objects.equals(Name, "IG")) {
                Picasso.with(c).load(R.drawable.insta_back).into(imageApp);
                Picasso.with(c).load(R.drawable.insta_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp1));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp1));
            } else if (Objects.equals(Name, "skype")) {
                Picasso.with(c).load(R.drawable.skype_back).into(imageApp);
                Picasso.with(c).load(R.drawable.skype_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp2));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp2));
            } else if (Objects.equals(Name, "Skype")) {
                Picasso.with(c).load(R.drawable.skype_back).into(imageApp);
                Picasso.with(c).load(R.drawable.skype_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp2));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp2));
            } else if (Objects.equals(Name, "SKYPE")) {
                Picasso.with(c).load(R.drawable.skype_back).into(imageApp);
                Picasso.with(c).load(R.drawable.skype_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp2));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp2));
            } else if (Objects.equals(Name, "yahoo")) {
                Picasso.with(c).load(R.drawable.yahoo_back).into(imageApp);
                Picasso.with(c).load(R.drawable.yahoo_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp3));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp3));
            } else if (Objects.equals(Name, "Yahoo")) {
                Picasso.with(c).load(R.drawable.yahoo_back).into(imageApp);
                Picasso.with(c).load(R.drawable.yahoo_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp3));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp3));
            } else if (Objects.equals(Name, "YAHOO")) {
                Picasso.with(c).load(R.drawable.yahoo_back).into(imageApp);
                Picasso.with(c).load(R.drawable.yahoo_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp3));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp3));
            } else if (Objects.equals(Name, "wechat")) {
                Picasso.with(c).load(R.drawable.wechat_back).into(imageApp);
                Picasso.with(c).load(R.drawable.wechat_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp4));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp4));
            } else if (Objects.equals(Name, "Wechat")) {
                Picasso.with(c).load(R.drawable.wechat_back).into(imageApp);
                Picasso.with(c).load(R.drawable.wechat_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp4));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp4));
            } else if (Objects.equals(Name, "WeChat")) {
                Picasso.with(c).load(R.drawable.wechat_back).into(imageApp);
                Picasso.with(c).load(R.drawable.wechat_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp4));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp4));
            } else if (Objects.equals(Name, "WECHAT")) {
                Picasso.with(c).load(R.drawable.wechat_back).into(imageApp);
                Picasso.with(c).load(R.drawable.wechat_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp4));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp4));
            } else if (Objects.equals(Name, "voxer")) {
                Picasso.with(c).load(R.drawable.voxer_back).into(imageApp);
                Picasso.with(c).load(R.drawable.voxer_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp5));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp5));
            } else if (Objects.equals(Name, "Voxer")) {
                Picasso.with(c).load(R.drawable.voxer_back).into(imageApp);
                Picasso.with(c).load(R.drawable.voxer_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp5));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp5));
            } else if (Objects.equals(Name, "VOXER")) {
                Picasso.with(c).load(R.drawable.voxer_back).into(imageApp);
                Picasso.with(c).load(R.drawable.voxer_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp5));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp5));
            } else if (Objects.equals(Name, "viber")) {
                Picasso.with(c).load(R.drawable.viber_back).into(imageApp);
                Picasso.with(c).load(R.drawable.viber_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp6));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp6));
            } else if (Objects.equals(Name, "Viber")) {
                Picasso.with(c).load(R.drawable.viber_back).into(imageApp);
                Picasso.with(c).load(R.drawable.viber_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp6));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp6));
            } else if (Objects.equals(Name, "VIBER")) {
                Picasso.with(c).load(R.drawable.viber_back).into(imageApp);
                Picasso.with(c).load(R.drawable.viber_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp6));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp6));
            } else if (Objects.equals(Name, "admob")) {
                Picasso.with(c).load(R.drawable.admob_back).into(imageApp);
                Picasso.with(c).load(R.drawable.admob_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp7));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp7));
            } else if (Objects.equals(Name, "Admob")) {
                Picasso.with(c).load(R.drawable.admob_back).into(imageApp);
                Picasso.with(c).load(R.drawable.admob_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp7));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp7));
            } else if (Objects.equals(Name, "AdMob")) {
                Picasso.with(c).load(R.drawable.admob_back).into(imageApp);
                Picasso.with(c).load(R.drawable.admob_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp7));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp7));
            } else if (Objects.equals(Name, "ADMOB")) {
                Picasso.with(c).load(R.drawable.admob_back).into(imageApp);
                Picasso.with(c).load(R.drawable.admob_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp7));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp7));
            } else if (Objects.equals(Name, "tango")) {
                Picasso.with(c).load(R.drawable.tango_back).into(imageApp);
                Picasso.with(c).load(R.drawable.tango_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp8));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp8));
            } else if (Objects.equals(Name, "Tango")) {
                Picasso.with(c).load(R.drawable.tango_back).into(imageApp);
                Picasso.with(c).load(R.drawable.tango_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp8));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp8));
            } else if (Objects.equals(Name, "TANGO")) {
                Picasso.with(c).load(R.drawable.tango_back).into(imageApp);
                Picasso.with(c).load(R.drawable.tango_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp8));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp8));
            } else if (Objects.equals(Name, "telegram")) {
                Picasso.with(c).load(R.drawable.t_back).into(imageApp);
                Picasso.with(c).load(R.drawable.t_logo).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp9));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp9));
            } else if (Objects.equals(Name, "t")) {
                Picasso.with(c).load(R.drawable.t_back).into(imageApp);
                Picasso.with(c).load(R.drawable.t_logo).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp9));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp9));
            } else if (Objects.equals(Name, "T")) {
                Picasso.with(c).load(R.drawable.t_back).into(imageApp);
                Picasso.with(c).load(R.drawable.t_logo).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp9));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp9));
            } else if (Objects.equals(Name, "Telegram")) {
                Picasso.with(c).load(R.drawable.t_back).into(imageApp);
                Picasso.with(c).load(R.drawable.t_logo).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp9));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp9));
            } else if (Objects.equals(Name, "Teleg")) {
                Picasso.with(c).load(R.drawable.t_back).into(imageApp);
                Picasso.with(c).load(R.drawable.t_logo).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp9));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp9));
            } else if (Objects.equals(Name, "Tele")) {
                Picasso.with(c).load(R.drawable.t_back).into(imageApp);
                Picasso.with(c).load(R.drawable.t_logo).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp9));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp9));
            } else if (Objects.equals(Name, "TeleGram")) {
                Picasso.with(c).load(R.drawable.t_back).into(imageApp);
                Picasso.with(c).load(R.drawable.t_logo).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp9));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp9));
            } else if (Objects.equals(Name, "TELEGRAM")) {
                Picasso.with(c).load(R.drawable.t_back).into(imageApp);
                Picasso.with(c).load(R.drawable.t_logo).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp9));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp9));
            } else if (Objects.equals(Name, "TGram")) {
                Picasso.with(c).load(R.drawable.t_back).into(imageApp);
                Picasso.with(c).load(R.drawable.t_logo).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp9));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp9));
            } else if (Objects.equals(Name, "TG")) {
                Picasso.with(c).load(R.drawable.t_back).into(imageApp);
                Picasso.with(c).load(R.drawable.t_logo).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp9));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp9));
            } else if (Objects.equals(Name, "tgram")) {
                Picasso.with(c).load(R.drawable.t_back).into(imageApp);
                Picasso.with(c).load(R.drawable.t_logo).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp9));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp9));
            } else if (Objects.equals(Name, "tGram")) {
                Picasso.with(c).load(R.drawable.t_back).into(imageApp);
                Picasso.with(c).load(R.drawable.t_logo).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp9));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp9));
            } else if (Objects.equals(Name, "Tgram")) {
                Picasso.with(c).load(R.drawable.t_back).into(imageApp);
                Picasso.with(c).load(R.drawable.t_logo).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp9));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp9));
            } else if (Objects.equals(Name, "tg")) {
                Picasso.with(c).load(R.drawable.t_back).into(imageApp);
                Picasso.with(c).load(R.drawable.t_logo).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp9));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp9));
            } else if (Objects.equals(Name, "wordpress")) {
                Picasso.with(c).load(R.drawable.t_back).into(imageApp);
                Picasso.with(c).load(R.drawable.wordpress_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp9));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp9));
            } else if (Objects.equals(Name, "Wordpress")) {
                Picasso.with(c).load(R.drawable.t_back).into(imageApp);
                Picasso.with(c).load(R.drawable.wordpress_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp9));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp9));
            } else if (Objects.equals(Name, "WordPress")) {
                Picasso.with(c).load(R.drawable.t_back).into(imageApp);
                Picasso.with(c).load(R.drawable.wordpress_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp9));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp9));
            } else if (Objects.equals(Name, "WP")) {
                Picasso.with(c).load(R.drawable.t_back).into(imageApp);
                Picasso.with(c).load(R.drawable.wordpress_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp9));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp9));
            } else if (Objects.equals(Name, "kik.")) {
                Picasso.with(c).load(R.drawable.kik_back).into(imageApp);
                Picasso.with(c).load(R.drawable.kik_logo).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp10));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp10));
            } else if (Objects.equals(Name, "kik")) {
                Picasso.with(c).load(R.drawable.kik_back).into(imageApp);
                Picasso.with(c).load(R.drawable.kik_logo).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp10));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp10));
            } else if (Objects.equals(Name, "Kik")) {
                Picasso.with(c).load(R.drawable.kik_back).into(imageApp);
                Picasso.with(c).load(R.drawable.kik_logo).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp10));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp10));
            } else if (Objects.equals(Name, "Kik.")) {
                Picasso.with(c).load(R.drawable.kik_back).into(imageApp);
                Picasso.with(c).load(R.drawable.kik_logo).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp10));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp10));
            } else if (Objects.equals(Name, "KIK.")) {
                Picasso.with(c).load(R.drawable.kik_back).into(imageApp);
                Picasso.with(c).load(R.drawable.kik_logo).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp10));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp10));
            } else if (Objects.equals(Name, "KIK")) {
                Picasso.with(c).load(R.drawable.kik_back).into(imageApp);
                Picasso.with(c).load(R.drawable.kik_logo).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp10));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp10));
            } else if (Objects.equals(Name, "line")) {
                Picasso.with(c).load(R.drawable.line_back).into(imageApp);
                Picasso.with(c).load(R.drawable.line_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp10));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp10));
            } else if (Objects.equals(Name, "Line")) {
                Picasso.with(c).load(R.drawable.line_back).into(imageApp);
                Picasso.with(c).load(R.drawable.line_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp10));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp10));
            } else if (Objects.equals(Name, "LINE")) {
                Picasso.with(c).load(R.drawable.line_back).into(imageApp);
                Picasso.with(c).load(R.drawable.line_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp10));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp10));
            } else if (Objects.equals(Name, "hangouts")) {
                Picasso.with(c).load(R.drawable.hangouts_back).into(imageApp);
                Picasso.with(c).load(R.drawable.hangouts_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp11));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp11));
            } else if (Objects.equals(Name, "Hangouts")) {
                Picasso.with(c).load(R.drawable.hangouts_back).into(imageApp);
                Picasso.with(c).load(R.drawable.hangouts_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp11));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp11));
            } else if (Objects.equals(Name, "Hangout")) {
                Picasso.with(c).load(R.drawable.hangouts_back).into(imageApp);
                Picasso.with(c).load(R.drawable.hangouts_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp11));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp11));
            } else if (Objects.equals(Name, "facebook")) {
                Picasso.with(c).load(R.drawable.facebook_back).into(imageApp);
                Picasso.with(c).load(R.drawable.facebook_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp12));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp12));
            } else if (Objects.equals(Name, "Facebook")) {
                Picasso.with(c).load(R.drawable.facebook_back).into(imageApp);
                Picasso.with(c).load(R.drawable.facebook_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp12));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp12));
            } else if (Objects.equals(Name, "FaceBook")) {
                Picasso.with(c).load(R.drawable.facebook_back).into(imageApp);
                Picasso.with(c).load(R.drawable.facebook_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp12));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp12));
            } else if (Objects.equals(Name, "FACEBOOK")) {
                Picasso.with(c).load(R.drawable.facebook_back).into(imageApp);
                Picasso.with(c).load(R.drawable.facebook_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp12));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp12));
            } else if (Objects.equals(Name, "FB")) {
                Picasso.with(c).load(R.drawable.facebook_back).into(imageApp);
                Picasso.with(c).load(R.drawable.facebook_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp12));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp12));
            } else if (Objects.equals(Name, "fb")) {
                Picasso.with(c).load(R.drawable.facebook_back).into(imageApp);
                Picasso.with(c).load(R.drawable.facebook_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp12));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp12));
            } else if (Objects.equals(Name, "facebook messenger")) {
                Picasso.with(c).load(R.drawable.facebook_messenger_back).into(imageApp);
                Picasso.with(c).load(R.drawable.facebook_messenger_logo).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp13));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp13));
            } else if (Objects.equals(Name, "Facebook Messenger")) {
                Picasso.with(c).load(R.drawable.facebook_messenger_back).into(imageApp);
                Picasso.with(c).load(R.drawable.facebook_messenger_logo).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp13));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp13));
            } else if (Objects.equals(Name, "FMessenger")) {
                Picasso.with(c).load(R.drawable.facebook_messenger_back).into(imageApp);
                Picasso.with(c).load(R.drawable.facebook_messenger_logo).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp13));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp13));
            } else if (Objects.equals(Name, "FM")) {
                Picasso.with(c).load(R.drawable.facebook_messenger_back).into(imageApp);
                Picasso.with(c).load(R.drawable.facebook_messenger_logo).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp13));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp13));
            } else if (Objects.equals(Name, "fm")) {
                Picasso.with(c).load(R.drawable.facebook_messenger_back).into(imageApp);
                Picasso.with(c).load(R.drawable.facebook_messenger_logo).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp13));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp13));
            } else if (Objects.equals(Name, "fbm")) {
                Picasso.with(c).load(R.drawable.facebook_messenger_back).into(imageApp);
                Picasso.with(c).load(R.drawable.facebook_messenger_logo).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp13));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp13));
            } else if (Objects.equals(Name, "snapchat")) {
                Picasso.with(c).load(R.drawable.snapchat_back).into(imageApp);
                Picasso.with(c).load(R.drawable.snapchat_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp14));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp14));
            } else if (Objects.equals(Name, "Snapchat")) {
                Picasso.with(c).load(R.drawable.snapchat_back).into(imageApp);
                Picasso.with(c).load(R.drawable.snapchat_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp14));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp14));
            } else if (Objects.equals(Name, "SnapChat")) {
                Picasso.with(c).load(R.drawable.snapchat_back).into(imageApp);
                Picasso.with(c).load(R.drawable.snapchat_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp14));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp14));
            } else if (Objects.equals(Name, "Snap")) {
                Picasso.with(c).load(R.drawable.snapchat_back).into(imageApp);
                Picasso.with(c).load(R.drawable.snapchat_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp14));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp14));
            } else if (Objects.equals(Name, "SC")) {
                Picasso.with(c).load(R.drawable.snapchat_back).into(imageApp);
                Picasso.with(c).load(R.drawable.snapchat_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp14));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp14));
            } else if (Objects.equals(Name, "SCH")) {
                Picasso.with(c).load(R.drawable.snapchat_back).into(imageApp);
                Picasso.with(c).load(R.drawable.snapchat_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp14));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp14));
            } else if (Objects.equals(Name, "snap")) {
                Picasso.with(c).load(R.drawable.snapchat_back).into(imageApp);
                Picasso.with(c).load(R.drawable.snapchat_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp14));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp14));
            } else if (Objects.equals(Name, "wispi")) {
                Picasso.with(c).load(R.drawable.wipsi_back).into(imageApp);
                Picasso.with(c).load(R.drawable.wispi_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp15));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp15));
            } else if (Objects.equals(Name, "Wispi")) {
                Picasso.with(c).load(R.drawable.wipsi_back).into(imageApp);
                Picasso.with(c).load(R.drawable.wispi_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp15));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp15));
            } else if (Objects.equals(Name, "WISPI")) {
                Picasso.with(c).load(R.drawable.wipsi_back).into(imageApp);
                Picasso.with(c).load(R.drawable.wispi_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp15));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp15));
            } else if (Objects.equals(Name, "linkedin")) {
                Picasso.with(c).load(R.drawable.linkedin_back).into(imageApp);
                Picasso.with(c).load(R.drawable.linkedin_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp16));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp16));
            } else if (Objects.equals(Name, "Linkedin")) {
                Picasso.with(c).load(R.drawable.linkedin_back).into(imageApp);
                Picasso.with(c).load(R.drawable.linkedin_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp16));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp16));
            } else if (Objects.equals(Name, "Link")) {
                Picasso.with(c).load(R.drawable.linkedin_back).into(imageApp);
                Picasso.with(c).load(R.drawable.linkedin_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp16));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp16));
            } else if (Objects.equals(Name, "link")) {
                Picasso.with(c).load(R.drawable.linkedin_back).into(imageApp);
                Picasso.with(c).load(R.drawable.linkedin_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp16));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp16));
            } else if (Objects.equals(Name, "google plus")) {
                Picasso.with(c).load(R.drawable.snapchat_back).into(imageApp);
                Picasso.with(c).load(R.drawable.google_plus_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp17));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp17));
            } else if (Objects.equals(Name, "Google Plus")) {
                Picasso.with(c).load(R.drawable.snapchat_back).into(imageApp);
                Picasso.with(c).load(R.drawable.google_plus_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp17));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp17));
            } else if (Objects.equals(Name, "GPlus")) {
                Picasso.with(c).load(R.drawable.snapchat_back).into(imageApp);
                Picasso.with(c).load(R.drawable.google_plus_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp17));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp17));
            } else if (Objects.equals(Name, "gplus")) {
                Picasso.with(c).load(R.drawable.snapchat_back).into(imageApp);
                Picasso.with(c).load(R.drawable.google_plus_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp17));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp17));
            } else if (Objects.equals(Name, "plus")) {
                Picasso.with(c).load(R.drawable.snapchat_back).into(imageApp);
                Picasso.with(c).load(R.drawable.google_plus_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp17));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp17));
            } else if (Objects.equals(Name, "googleplus")) {
                Picasso.with(c).load(R.drawable.snapchat_back).into(imageApp);
                Picasso.with(c).load(R.drawable.google_plus_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp17));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp17));
            } else if (Objects.equals(Name, "googlep")) {
                Picasso.with(c).load(R.drawable.snapchat_back).into(imageApp);
                Picasso.with(c).load(R.drawable.google_plus_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp17));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp17));
            } else if (Objects.equals(Name, "google p")) {
                Picasso.with(c).load(R.drawable.snapchat_back).into(imageApp);
                Picasso.with(c).load(R.drawable.google_plus_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp17));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp17));
            } else if (Objects.equals(Name, "google adwords")) {
                Picasso.with(c).load(R.drawable.adwords_back).into(imageApp);
                Picasso.with(c).load(R.drawable.adwords_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp18));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp18));
            } else if (Objects.equals(Name, "Google Adwords")) {
                Picasso.with(c).load(R.drawable.adwords_back).into(imageApp);
                Picasso.with(c).load(R.drawable.adwords_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp18));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp18));
            } else if (Objects.equals(Name, "GAdwords")) {
                Picasso.with(c).load(R.drawable.adwords_back).into(imageApp);
                Picasso.with(c).load(R.drawable.adwords_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp18));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp18));
            } else if (Objects.equals(Name, "Adwords")) {
                Picasso.with(c).load(R.drawable.adwords_back).into(imageApp);
                Picasso.with(c).load(R.drawable.adwords_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp18));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp18));
            } else if (Objects.equals(Name, "Adword")) {
                Picasso.with(c).load(R.drawable.adwords_back).into(imageApp);
                Picasso.with(c).load(R.drawable.adwords_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp18));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp18));
            } else if (Objects.equals(Name, "AW")) {
                Picasso.with(c).load(R.drawable.adwords_back).into(imageApp);
                Picasso.with(c).load(R.drawable.adwords_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp18));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp18));
            } else if (Objects.equals(Name, "allo")) {
                Picasso.with(c).load(R.drawable.allo_back).into(imageApp);
                Picasso.with(c).load(R.drawable.allo_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp19));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp19));
            } else if (Objects.equals(Name, "Google allo")) {
                Picasso.with(c).load(R.drawable.allo_back).into(imageApp);
                Picasso.with(c).load(R.drawable.allo_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp19));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp19));
            } else if (Objects.equals(Name, "Allo")) {
                Picasso.with(c).load(R.drawable.allo_back).into(imageApp);
                Picasso.with(c).load(R.drawable.allo_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp19));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp19));
            } else if (Objects.equals(Name, "ALLO")) {
                Picasso.with(c).load(R.drawable.allo_back).into(imageApp);
                Picasso.with(c).load(R.drawable.allo_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp19));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp19));
            } else if (Objects.equals(Name, "GA")) {
                Picasso.with(c).load(R.drawable.allo_back).into(imageApp);
                Picasso.with(c).load(R.drawable.allo_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp19));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp19));
            } else if (Objects.equals(Name, "ga")) {
                Picasso.with(c).load(R.drawable.allo_back).into(imageApp);
                Picasso.with(c).load(R.drawable.allo_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp19));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp19));
            } else if (Objects.equals(Name, "netflix")) {
                Picasso.with(c).load(R.drawable.n_back).into(imageApp);
                Picasso.with(c).load(R.drawable.n_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp20));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp20));
            } else if (Objects.equals(Name, "Netflix")) {
                Picasso.with(c).load(R.drawable.n_back).into(imageApp);
                Picasso.with(c).load(R.drawable.n_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp20));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp20));
            } else if (Objects.equals(Name, "blogger")) {
                Picasso.with(c).load(R.drawable.blogger_back).into(imageApp);
                Picasso.with(c).load(R.drawable.blogger_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp21));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp21));
            } else if (Objects.equals(Name, "Blogger")) {
                Picasso.with(c).load(R.drawable.blogger_back).into(imageApp);
                Picasso.with(c).load(R.drawable.blogger_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp21));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp21));
            } else if (Objects.equals(Name, "amazon")) {
                Picasso.with(c).load(R.drawable.amazon_back).into(imageApp);
                Picasso.with(c).load(R.drawable.amazon_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp22));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp22));
            } else if (Objects.equals(Name, "Amazon")) {
                Picasso.with(c).load(R.drawable.amazon_back).into(imageApp);
                Picasso.with(c).load(R.drawable.amazon_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp22));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp22));
            } else if (Objects.equals(Name, "Amazon shop")) {
                Picasso.with(c).load(R.drawable.amazon_back).into(imageApp);
                Picasso.with(c).load(R.drawable.amazon_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp22));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp22));
            } else if (Objects.equals(Name, "twitter")) {
                Picasso.with(c).load(R.drawable.amazon_back).into(imageApp);
                Picasso.with(c).load(R.drawable.twitter_back).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp13));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp13));
            } else if (Objects.equals(Name, "Twitter")) {
                Picasso.with(c).load(R.drawable.amazon_back).into(imageApp);
                Picasso.with(c).load(R.drawable.twitter_back).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp13));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp13));
            } else if (Objects.equals(Name, "Twit")) {
                Picasso.with(c).load(R.drawable.amazon_back).into(imageApp);
                Picasso.with(c).load(R.drawable.twitter_back).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp13));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp13));
            } else if (Objects.equals(Name, "Twitt")) {
                Picasso.with(c).load(R.drawable.amazon_back).into(imageApp);
                Picasso.with(c).load(R.drawable.twitter_back).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp13));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp13));
            } else if (Objects.equals(Name, "persian stack")) {
                Picasso.with(c).load(R.drawable.persian_stack_back).into(imageApp);
                Picasso.with(c).load(R.drawable.persian_stack_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp23));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp23));
            } else if (Objects.equals(Name, "persianstack")) {
                Picasso.with(c).load(R.drawable.persian_stack_back).into(imageApp);
                Picasso.with(c).load(R.drawable.persian_stack_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp23));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp23));
            } else if (Objects.equals(Name, "persianstacks")) {
                Picasso.with(c).load(R.drawable.persian_stack_back).into(imageApp);
                Picasso.with(c).load(R.drawable.persian_stack_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp23));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp23));
            } else if (Objects.equals(Name, "persian stacks")) {
                Picasso.with(c).load(R.drawable.persian_stack_back).into(imageApp);
                Picasso.with(c).load(R.drawable.persian_stack_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp23));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp23));
            } else if (Objects.equals(Name, "pstacks")) {
                Picasso.with(c).load(R.drawable.persian_stack_back).into(imageApp);
                Picasso.with(c).load(R.drawable.persian_stack_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp23));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp23));
            } else if (Objects.equals(Name, "pstack")) {
                Picasso.with(c).load(R.drawable.persian_stack_back).into(imageApp);
                Picasso.with(c).load(R.drawable.persian_stack_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp23));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp23));
            } else if (Objects.equals(Name, "ps")) {
                Picasso.with(c).load(R.drawable.persian_stack_back).into(imageApp);
                Picasso.with(c).load(R.drawable.persian_stack_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp23));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp23));
            } else if (Objects.equals(Name, "persian stacks")) {
                Picasso.with(c).load(R.drawable.persian_stack_back).into(imageApp);
                Picasso.with(c).load(R.drawable.persian_stack_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp23));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp23));
            } else if (Objects.equals(Name, "Persian Stack")) {
                Picasso.with(c).load(R.drawable.persian_stack_back).into(imageApp);
                Picasso.with(c).load(R.drawable.persian_stack_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp23));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp23));
            } else if (Objects.equals(Name, "alibaba")) {
                Picasso.with(c).load(R.drawable.alibaba_back).into(imageApp);
                Picasso.with(c).load(R.drawable.alibaba_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp24));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp24));
            } else if (Objects.equals(Name, "ali baba")) {
                Picasso.with(c).load(R.drawable.alibaba_back).into(imageApp);
                Picasso.with(c).load(R.drawable.alibaba_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp24));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp24));
            } else if (Objects.equals(Name, "Alibaba")) {
                Picasso.with(c).load(R.drawable.alibaba_back).into(imageApp);
                Picasso.with(c).load(R.drawable.alibaba_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp24));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp24));
            } else if (Objects.equals(Name, "AB")) {
                Picasso.with(c).load(R.drawable.alibaba_back).into(imageApp);
                Picasso.with(c).load(R.drawable.alibaba_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp24));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp24));
            } else if (Objects.equals(Name, "ab")) {
                Picasso.with(c).load(R.drawable.alibaba_back).into(imageApp);
                Picasso.with(c).load(R.drawable.alibaba_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp24));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp24));
            } else if (Objects.equals(Name, "tumblr")) {
                Picasso.with(c).load(R.drawable.tumber_back).into(imageApp);
                Picasso.with(c).load(R.drawable.tumber_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp25));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp25));
            } else if (Objects.equals(Name, "Tumblr")) {
                Picasso.with(c).load(R.drawable.tumber_back).into(imageApp);
                Picasso.with(c).load(R.drawable.tumber_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp25));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp25));
            } else if (Objects.equals(Name, "T")) {
                Picasso.with(c).load(R.drawable.tumber_back).into(imageApp);
                Picasso.with(c).load(R.drawable.tumber_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp25));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp25));
            } else if (Objects.equals(Name, "github")) {
                Picasso.with(c).load(R.drawable.github_back).into(imageApp);
                Picasso.with(c).load(R.drawable.github_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp26));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp26));
            } else if (Objects.equals(Name, "Github")) {
                Picasso.with(c).load(R.drawable.github_back).into(imageApp);
                Picasso.with(c).load(R.drawable.github_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp26));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp26));
            } else if (Objects.equals(Name, "GHub")) {
                Picasso.with(c).load(R.drawable.github_back).into(imageApp);
                Picasso.with(c).load(R.drawable.github_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp26));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp26));
            } else if (Objects.equals(Name, "GH")) {
                Picasso.with(c).load(R.drawable.github_back).into(imageApp);
                Picasso.with(c).load(R.drawable.github_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp26));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp26));
            } else if (Objects.equals(Name, "ebay")) {
                Picasso.with(c).load(R.drawable.ebay_back).into(imageApp);
                Picasso.with(c).load(R.drawable.ebay_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.gray2));
                Password2.setTextColor(c.getResources().getColor(R.color.gray2));
            } else if (Objects.equals(Name, "Ebay")) {
                Picasso.with(c).load(R.drawable.ebay_back).into(imageApp);
                Picasso.with(c).load(R.drawable.ebay_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.gray2));
                Password2.setTextColor(c.getResources().getColor(R.color.gray2));
            } else if (Objects.equals(Name, "uber")) {
                Picasso.with(c).load(R.drawable.amazon_back).into(imageApp);
                Picasso.with(c).load(R.drawable.uber_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.gray2));
                Password2.setTextColor(c.getResources().getColor(R.color.gray2));
            } else if (Objects.equals(Name, "Uber")) {
                Picasso.with(c).load(R.drawable.amazon_back).into(imageApp);
                Picasso.with(c).load(R.drawable.uber_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.gray2));
                Password2.setTextColor(c.getResources().getColor(R.color.gray2));
            } else {
                if (back == 1) {
                    Picasso.with(c).load(R.drawable.app_card1).into(imageApp);
                } else if (back == 2) {
                    Picasso.with(c).load(R.drawable.app_card2).into(imageApp);
                } else if (back == 3) {
                    Picasso.with(c).load(R.drawable.app_card3).into(imageApp);
                } else if (back == 4) {
                    Picasso.with(c).load(R.drawable.app_card4).into(imageApp);
                } else if (back == 5) {
                    Picasso.with(c).load(R.drawable.app_card5).into(imageApp);
                } else if (back == 6) {
                    Picasso.with(c).load(R.drawable.app_card6).into(imageApp);
                } else if (back == 7) {
                    Picasso.with(c).load(R.drawable.app_card7).into(imageApp);
                }
                textapp.setText(Name);
                textapp.setVisibility(View.VISIBLE);
                icon.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.black));
                Password2.setTextColor(c.getResources().getColor(R.color.black));
            }


            Typeface face = Typeface.createFromAsset(c.getAssets(), "Yantramanav-Light.ttf");
            Typeface face2 = Typeface.createFromAsset(c.getAssets(), "kredit_back.ttf");
            textapp.setTypeface(face2);
            User2.setTypeface(face);
            Password2.setTypeface(face);

        } else if (NOE == 1) {

            //Web

            User2.setText(User);
            Password2.setText(Password);
            textbk2.setText(Daiitle);
            textbk3.setText(bn);
            textbk4.setText(ba);
            textbk6.setText(Email);
            textbk7.setText(Mobile);


            if (Objects.equals(Name, "instagram")) {
                Picasso.with(c).load(R.drawable.insta_back).into(imageApp);
                Picasso.with(c).load(R.drawable.insta_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp1));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp1));
            } else if (Objects.equals(Name, "insta")) {
                Picasso.with(c).load(R.drawable.insta_back).into(imageApp);
                Picasso.with(c).load(R.drawable.insta_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp1));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp1));
            } else if (Objects.equals(Name, "www.instagram.com")) {
                Picasso.with(c).load(R.drawable.insta_back).into(imageApp);
                Picasso.with(c).load(R.drawable.insta_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp1));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp1));
            } else if (Objects.equals(Name, "instagram.com")) {
                Picasso.with(c).load(R.drawable.insta_back).into(imageApp);
                Picasso.with(c).load(R.drawable.insta_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp1));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp1));
            } else if (Objects.equals(Name, "https://www.instagram.com")) {
                Picasso.with(c).load(R.drawable.insta_back).into(imageApp);
                Picasso.with(c).load(R.drawable.insta_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp1));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp1));
            } else if (Objects.equals(Name, "IG")) {
                Picasso.with(c).load(R.drawable.insta_back).into(imageApp);
                Picasso.with(c).load(R.drawable.insta_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp1));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp1));
            } else if (Objects.equals(Name, "skype")) {
                Picasso.with(c).load(R.drawable.skype_back).into(imageApp);
                Picasso.with(c).load(R.drawable.skype_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp2));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp2));
            } else if (Objects.equals(Name, "https://www.skype.com")) {
                Picasso.with(c).load(R.drawable.skype_back).into(imageApp);
                Picasso.with(c).load(R.drawable.skype_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp2));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp2));
            } else if (Objects.equals(Name, "www.skype.com")) {
                Picasso.with(c).load(R.drawable.skype_back).into(imageApp);
                Picasso.with(c).load(R.drawable.skype_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp2));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp2));
            } else if (Objects.equals(Name, "skype.com")) {
                Picasso.with(c).load(R.drawable.skype_back).into(imageApp);
                Picasso.with(c).load(R.drawable.skype_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp2));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp2));
            } else if (Objects.equals(Name, "yahoo")) {
                Picasso.with(c).load(R.drawable.yahoo_back).into(imageApp);
                Picasso.with(c).load(R.drawable.yahoo_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp3));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp3));
            } else if (Objects.equals(Name, "https://www.yahoo.com")) {
                Picasso.with(c).load(R.drawable.yahoo_back).into(imageApp);
                Picasso.with(c).load(R.drawable.yahoo_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp3));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp3));
            } else if (Objects.equals(Name, "www.yahoo.com")) {
                Picasso.with(c).load(R.drawable.yahoo_back).into(imageApp);
                Picasso.with(c).load(R.drawable.yahoo_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp3));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp3));
            } else if (Objects.equals(Name, "yahoo.com")) {
                Picasso.with(c).load(R.drawable.yahoo_back).into(imageApp);
                Picasso.with(c).load(R.drawable.yahoo_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp3));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp3));
            } else if (Objects.equals(Name, "wechat")) {
                Picasso.with(c).load(R.drawable.wechat_back).into(imageApp);
                Picasso.with(c).load(R.drawable.wechat_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp4));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp4));
            } else if (Objects.equals(Name, "https://www.wechat.com")) {
                Picasso.with(c).load(R.drawable.wechat_back).into(imageApp);
                Picasso.with(c).load(R.drawable.wechat_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp4));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp4));
            } else if (Objects.equals(Name, "www.wechat.com")) {
                Picasso.with(c).load(R.drawable.wechat_back).into(imageApp);
                Picasso.with(c).load(R.drawable.wechat_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp4));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp4));
            } else if (Objects.equals(Name, "wechat.com")) {
                Picasso.with(c).load(R.drawable.wechat_back).into(imageApp);
                Picasso.with(c).load(R.drawable.wechat_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp4));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp4));
            } else if (Objects.equals(Name, "https://web.wechat.com")) {
                Picasso.with(c).load(R.drawable.wechat_back).into(imageApp);
                Picasso.with(c).load(R.drawable.wechat_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp4));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp4));
            } else if (Objects.equals(Name, "web.wechat.com")) {
                Picasso.with(c).load(R.drawable.wechat_back).into(imageApp);
                Picasso.with(c).load(R.drawable.wechat_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp4));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp4));
            } else if (Objects.equals(Name, "voxer")) {
                Picasso.with(c).load(R.drawable.voxer_back).into(imageApp);
                Picasso.with(c).load(R.drawable.voxer_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp5));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp5));
            } else if (Objects.equals(Name, "https://voxer.com")) {
                Picasso.with(c).load(R.drawable.voxer_back).into(imageApp);
                Picasso.with(c).load(R.drawable.voxer_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp5));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp5));
            } else if (Objects.equals(Name, "voxer.com")) {
                Picasso.with(c).load(R.drawable.voxer_back).into(imageApp);
                Picasso.with(c).load(R.drawable.voxer_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp5));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp5));
            } else if (Objects.equals(Name, "https://web.voxer.com")) {
                Picasso.with(c).load(R.drawable.voxer_back).into(imageApp);
                Picasso.with(c).load(R.drawable.voxer_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp5));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp5));
            } else if (Objects.equals(Name, "web.voxer.com")) {
                Picasso.with(c).load(R.drawable.voxer_back).into(imageApp);
                Picasso.with(c).load(R.drawable.voxer_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp5));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp5));
            } else if (Objects.equals(Name, "viber")) {
                Picasso.with(c).load(R.drawable.viber_back).into(imageApp);
                Picasso.with(c).load(R.drawable.viber_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp6));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp6));
            } else if (Objects.equals(Name, "https://www.viber.com")) {
                Picasso.with(c).load(R.drawable.viber_back).into(imageApp);
                Picasso.with(c).load(R.drawable.viber_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp6));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp6));
            } else if (Objects.equals(Name, "www.viber.com")) {
                Picasso.with(c).load(R.drawable.viber_back).into(imageApp);
                Picasso.with(c).load(R.drawable.viber_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp6));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp6));
            } else if (Objects.equals(Name, "viber.com")) {
                Picasso.with(c).load(R.drawable.viber_back).into(imageApp);
                Picasso.with(c).load(R.drawable.viber_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp6));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp6));
            } else if (Objects.equals(Name, "admob")) {
                Picasso.with(c).load(R.drawable.admob_back).into(imageApp);
                Picasso.with(c).load(R.drawable.admob_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp7));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp7));
            } else if (Objects.equals(Name, "Admob")) {
                Picasso.with(c).load(R.drawable.admob_back).into(imageApp);
                Picasso.with(c).load(R.drawable.admob_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp7));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp7));
            } else if (Objects.equals(Name, "https://www.google.com/admob/")) {
                Picasso.with(c).load(R.drawable.admob_back).into(imageApp);
                Picasso.with(c).load(R.drawable.admob_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp7));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp7));
            } else if (Objects.equals(Name, "https://www.google.com/admob")) {
                Picasso.with(c).load(R.drawable.admob_back).into(imageApp);
                Picasso.with(c).load(R.drawable.admob_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp7));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp7));
            } else if (Objects.equals(Name, "www.google.com/admob")) {
                Picasso.with(c).load(R.drawable.admob_back).into(imageApp);
                Picasso.with(c).load(R.drawable.admob_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp7));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp7));
            } else if (Objects.equals(Name, "google.com/admob")) {
                Picasso.with(c).load(R.drawable.admob_back).into(imageApp);
                Picasso.with(c).load(R.drawable.admob_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp7));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp7));
            } else if (Objects.equals(Name, "https://developers.google.com/admob")) {
                Picasso.with(c).load(R.drawable.admob_back).into(imageApp);
                Picasso.with(c).load(R.drawable.admob_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp7));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp7));
            } else if (Objects.equals(Name, "developers.google.com/admob")) {
                Picasso.with(c).load(R.drawable.admob_back).into(imageApp);
                Picasso.with(c).load(R.drawable.admob_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp7));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp7));
            } else if (Objects.equals(Name, "https://apps.admob.com/signup")) {
                Picasso.with(c).load(R.drawable.admob_back).into(imageApp);
                Picasso.with(c).load(R.drawable.admob_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp7));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp7));
            } else if (Objects.equals(Name, "apps.admob.com/signup")) {
                Picasso.with(c).load(R.drawable.admob_back).into(imageApp);
                Picasso.with(c).load(R.drawable.admob_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp7));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp7));
            } else if (Objects.equals(Name, "tango")) {
                Picasso.with(c).load(R.drawable.tango_back).into(imageApp);
                Picasso.with(c).load(R.drawable.tango_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp8));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp8));
            } else if (Objects.equals(Name, "https://www.tango.me")) {
                Picasso.with(c).load(R.drawable.tango_back).into(imageApp);
                Picasso.with(c).load(R.drawable.tango_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp8));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp8));
            } else if (Objects.equals(Name, "www.tango.me")) {
                Picasso.with(c).load(R.drawable.tango_back).into(imageApp);
                Picasso.with(c).load(R.drawable.tango_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp8));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp8));
            } else if (Objects.equals(Name, "tango.me")) {
                Picasso.with(c).load(R.drawable.tango_back).into(imageApp);
                Picasso.with(c).load(R.drawable.tango_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp8));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp8));
            } else if (Objects.equals(Name, "telegram")) {
                Picasso.with(c).load(R.drawable.t_back).into(imageApp);
                Picasso.with(c).load(R.drawable.t_logo).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp9));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp9));
            } else if (Objects.equals(Name, "t")) {
                Picasso.with(c).load(R.drawable.t_back).into(imageApp);
                Picasso.with(c).load(R.drawable.t_logo).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp9));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp9));
            } else if (Objects.equals(Name, "Tele")) {
                Picasso.with(c).load(R.drawable.t_back).into(imageApp);
                Picasso.with(c).load(R.drawable.t_logo).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp9));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp9));
            } else if (Objects.equals(Name, "TGram")) {
                Picasso.with(c).load(R.drawable.t_back).into(imageApp);
                Picasso.with(c).load(R.drawable.t_logo).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp9));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp9));
            } else if (Objects.equals(Name, "TG")) {
                Picasso.with(c).load(R.drawable.t_back).into(imageApp);
                Picasso.with(c).load(R.drawable.t_logo).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp9));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp9));
            } else if (Objects.equals(Name, "tgram")) {
                Picasso.with(c).load(R.drawable.t_back).into(imageApp);
                Picasso.with(c).load(R.drawable.t_logo).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp9));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp9));
            } else if (Objects.equals(Name, "https://telegram.org")) {
                Picasso.with(c).load(R.drawable.t_back).into(imageApp);
                Picasso.with(c).load(R.drawable.t_logo).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp9));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp9));
            } else if (Objects.equals(Name, "telegram.org")) {
                Picasso.with(c).load(R.drawable.t_back).into(imageApp);
                Picasso.with(c).load(R.drawable.t_logo).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp9));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp9));
            } else if (Objects.equals(Name, "wordpress")) {
                Picasso.with(c).load(R.drawable.t_back).into(imageApp);
                Picasso.with(c).load(R.drawable.wordpress_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp9));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp9));
            } else if (Objects.equals(Name, "https://wordpress.com")) {
                Picasso.with(c).load(R.drawable.t_back).into(imageApp);
                Picasso.with(c).load(R.drawable.wordpress_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp9));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp9));
            } else if (Objects.equals(Name, "wordpress.com")) {
                Picasso.with(c).load(R.drawable.t_back).into(imageApp);
                Picasso.with(c).load(R.drawable.wordpress_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp9));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp9));
            } else if (Objects.equals(Name, "https://wordpress.org")) {
                Picasso.with(c).load(R.drawable.t_back).into(imageApp);
                Picasso.with(c).load(R.drawable.wordpress_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp9));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp9));
            } else if (Objects.equals(Name, "wordpress.org")) {
                Picasso.with(c).load(R.drawable.t_back).into(imageApp);
                Picasso.with(c).load(R.drawable.wordpress_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp9));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp9));
            } else if (Objects.equals(Name, "WP")) {
                Picasso.with(c).load(R.drawable.t_back).into(imageApp);
                Picasso.with(c).load(R.drawable.wordpress_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp9));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp9));
            } else if (Objects.equals(Name, "kik.")) {
                Picasso.with(c).load(R.drawable.kik_back).into(imageApp);
                Picasso.with(c).load(R.drawable.kik_logo).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp10));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp10));
            } else if (Objects.equals(Name, "kik")) {
                Picasso.with(c).load(R.drawable.kik_back).into(imageApp);
                Picasso.with(c).load(R.drawable.kik_logo).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp10));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp10));
            } else if (Objects.equals(Name, "https://www.kik.com")) {
                Picasso.with(c).load(R.drawable.kik_back).into(imageApp);
                Picasso.with(c).load(R.drawable.kik_logo).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp10));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp10));
            } else if (Objects.equals(Name, "www.kik.com")) {
                Picasso.with(c).load(R.drawable.kik_back).into(imageApp);
                Picasso.with(c).load(R.drawable.kik_logo).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp10));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp10));
            } else if (Objects.equals(Name, "kik.com")) {
                Picasso.with(c).load(R.drawable.kik_back).into(imageApp);
                Picasso.with(c).load(R.drawable.kik_logo).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp10));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp10));
            } else if (Objects.equals(Name, "line")) {
                Picasso.with(c).load(R.drawable.line_back).into(imageApp);
                Picasso.with(c).load(R.drawable.line_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp10));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp10));
            } else if (Objects.equals(Name, "https://line.me")) {
                Picasso.with(c).load(R.drawable.line_back).into(imageApp);
                Picasso.with(c).load(R.drawable.line_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp10));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp10));
            } else if (Objects.equals(Name, "line.me")) {
                Picasso.with(c).load(R.drawable.line_back).into(imageApp);
                Picasso.with(c).load(R.drawable.line_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp10));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp10));
            } else if (Objects.equals(Name, "hangouts")) {
                Picasso.with(c).load(R.drawable.hangouts_back).into(imageApp);
                Picasso.with(c).load(R.drawable.hangouts_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp11));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp11));
            } else if (Objects.equals(Name, "https://hangouts.google.com")) {
                Picasso.with(c).load(R.drawable.hangouts_back).into(imageApp);
                Picasso.with(c).load(R.drawable.hangouts_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp11));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp11));
            } else if (Objects.equals(Name, "hangouts.google.com")) {
                Picasso.with(c).load(R.drawable.hangouts_back).into(imageApp);
                Picasso.with(c).load(R.drawable.hangouts_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp11));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp11));
            } else if (Objects.equals(Name, "facebook")) {
                Picasso.with(c).load(R.drawable.facebook_back).into(imageApp);
                Picasso.with(c).load(R.drawable.facebook_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp12));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp12));
            } else if (Objects.equals(Name, "FB")) {
                Picasso.with(c).load(R.drawable.facebook_back).into(imageApp);
                Picasso.with(c).load(R.drawable.facebook_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp12));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp12));
            } else if (Objects.equals(Name, "https://www.facebook.com")) {
                Picasso.with(c).load(R.drawable.facebook_back).into(imageApp);
                Picasso.with(c).load(R.drawable.facebook_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp12));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp12));
            } else if (Objects.equals(Name, "www.facebook.com")) {
                Picasso.with(c).load(R.drawable.facebook_back).into(imageApp);
                Picasso.with(c).load(R.drawable.facebook_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp12));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp12));
            } else if (Objects.equals(Name, "facebook.com")) {
                Picasso.with(c).load(R.drawable.facebook_back).into(imageApp);
                Picasso.with(c).load(R.drawable.facebook_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp12));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp12));
            } else if (Objects.equals(Name, "facebook.com/login/")) {
                Picasso.with(c).load(R.drawable.facebook_back).into(imageApp);
                Picasso.with(c).load(R.drawable.facebook_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp12));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp12));
            } else if (Objects.equals(Name, "facebook.com/login")) {
                Picasso.with(c).load(R.drawable.facebook_back).into(imageApp);
                Picasso.with(c).load(R.drawable.facebook_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp12));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp12));
            } else if (Objects.equals(Name, "facebook messenger")) {
                Picasso.with(c).load(R.drawable.facebook_messenger_back).into(imageApp);
                Picasso.with(c).load(R.drawable.facebook_messenger_logo).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp13));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp13));
            } else if (Objects.equals(Name, "FMessenger")) {
                Picasso.with(c).load(R.drawable.facebook_messenger_back).into(imageApp);
                Picasso.with(c).load(R.drawable.facebook_messenger_logo).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp13));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp13));
            } else if (Objects.equals(Name, "fm")) {
                Picasso.with(c).load(R.drawable.facebook_messenger_back).into(imageApp);
                Picasso.with(c).load(R.drawable.facebook_messenger_logo).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp13));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp13));
            } else if (Objects.equals(Name, "https://www.messenger.com")) {
                Picasso.with(c).load(R.drawable.facebook_messenger_back).into(imageApp);
                Picasso.with(c).load(R.drawable.facebook_messenger_logo).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp13));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp13));
            } else if (Objects.equals(Name, "www.messenger.com")) {
                Picasso.with(c).load(R.drawable.facebook_messenger_back).into(imageApp);
                Picasso.with(c).load(R.drawable.facebook_messenger_logo).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp13));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp13));
            } else if (Objects.equals(Name, "messenger.com")) {
                Picasso.with(c).load(R.drawable.facebook_messenger_back).into(imageApp);
                Picasso.with(c).load(R.drawable.facebook_messenger_logo).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp13));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp13));
            } else if (Objects.equals(Name, "snapchat")) {
                Picasso.with(c).load(R.drawable.snapchat_back).into(imageApp);
                Picasso.with(c).load(R.drawable.snapchat_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp14));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp14));
            } else if (Objects.equals(Name, "Snap")) {
                Picasso.with(c).load(R.drawable.snapchat_back).into(imageApp);
                Picasso.with(c).load(R.drawable.snapchat_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp14));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp14));
            } else if (Objects.equals(Name, "SC")) {
                Picasso.with(c).load(R.drawable.snapchat_back).into(imageApp);
                Picasso.with(c).load(R.drawable.snapchat_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp14));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp14));
            } else if (Objects.equals(Name, "https://www.snapchat.com")) {
                Picasso.with(c).load(R.drawable.snapchat_back).into(imageApp);
                Picasso.with(c).load(R.drawable.snapchat_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp14));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp14));
            } else if (Objects.equals(Name, "www.snapchat.com")) {
                Picasso.with(c).load(R.drawable.snapchat_back).into(imageApp);
                Picasso.with(c).load(R.drawable.snapchat_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp14));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp14));
            } else if (Objects.equals(Name, "snapchat.com")) {
                Picasso.with(c).load(R.drawable.snapchat_back).into(imageApp);
                Picasso.with(c).load(R.drawable.snapchat_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp14));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp14));
            } else if (Objects.equals(Name, "wispi")) {
                Picasso.with(c).load(R.drawable.wipsi_back).into(imageApp);
                Picasso.with(c).load(R.drawable.wispi_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp15));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp15));
            } else if (Objects.equals(Name, "www.wispiapp.com")) {
                Picasso.with(c).load(R.drawable.wipsi_back).into(imageApp);
                Picasso.with(c).load(R.drawable.wispi_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp15));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp15));
            } else if (Objects.equals(Name, "wispiapp.com")) {
                Picasso.with(c).load(R.drawable.wipsi_back).into(imageApp);
                Picasso.with(c).load(R.drawable.wispi_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp15));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp15));
            } else if (Objects.equals(Name, "linkedin")) {
                Picasso.with(c).load(R.drawable.linkedin_back).into(imageApp);
                Picasso.with(c).load(R.drawable.linkedin_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp16));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp16));
            } else if (Objects.equals(Name, "https://www.linkedin.com")) {
                Picasso.with(c).load(R.drawable.linkedin_back).into(imageApp);
                Picasso.with(c).load(R.drawable.linkedin_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp16));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp16));
            } else if (Objects.equals(Name, "www.linkedin.com")) {
                Picasso.with(c).load(R.drawable.linkedin_back).into(imageApp);
                Picasso.with(c).load(R.drawable.linkedin_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp16));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp16));
            } else if (Objects.equals(Name, "linkedin.com")) {
                Picasso.with(c).load(R.drawable.linkedin_back).into(imageApp);
                Picasso.with(c).load(R.drawable.linkedin_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp16));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp16));
            } else if (Objects.equals(Name, "https://www.linkedin.com/uas/login")) {
                Picasso.with(c).load(R.drawable.linkedin_back).into(imageApp);
                Picasso.with(c).load(R.drawable.linkedin_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp16));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp16));
            } else if (Objects.equals(Name, "www.linkedin.com/uas/login")) {
                Picasso.with(c).load(R.drawable.linkedin_back).into(imageApp);
                Picasso.with(c).load(R.drawable.linkedin_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp16));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp16));
            } else if (Objects.equals(Name, "linkedin.com/uas/login")) {
                Picasso.with(c).load(R.drawable.linkedin_back).into(imageApp);
                Picasso.with(c).load(R.drawable.linkedin_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp16));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp16));
            } else if (Objects.equals(Name, "google plus")) {
                Picasso.with(c).load(R.drawable.snapchat_back).into(imageApp);
                Picasso.with(c).load(R.drawable.google_plus_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp17));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp17));
            } else if (Objects.equals(Name, "GPlus")) {
                Picasso.with(c).load(R.drawable.snapchat_back).into(imageApp);
                Picasso.with(c).load(R.drawable.google_plus_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp17));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp17));
            } else if (Objects.equals(Name, "https://plus.google.com")) {
                Picasso.with(c).load(R.drawable.snapchat_back).into(imageApp);
                Picasso.with(c).load(R.drawable.google_plus_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp17));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp17));
            } else if (Objects.equals(Name, "plus.google.com")) {
                Picasso.with(c).load(R.drawable.snapchat_back).into(imageApp);
                Picasso.with(c).load(R.drawable.google_plus_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp17));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp17));
            } else if (Objects.equals(Name, "google adwords")) {
                Picasso.with(c).load(R.drawable.adwords_back).into(imageApp);
                Picasso.with(c).load(R.drawable.adwords_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp18));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp18));
            } else if (Objects.equals(Name, "GAdwords")) {
                Picasso.with(c).load(R.drawable.adwords_back).into(imageApp);
                Picasso.with(c).load(R.drawable.adwords_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp18));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp18));
            } else if (Objects.equals(Name, "Adwords")) {
                Picasso.with(c).load(R.drawable.adwords_back).into(imageApp);
                Picasso.with(c).load(R.drawable.adwords_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp18));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp18));
            } else if (Objects.equals(Name, "Adword")) {
                Picasso.with(c).load(R.drawable.adwords_back).into(imageApp);
                Picasso.with(c).load(R.drawable.adwords_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp18));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp18));
            } else if (Objects.equals(Name, "https://adwords.google.com/home")) {
                Picasso.with(c).load(R.drawable.adwords_back).into(imageApp);
                Picasso.with(c).load(R.drawable.adwords_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp18));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp18));
            } else if (Objects.equals(Name, "adwords.google.com/home")) {
                Picasso.with(c).load(R.drawable.adwords_back).into(imageApp);
                Picasso.with(c).load(R.drawable.adwords_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp18));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp18));
            } else if (Objects.equals(Name, "adwords.google.com/")) {
                Picasso.with(c).load(R.drawable.adwords_back).into(imageApp);
                Picasso.with(c).load(R.drawable.adwords_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp18));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp18));
            } else if (Objects.equals(Name, "adwords.google.com")) {
                Picasso.with(c).load(R.drawable.adwords_back).into(imageApp);
                Picasso.with(c).load(R.drawable.adwords_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp18));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp18));
            } else if (Objects.equals(Name, "allo")) {
                Picasso.with(c).load(R.drawable.allo_back).into(imageApp);
                Picasso.with(c).load(R.drawable.allo_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp19));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp19));
            } else if (Objects.equals(Name, "Google allo")) {
                Picasso.with(c).load(R.drawable.allo_back).into(imageApp);
                Picasso.with(c).load(R.drawable.allo_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp19));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp19));
            } else if (Objects.equals(Name, "GA")) {
                Picasso.with(c).load(R.drawable.allo_back).into(imageApp);
                Picasso.with(c).load(R.drawable.allo_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp19));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp19));
            } else if (Objects.equals(Name, "https://allo.google.com")) {
                Picasso.with(c).load(R.drawable.allo_back).into(imageApp);
                Picasso.with(c).load(R.drawable.allo_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp19));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp19));
            } else if (Objects.equals(Name, "allo.google.com")) {
                Picasso.with(c).load(R.drawable.allo_back).into(imageApp);
                Picasso.with(c).load(R.drawable.allo_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp19));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp19));
            } else if (Objects.equals(Name, "netflix")) {
                Picasso.with(c).load(R.drawable.n_back).into(imageApp);
                Picasso.with(c).load(R.drawable.n_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp20));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp20));
            } else if (Objects.equals(Name, "https://www.netflix.com")) {
                Picasso.with(c).load(R.drawable.n_back).into(imageApp);
                Picasso.with(c).load(R.drawable.n_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp20));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp20));
            } else if (Objects.equals(Name, "www.netflix.com")) {
                Picasso.with(c).load(R.drawable.n_back).into(imageApp);
                Picasso.with(c).load(R.drawable.n_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp20));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp20));
            } else if (Objects.equals(Name, "netflix.com")) {
                Picasso.with(c).load(R.drawable.n_back).into(imageApp);
                Picasso.with(c).load(R.drawable.n_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp20));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp20));
            } else if (Objects.equals(Name, "blogger")) {
                Picasso.with(c).load(R.drawable.blogger_back).into(imageApp);
                Picasso.with(c).load(R.drawable.blogger_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp21));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp21));
            } else if (Objects.equals(Name, "https://www.blogger.com")) {
                Picasso.with(c).load(R.drawable.blogger_back).into(imageApp);
                Picasso.with(c).load(R.drawable.blogger_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp21));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp21));
            } else if (Objects.equals(Name, "www.blogger.com")) {
                Picasso.with(c).load(R.drawable.blogger_back).into(imageApp);
                Picasso.with(c).load(R.drawable.blogger_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp21));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp21));
            } else if (Objects.equals(Name, "blogger.com")) {
                Picasso.with(c).load(R.drawable.blogger_back).into(imageApp);
                Picasso.with(c).load(R.drawable.blogger_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp21));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp21));
            } else if (Objects.equals(Name, "amazon")) {
                Picasso.with(c).load(R.drawable.amazon_back).into(imageApp);
                Picasso.with(c).load(R.drawable.amazon_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp22));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp22));
            } else if (Objects.equals(Name, "Amazon")) {
                Picasso.with(c).load(R.drawable.amazon_back).into(imageApp);
                Picasso.with(c).load(R.drawable.amazon_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp22));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp22));
            } else if (Objects.equals(Name, "https://www.amazon.com")) {
                Picasso.with(c).load(R.drawable.amazon_back).into(imageApp);
                Picasso.with(c).load(R.drawable.amazon_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp22));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp22));
            } else if (Objects.equals(Name, "www.amazon.com")) {
                Picasso.with(c).load(R.drawable.amazon_back).into(imageApp);
                Picasso.with(c).load(R.drawable.amazon_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp22));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp22));
            } else if (Objects.equals(Name, "amazon.com")) {
                Picasso.with(c).load(R.drawable.amazon_back).into(imageApp);
                Picasso.with(c).load(R.drawable.amazon_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp22));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp22));
            } else if (Objects.equals(Name, "twitter")) {
                Picasso.with(c).load(R.drawable.amazon_back).into(imageApp);
                Picasso.with(c).load(R.drawable.twitter_back).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp13));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp13));
            } else if (Objects.equals(Name, "Twitter")) {
                Picasso.with(c).load(R.drawable.amazon_back).into(imageApp);
                Picasso.with(c).load(R.drawable.twitter_back).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp13));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp13));
            } else if (Objects.equals(Name, "Twit")) {
                Picasso.with(c).load(R.drawable.amazon_back).into(imageApp);
                Picasso.with(c).load(R.drawable.twitter_back).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp13));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp13));
            } else if (Objects.equals(Name, "https://twitter.com")) {
                Picasso.with(c).load(R.drawable.amazon_back).into(imageApp);
                Picasso.with(c).load(R.drawable.twitter_back).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp13));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp13));
            } else if (Objects.equals(Name, "twitter.com")) {
                Picasso.with(c).load(R.drawable.amazon_back).into(imageApp);
                Picasso.with(c).load(R.drawable.twitter_back).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp13));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp13));
            } else if (Objects.equals(Name, "https://twitter.com/login")) {
                Picasso.with(c).load(R.drawable.amazon_back).into(imageApp);
                Picasso.with(c).load(R.drawable.twitter_back).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp13));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp13));
            } else if (Objects.equals(Name, "twitter.com/login")) {
                Picasso.with(c).load(R.drawable.amazon_back).into(imageApp);
                Picasso.with(c).load(R.drawable.twitter_back).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp13));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp13));
            } else if (Objects.equals(Name, "persian stack")) {
                Picasso.with(c).load(R.drawable.persian_stack_back).into(imageApp);
                Picasso.with(c).load(R.drawable.persian_stack_back).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp23));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp23));
            } else if (Objects.equals(Name, "persianstack")) {
                Picasso.with(c).load(R.drawable.persian_stack_back).into(imageApp);
                Picasso.with(c).load(R.drawable.persian_stack_back).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp23));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp23));
            } else if (Objects.equals(Name, "persianstacks")) {
                Picasso.with(c).load(R.drawable.persian_stack_back).into(imageApp);
                Picasso.with(c).load(R.drawable.persian_stack_back).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp23));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp23));
            } else if (Objects.equals(Name, "persian stacks")) {
                Picasso.with(c).load(R.drawable.persian_stack_back).into(imageApp);
                Picasso.with(c).load(R.drawable.persian_stack_back).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp23));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp23));
            } else if (Objects.equals(Name, "pstacks")) {
                Picasso.with(c).load(R.drawable.persian_stack_back).into(imageApp);
                Picasso.with(c).load(R.drawable.persian_stack_back).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp23));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp23));
            } else if (Objects.equals(Name, "pstack")) {
                Picasso.with(c).load(R.drawable.persian_stack_back).into(imageApp);
                Picasso.with(c).load(R.drawable.persian_stack_back).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp23));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp23));
            } else if (Objects.equals(Name, "ps")) {
                Picasso.with(c).load(R.drawable.persian_stack_back).into(imageApp);
                Picasso.with(c).load(R.drawable.persian_stack_back).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp23));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp23));
            } else if (Objects.equals(Name, "persian stacks")) {
                Picasso.with(c).load(R.drawable.persian_stack_back).into(imageApp);
                Picasso.with(c).load(R.drawable.persian_stack_back).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp23));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp23));
            } else if (Objects.equals(Name, "Persian Stack")) {
                Picasso.with(c).load(R.drawable.persian_stack_back).into(imageApp);
                Picasso.with(c).load(R.drawable.persian_stack_back).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp23));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp23));
            } else if (Objects.equals(Name, "http://www.persianstacks.ir/")) {
                Picasso.with(c).load(R.drawable.persian_stack_back).into(imageApp);
                Picasso.with(c).load(R.drawable.persian_stack_back).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp23));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp23));
            } else if (Objects.equals(Name, "http://www.persianstacks.ir")) {
                Picasso.with(c).load(R.drawable.persian_stack_back).into(imageApp);
                Picasso.with(c).load(R.drawable.persian_stack_back).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp23));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp23));
            } else if (Objects.equals(Name, "http://www.persianstacks.com")) {
                Picasso.with(c).load(R.drawable.persian_stack_back).into(imageApp);
                Picasso.with(c).load(R.drawable.persian_stack_back).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp23));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp23));
            } else if (Objects.equals(Name, "www.persianstacks.ir/")) {
                Picasso.with(c).load(R.drawable.persian_stack_back).into(imageApp);
                Picasso.with(c).load(R.drawable.persian_stack_back).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp23));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp23));
            } else if (Objects.equals(Name, "www.persianstacks.ir")) {
                Picasso.with(c).load(R.drawable.persian_stack_back).into(imageApp);
                Picasso.with(c).load(R.drawable.persian_stack_back).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp23));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp23));
            } else if (Objects.equals(Name, "www.persianstacks.com")) {
                Picasso.with(c).load(R.drawable.persian_stack_back).into(imageApp);
                Picasso.with(c).load(R.drawable.persian_stack_back).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp23));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp23));
            } else if (Objects.equals(Name, "persianstacks.ir")) {
                Picasso.with(c).load(R.drawable.persian_stack_back).into(imageApp);
                Picasso.with(c).load(R.drawable.persian_stack_back).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp23));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp23));
            } else if (Objects.equals(Name, "persianstacks.com")) {
                Picasso.with(c).load(R.drawable.persian_stack_back).into(imageApp);
                Picasso.with(c).load(R.drawable.persian_stack_back).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp23));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp23));
            } else if (Objects.equals(Name, "alibaba")) {
                Picasso.with(c).load(R.drawable.alibaba_back).into(imageApp);
                Picasso.with(c).load(R.drawable.alibaba_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp24));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp24));
            } else if (Objects.equals(Name, "www.alibaba.com")) {
                Picasso.with(c).load(R.drawable.alibaba_back).into(imageApp);
                Picasso.with(c).load(R.drawable.alibaba_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp24));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp24));
            } else if (Objects.equals(Name, "alibaba.com")) {
                Picasso.with(c).load(R.drawable.alibaba_back).into(imageApp);
                Picasso.with(c).load(R.drawable.alibaba_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp24));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp24));
            } else if (Objects.equals(Name, "Alibaba")) {
                Picasso.with(c).load(R.drawable.alibaba_back).into(imageApp);
                Picasso.with(c).load(R.drawable.alibaba_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp24));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp24));
            } else if (Objects.equals(Name, "tumblr")) {
                Picasso.with(c).load(R.drawable.tumber_back).into(imageApp);
                Picasso.with(c).load(R.drawable.tumber_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp25));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp25));
            } else if (Objects.equals(Name, "https://www.tumblr.com")) {
                Picasso.with(c).load(R.drawable.tumber_back).into(imageApp);
                Picasso.with(c).load(R.drawable.tumber_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp25));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp25));
            } else if (Objects.equals(Name, "www.tumblr.com")) {
                Picasso.with(c).load(R.drawable.tumber_back).into(imageApp);
                Picasso.with(c).load(R.drawable.tumber_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp25));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp25));
            } else if (Objects.equals(Name, "tumblr.com")) {
                Picasso.with(c).load(R.drawable.tumber_back).into(imageApp);
                Picasso.with(c).load(R.drawable.tumber_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp25));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp25));
            } else if (Objects.equals(Name, "https://www.tumblr.com/login")) {
                Picasso.with(c).load(R.drawable.tumber_back).into(imageApp);
                Picasso.with(c).load(R.drawable.tumber_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp25));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp25));
            } else if (Objects.equals(Name, "www.tumblr.com/login")) {
                Picasso.with(c).load(R.drawable.tumber_back).into(imageApp);
                Picasso.with(c).load(R.drawable.tumber_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp25));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp25));
            } else if (Objects.equals(Name, "tumblr.com/login")) {
                Picasso.with(c).load(R.drawable.tumber_back).into(imageApp);
                Picasso.with(c).load(R.drawable.tumber_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp25));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp25));
            } else if (Objects.equals(Name, "github")) {
                Picasso.with(c).load(R.drawable.github_back).into(imageApp);
                Picasso.with(c).load(R.drawable.github_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp26));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp26));
            } else if (Objects.equals(Name, "GHub")) {
                Picasso.with(c).load(R.drawable.github_back).into(imageApp);
                Picasso.with(c).load(R.drawable.github_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp26));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp26));
            } else if (Objects.equals(Name, "https://github.com")) {
                Picasso.with(c).load(R.drawable.github_back).into(imageApp);
                Picasso.with(c).load(R.drawable.github_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp26));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp26));
            } else if (Objects.equals(Name, "github.com")) {
                Picasso.with(c).load(R.drawable.github_back).into(imageApp);
                Picasso.with(c).load(R.drawable.github_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.colorapp26));
                Password2.setTextColor(c.getResources().getColor(R.color.colorapp26));
            } else if (Objects.equals(Name, "ebay")) {
                Picasso.with(c).load(R.drawable.ebay_back).into(imageApp);
                Picasso.with(c).load(R.drawable.ebay_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.gray2));
                Password2.setTextColor(c.getResources().getColor(R.color.gray2));
            } else if (Objects.equals(Name, "https://www.ebay.com")) {
                Picasso.with(c).load(R.drawable.ebay_back).into(imageApp);
                Picasso.with(c).load(R.drawable.ebay_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.gray2));
                Password2.setTextColor(c.getResources().getColor(R.color.gray2));
            } else if (Objects.equals(Name, "www.ebay.com")) {
                Picasso.with(c).load(R.drawable.ebay_back).into(imageApp);
                Picasso.with(c).load(R.drawable.ebay_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.gray2));
                Password2.setTextColor(c.getResources().getColor(R.color.gray2));
            } else if (Objects.equals(Name, "ebay.com")) {
                Picasso.with(c).load(R.drawable.ebay_back).into(imageApp);
                Picasso.with(c).load(R.drawable.ebay_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.gray2));
                Password2.setTextColor(c.getResources().getColor(R.color.gray2));
            } else if (Objects.equals(Name, "uber")) {
                Picasso.with(c).load(R.drawable.ebay_back).into(imageApp);
                Picasso.with(c).load(R.drawable.uber_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.gray2));
                Password2.setTextColor(c.getResources().getColor(R.color.gray2));
            } else if (Objects.equals(Name, "https://www.uber.com")) {
                Picasso.with(c).load(R.drawable.ebay_back).into(imageApp);
                Picasso.with(c).load(R.drawable.uber_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.gray2));
                Password2.setTextColor(c.getResources().getColor(R.color.gray2));
            } else if (Objects.equals(Name, "www.uber.com")) {
                Picasso.with(c).load(R.drawable.ebay_back).into(imageApp);
                Picasso.with(c).load(R.drawable.uber_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.gray2));
                Password2.setTextColor(c.getResources().getColor(R.color.gray2));
            } else if (Objects.equals(Name, "uber.com")) {
                Picasso.with(c).load(R.drawable.ebay_back).into(imageApp);
                Picasso.with(c).load(R.drawable.uber_icon).into(icon);
                textapp.setVisibility(View.INVISIBLE);
                User2.setTextColor(c.getResources().getColor(R.color.gray2));
                Password2.setTextColor(c.getResources().getColor(R.color.gray2));
            } else {
                if (back == 1) {
                    final int sdk = Build.VERSION.SDK_INT;
                    if (sdk < Build.VERSION_CODES.JELLY_BEAN){
                        back_gr.setBackgroundDrawable(c.getResources().getDrawable(R.drawable.web1));
                    }else {
                        back_gr.setBackground(c.getResources().getDrawable(R.drawable.web1));
                    }
                    User2.setTextColor(c.getResources().getColor(R.color.colorweb1));
                    Password2.setTextColor(c.getResources().getColor(R.color.colorweb1));
                } else if (back == 2) {
                    final int sdk = Build.VERSION.SDK_INT;
                    if (sdk < Build.VERSION_CODES.JELLY_BEAN){
                        back_gr.setBackgroundDrawable(c.getResources().getDrawable(R.drawable.web2));
                    }else {
                        back_gr.setBackground(c.getResources().getDrawable(R.drawable.web2));
                    }
                    User2.setTextColor(c.getResources().getColor(R.color.colorweb2));
                    Password2.setTextColor(c.getResources().getColor(R.color.colorweb2));
                } else if (back == 3) {
                    final int sdk = Build.VERSION.SDK_INT;
                    if (sdk < Build.VERSION_CODES.JELLY_BEAN){
                        back_gr.setBackgroundDrawable(c.getResources().getDrawable(R.drawable.web3));
                    }else {
                        back_gr.setBackground(c.getResources().getDrawable(R.drawable.web3));
                    }
                    User2.setTextColor(c.getResources().getColor(R.color.colorweb3));
                    Password2.setTextColor(c.getResources().getColor(R.color.colorweb3));
                } else if (back == 4) {
                    final int sdk = Build.VERSION.SDK_INT;
                    if (sdk < Build.VERSION_CODES.JELLY_BEAN){
                        back_gr.setBackgroundDrawable(c.getResources().getDrawable(R.drawable.web4));
                    }else {
                        back_gr.setBackground(c.getResources().getDrawable(R.drawable.web4));
                    }
                    User2.setTextColor(c.getResources().getColor(R.color.colorweb4));
                    Password2.setTextColor(c.getResources().getColor(R.color.colorweb4));
                } else if (back == 5) {
                    final int sdk = Build.VERSION.SDK_INT;
                    if (sdk < Build.VERSION_CODES.JELLY_BEAN){
                        back_gr.setBackgroundDrawable(c.getResources().getDrawable(R.drawable.web5));
                    }else {
                        back_gr.setBackground(c.getResources().getDrawable(R.drawable.web5));
                    }
                    User2.setTextColor(c.getResources().getColor(R.color.colorweb5));
                    Password2.setTextColor(c.getResources().getColor(R.color.colorweb5));
                } else if (back == 6) {
                    final int sdk = Build.VERSION.SDK_INT;
                    if (sdk < Build.VERSION_CODES.JELLY_BEAN){
                        back_gr.setBackgroundDrawable(c.getResources().getDrawable(R.drawable.web2));
                    }else {
                        back_gr.setBackground(c.getResources().getDrawable(R.drawable.web2));
                    }
                    User2.setTextColor(c.getResources().getColor(R.color.colorweb2));
                    Password2.setTextColor(c.getResources().getColor(R.color.colorweb2));
                } else if (back == 7) {
                    final int sdk = Build.VERSION.SDK_INT;
                    if (sdk < Build.VERSION_CODES.JELLY_BEAN){
                        back_gr.setBackgroundDrawable(c.getResources().getDrawable(R.drawable.web4));
                    }else {
                        back_gr.setBackground(c.getResources().getDrawable(R.drawable.web4));
                    }
                    User2.setTextColor(c.getResources().getColor(R.color.colorweb4));
                    Password2.setTextColor(c.getResources().getColor(R.color.colorweb4));
                }
                imageApp.setVisibility(View.INVISIBLE);
                icon.setVisibility(View.INVISIBLE);
                textapp.setVisibility(View.INVISIBLE);
                website.setVisibility(View.VISIBLE);
                website.setText(Name);
            }

        }

        User2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboard = (ClipboardManager) c.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("User", User2.getText().toString());
                clipboard.setPrimaryClip(clip);
                Toasty.success(c, "Username Copied", Toast.LENGTH_SHORT, true).show();
            }
        });

        Password2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboard = (ClipboardManager) c.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("password", Password2.getText().toString());
                clipboard.setPrimaryClip(clip);
                Toasty.success(c, "Password Copied", Toast.LENGTH_SHORT, true).show();
            }
        });

        website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = website.getText().toString();
                if (!url.startsWith("http://") && !url.startsWith("https://"))
                    url = "http://" + url;
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                c.startActivity(browserIntent);
            }
        });

        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                (new DialogDetailWebApp(c, User2.getText().toString(), Password2.getText().toString(), textapp.getText().toString(), textbk6.getText().toString(), textbk7.getText().toString(), textbk2.getText().toString(), textbk3.getText().toString(), textbk4.getText().toString())).showDialog();

                return false;
            }
        });

        return view;
    }
}
