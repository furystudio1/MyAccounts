package com.fury.myaccounts;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.scottyab.aescrypt.AESCrypt;

import java.security.GeneralSecurityException;

import es.dmoral.toasty.Toasty;

/**
 * Created by fury on 9/10/2017.
 */
public class NewPassword extends Activity {

    SharedPreferences one_play_preferences;
    SharedPreferences.Editor one_play_editor;
    String encryptedMsgPas,encryptedMsgEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newpassword);

        one_play_preferences = getApplicationContext().getSharedPreferences("PROJECT_NAME", android.content.Context.MODE_PRIVATE);
        one_play_editor = one_play_preferences.edit();
        one_play_editor.putString("PFURY", "217218217218");
        one_play_editor.apply();

        final EditText ET_newpassword = (EditText)findViewById(R.id.ET_newpassword);
        final EditText ET_Email = (EditText)findViewById(R.id.ET_Email);
        Button set = (Button) findViewById(R.id.set);

        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = String.valueOf(ET_Email.getText());
                String pas = String.valueOf(ET_newpassword.getText());
                if (pas.length() > 0){
                    if (pas.length() >= 6){
                        if (email.length() > 0){
                            String password = "217218217218";
                            try {
                                encryptedMsgEmail = AESCrypt.encrypt(password, email);
                            }catch (GeneralSecurityException e){
                                Toasty.error(NewPassword.this, (CharSequence) e, Toast.LENGTH_SHORT, true).show();
                            }
                            try {
                                encryptedMsgPas = AESCrypt.encrypt(password, pas);
                            }catch (GeneralSecurityException e){
                                Toasty.error(NewPassword.this, (CharSequence) e, Toast.LENGTH_SHORT, true).show();
                            }
                            one_play_editor.putString("EMAIL_forgot", encryptedMsgEmail);
                            one_play_editor.putString("passwordCode", encryptedMsgPas);
                            one_play_editor.putBoolean("password", true);
                            one_play_editor.putBoolean("one", false);
                            one_play_editor.commit();
                            Intent one = new Intent(NewPassword.this,Password_1.class);
                            startActivity(one);
                            finish();
                        }else {
                            Toasty.error(NewPassword.this, "Should a Email enter.", Toast.LENGTH_SHORT, true).show();
                        }
                    }else {
                        Toasty.warning(NewPassword.this, "Password is short, at least 6 number.", Toast.LENGTH_LONG, true).show();
                    }
                }else {
                    Toasty.error(NewPassword.this, "Should a Password enter.", Toast.LENGTH_SHORT, true).show();
                }
            }
        });

    }
}
