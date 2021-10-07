package com.fury.myaccounts.data;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fury.myaccounts.DialogDetailEmail;
import com.fury.myaccounts.R;
import com.scottyab.aescrypt.AESCrypt;

import java.security.GeneralSecurityException;
import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

/**
 * Created by fury on 9/14/2017.
 */
public class CustomAdapter2 extends BaseAdapter {

    Context c;
    ArrayList<Spacecraft2> spacecrafts;

    SharedPreferences one_play_preferences;
    SharedPreferences.Editor one_play_editor;
    String PasswordAll, User, Birthday, name, Password , Mobile, siteEmail;
    int email2;

    public CustomAdapter2(Context c, ArrayList<Spacecraft2> spacecrafts){
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
        if (view == null){
            view = LayoutInflater.from(c).inflate(R.layout.email,viewGroup,false);
        }

        one_play_preferences = c.getSharedPreferences("PROJECT_NAME", Context.MODE_PRIVATE);
        one_play_editor = one_play_preferences.edit();
        PasswordAll = one_play_preferences.getString("PFURY", "");

        ImageView imagecard = (ImageView)view.findViewById(R.id.imagecard);
        ImageView emailCard = (ImageView)view.findViewById(R.id.emailCard);
        final TextView Email = (TextView) view.findViewById(R.id.Email);
        final TextView passEmail = (TextView) view.findViewById(R.id.passEmail);
        final TextView textbk2 = (TextView) view.findViewById(R.id.textbk2);
        final TextView textbk3 = (TextView) view.findViewById(R.id.textbk3);
        final TextView textbk4 = (TextView) view.findViewById(R.id.textbk4);
        final TextView textbk5 = (TextView) view.findViewById(R.id.textbk5);
        final TextView textbk6 = (TextView) view.findViewById(R.id.textbk6);
        final TextView textbk7 = (TextView) view.findViewById(R.id.textbk7);
        final TextView textbk8 = (TextView) view.findViewById(R.id.textbk8);
        final TextView textbk9 = (TextView) view.findViewById(R.id.textbk9);
        Spacecraft2 s = (Spacecraft2)this.getItem(i);


        if (s.getUser().length()>0 && s.getUser() != null){
            try {
                User = AESCrypt.decrypt(PasswordAll, s.getUser());
            }catch (GeneralSecurityException e){
                //handle error - could be due to incorrect password or tampered encryptedMsg
            }
        }else {
            User = "";
        }

        if (s.getBirthday().length()>0 && s.getBirthday() != null){
            try {
                Birthday = AESCrypt.decrypt(PasswordAll, s.getBirthday());
            }catch (GeneralSecurityException e){
                //handle error - could be due to incorrect password or tampered encryptedMsg
            }
        }else {
            Birthday = "";
        }

        if (s.getName().length()>0 && s.getName() != null){
            try {
                name = AESCrypt.decrypt(PasswordAll, s.getName());
            }catch (GeneralSecurityException e){
                //handle error - could be due to incorrect password or tampered encryptedMsg
            }
        }else {
            name = "";
        }

        if (s.getPassword().length()>0 && s.getPassword() != null){
            try {
                Password = AESCrypt.decrypt(PasswordAll, s.getPassword());
            }catch (GeneralSecurityException e){
                //handle error - could be due to incorrect password or tampered encryptedMsg
            }
        }else {
            Password = "";
        }

        if (s.getMobile().length()>0 && s.getMobile() != null){
            try {
                Mobile = AESCrypt.decrypt(PasswordAll, s.getMobile());
            }catch (GeneralSecurityException e){
                //handle error - could be due to incorrect password or tampered encryptedMsg
            }
        }else {
            Mobile = "";
        }

        if (s.getSiteEmail().length()>0 && s.getSiteEmail() != null){
            try {
                siteEmail = AESCrypt.decrypt(PasswordAll, s.getSiteEmail());
            }catch (GeneralSecurityException e){
                //handle error - could be due to incorrect password or tampered encryptedMsg
            }
        }else {
            siteEmail = "";
        }


        int back = s.getBack();
        email2 = s.getEmail();
        String bn = String.valueOf(email2);
        String ba = String.valueOf(back);

        Email.setText(User);
        passEmail.setText(Password);
        textbk2.setText(Birthday);
        textbk3.setText(bn);
        textbk4.setText(ba);
        textbk5.setText(name);
        textbk6.setText(Mobile);
        textbk7.setText(siteEmail);

        Typeface face = Typeface.createFromAsset(c.getAssets(), "Yantramanav-Light.ttf");

        Email.setTypeface(face);
        passEmail.setTypeface(face);
        textbk8.setTypeface(face);
        textbk9.setTypeface(face);

        if (back == 1){
            imagecard.setImageResource(R.drawable.emailcard1);
        }else if (back == 2){
            imagecard.setImageResource(R.drawable.emailcard2);
        }else if (back == 3){
            imagecard.setImageResource(R.drawable.emailcard3);
            textbk8.setTextColor(c.getResources().getColor(R.color.gray));
            textbk9.setTextColor(c.getResources().getColor(R.color.gray));
            Email.setTextColor(c.getResources().getColor(R.color.gray));
            passEmail.setTextColor(c.getResources().getColor(R.color.gray));
        }

        if (email2 == 0){
            emailCard.setVisibility(View.GONE);
        }else if (email2 == 1){
            emailCard.setImageResource(R.drawable.gmail);
        }else if (email2 == 2){
            emailCard.setImageResource(R.drawable.yahoo);
        }else if (email2 == 3){
            emailCard.setImageResource(R.drawable.hotmail);
        }else if (email2 == 4){
            emailCard.setImageResource(R.drawable.outlook);
        }else if (email2 == 5){
            emailCard.setImageResource(R.drawable.icloud);
        }else if (email2 == 6){
            emailCard.setImageResource(R.drawable.gmx);
        }else if (email2 == 7){
            emailCard.setImageResource(R.drawable.zoho);
        }else if (email2 == 8){
            emailCard.setImageResource(R.drawable.yandex);
        }else if (email2 == 9){
            emailCard.setImageResource(R.drawable.nextcloud);
        }else if (email2 == 10){
            emailCard.setImageResource(R.drawable.cpanel);
        }else if (email2 == 11){
            emailCard.setImageResource(R.drawable.aol);
        }else if (email2 == 12){
            emailCard.setVisibility(View.GONE);
        }

        Email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboard = (ClipboardManager) c.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Email", Email.getText().toString());
                clipboard.setPrimaryClip(clip);
                Toasty.success(c, "Email Copied" , Toast.LENGTH_SHORT, true).show();
            }
        });

        passEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboard = (ClipboardManager) c.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("password", passEmail.getText().toString());
                clipboard.setPrimaryClip(clip);
                Toasty.success(c, "Password Copied", Toast.LENGTH_SHORT, true).show();
            }
        });

        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                (new DialogDetailEmail(c,Email.getText().toString(),passEmail.getText().toString(),textbk2.getText().toString(),textbk5.getText().toString(),textbk6.getText().toString(),textbk7.getText().toString(),textbk3.getText().toString(),textbk4.getText().toString())).showDialog();
                return false;
            }
        });

        return view;
    }
}
