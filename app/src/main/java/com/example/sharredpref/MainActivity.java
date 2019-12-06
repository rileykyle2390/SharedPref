package com.example.sharredpref;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static com.example.sharredpref.R.*;

public class MainActivity extends AppCompatActivity {
    EditText name, email, password;
    Button save, retrieve, clear;

    private SharedPreferences pref;
    private String sharedPrefFile = "com.example.android.loginsharedprefs";

    @Override
    protected void onPause(){
        super.onPause();
        pref = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        SharedPreferences.Editor preferencesEditor = pref.edit();
        preferencesEditor.putString("NAME_KEY", name.getText().toString());
        preferencesEditor.putString("EMAIL_KEY", email.getText().toString());
        preferencesEditor.putString("PASS_KEY", password.getText().toString());
        preferencesEditor.commit();
    }

    @Override
    protected void onResume(){
        super.onResume();
        pref = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        name.setText(pref.getString("NAME_KEY",null));
        email.setText(pref.getString("EMAIL_KEY", null));
        password.setText(pref.getString("PASS_KEY", null));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText) findViewById(id.nameId);
        email = (EditText) findViewById(id.emailId);
        password = (EditText) findViewById(id.passwordId);
        save = (Button) findViewById(id.saveBut);
        retrieve = (Button) findViewById(id.retrieveBut);
        clear = (Button) findViewById(id.clearBut);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pref = getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("NAME_KEY", name.getText().toString());
                editor.putString("EMAIL_KEY", email.getText().toString());
                editor.putString("PASSWORD_KEY", password.getText().toString());
                editor.commit();
            }
        });

        retrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pref = getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE);
                name.setText(pref.getString("NAME_KEY",null));
                email.setText(pref.getString("EMAIL_KEY",null));
                password.setText(pref.getString("PASSWORD_KEY", null));
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name.setText("");
                email.setText("");
                password.setText("");
            }
        });

    }
}
