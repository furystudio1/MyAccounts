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

import com.fury.myaccounts.DialogDetailCard;
import com.fury.myaccounts.R;
import com.scottyab.aescrypt.AESCrypt;

import java.security.GeneralSecurityException;
import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

/**
 * Created by fury on 9/14/2017.
 */
public class CustomAdapter extends BaseAdapter {

    Context c;
    ArrayList<Spacecraft> spacecrafts;

    SharedPreferences one_play_preferences;
    SharedPreferences.Editor one_play_editor;
    String PasswordAll, number, ccv,name, pass ,email ,date;
    int bank;

    public CustomAdapter(Context c,ArrayList<Spacecraft> spacecrafts){
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
            view = LayoutInflater.from(c).inflate(R.layout.card,viewGroup,false);
        }

        one_play_preferences = c.getSharedPreferences("PROJECT_NAME", android.content.Context.MODE_PRIVATE);
        one_play_editor = one_play_preferences.edit();
        PasswordAll = one_play_preferences.getString("PFURY", "");

        ImageView imagecard = (ImageView)view.findViewById(R.id.imagecard);
        ImageView bankCard = (ImageView)view.findViewById(R.id.bankCard);
        final TextView numberCard = (TextView) view.findViewById(R.id.numberCard);
        final TextView textbk1 = (TextView) view.findViewById(R.id.textbk1);
        final TextView textbk2 = (TextView) view.findViewById(R.id.textbk2);
        final TextView textbk3 = (TextView) view.findViewById(R.id.textbk3);
        final TextView textbk4 = (TextView) view.findViewById(R.id.textbk4);
        final TextView font1 = (TextView) view.findViewById(R.id.font1);
        final TextView font2 = (TextView) view.findViewById(R.id.numberCCVCard2);
        final TextView numberDateCard = (TextView) view.findViewById(R.id.numberDateCard);
        final TextView numberCCVCard = (TextView) view.findViewById(R.id.numberCCVCard);
        final TextView NameCard = (TextView) view.findViewById(R.id.NameCard);
        Spacecraft s = (Spacecraft)this.getItem(i);


        if (s.getNumberCard().length()>0 && s.getNumberCard() != null){
            try {
                number = AESCrypt.decrypt(PasswordAll, s.getNumberCard());
            }catch (GeneralSecurityException e){
                //handle error - could be due to incorrect password or tampered encryptedMsg
            }
        }else {
            number = "";
        }

        if (s.getDate().length()>0 && s.getDate() != null){
            try {
                date = AESCrypt.decrypt(PasswordAll, s.getDate());
            }catch (GeneralSecurityException e){
                //handle error - could be due to incorrect password or tampered encryptedMsg
            }
        }else {
            date = "";
        }

        if (s.getCCV().length()>0 && s.getCCV() != null){
            try {
                ccv = AESCrypt.decrypt(PasswordAll, s.getCCV());
            }catch (GeneralSecurityException e){
                //handle error - could be due to incorrect password or tampered encryptedMsg
            }
        }else {
            ccv = "";
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

        if (s.getPass().length()>0 && s.getPass() != null){
            try {
                pass = AESCrypt.decrypt(PasswordAll, s.getPass());
            }catch (GeneralSecurityException e){
                //handle error - could be due to incorrect password or tampered encryptedMsg
            }
        }else {
            pass = "";
        }

        if (s.getEmail().length()>0 && s.getEmail() != null){
            try {
                email = AESCrypt.decrypt(PasswordAll, s.getEmail());
            }catch (GeneralSecurityException e){
                //handle error - could be due to incorrect password or tampered encryptedMsg
            }
        }else {
            email = "";
        }


        int back = s.getBack();
        bank = s.getBank();
        String bn = String.valueOf(bank);
        String ba = String.valueOf(back);

        numberCard.setText(number);
        textbk1.setText(email);
        textbk2.setText(pass);
        textbk3.setText(bn);
        textbk4.setText(ba);
        numberDateCard.setText(date);
        numberCCVCard.setText(ccv);
        NameCard.setText(name);

        Typeface face = Typeface.createFromAsset(c.getAssets(), "kredit_back.ttf");
        Typeface face2 = Typeface.createFromAsset(c.getAssets(), "kredit_back.ttf");

        numberCard.setTypeface(face2);
        numberDateCard.setTypeface(face);
        numberCCVCard.setTypeface(face);
        NameCard.setTypeface(face2);
        font1.setTypeface(face);
        font2.setTypeface(face);

        if (back == 1){
            imagecard.setImageResource(R.drawable.cardview1);
        }else if (back == 2){
            imagecard.setImageResource(R.drawable.cardview2);
        }else if (back == 3){
            imagecard.setImageResource(R.drawable.cardview3);
        }else if (back == 4){
            imagecard.setImageResource(R.drawable.cardview4);
        }else if (back == 5){
            imagecard.setImageResource(R.drawable.cardview5);
        }else if (back == 6){
            imagecard.setImageResource(R.drawable.cardview6);
        }

        if (bank == 0){
            bankCard.setVisibility(View.GONE);
        }else if (bank == 1){
            bankCard.setImageResource(R.drawable.visa);
        }else if (bank == 2){
            bankCard.setImageResource(R.drawable.mastercard);
        }else if (bank == 3){
            bankCard.setImageResource(R.drawable.american_express);
        }else if (bank == 4){
            bankCard.setImageResource(R.drawable.discover);
        }else if (bank == 5){
            bankCard.setImageResource(R.drawable.paypal);
        }else if (bank == 6){
            bankCard.setImageResource(R.drawable.maestro);
        }else if (bank == 7){
            bankCard.setImageResource(R.drawable.solo);
        }else if (bank == 8){
            bankCard.setImageResource(R.drawable.diners);
        }else if (bank == 9){
            bankCard.setVisibility(View.GONE);
        }

        numberCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboard = (ClipboardManager) c.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("NumberCard", numberCard.getText().toString());
                clipboard.setPrimaryClip(clip);
                Toasty.success(c, "Card Number Copied" , Toast.LENGTH_SHORT, true).show();
            }
        });

        numberCCVCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboard = (ClipboardManager) c.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("CCV", numberCCVCard.getText().toString());
                clipboard.setPrimaryClip(clip);
                Toasty.success(c, "CCV/CVC Copied", Toast.LENGTH_SHORT, true).show();
            }
        });

        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                (new DialogDetailCard(c,NameCard.getText().toString(),numberCard.getText().toString(),numberDateCard.getText().toString(),numberCCVCard.getText().toString(),textbk1.getText().toString(),textbk2.getText().toString(),textbk3.getText().toString(),textbk4.getText().toString())).showDialog();
                return false;
            }
        });

        return view;
    }
}
