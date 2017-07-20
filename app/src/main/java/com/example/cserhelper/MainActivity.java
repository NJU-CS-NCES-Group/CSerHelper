package com.example.cserhelper;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private EditText accountEdit;
    private EditText passwordEdit;
    private Button login;
    private CheckBox rememberPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        accountEdit = (EditText) findViewById(R.id.editText_no);
        passwordEdit = (EditText) findViewById(R.id.editText_pwd);
        rememberPass = (CheckBox) findViewById(R.id.remember_pass);
        login = (Button) findViewById(R.id.login);
        boolean isRemember = pref.getBoolean("remember_password", false);
        if (isRemember) {
            //set username and password in EditText
            String account = pref.getString("account", "");
            String password = pref.getString("password", "");
            accountEdit.setText(account);
            passwordEdit.setText(password);
            rememberPass.setChecked(true);
        }
        /* TODO:Checkbox */

        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login:
                String inputNumber = accountEdit.getText().toString();
                String inputPassword = passwordEdit.getText().toString();
                Log.d("MainActivity", inputNumber);
                Log.d("MainActivity", inputPassword);
                if (inputNumber.equals("123456")&&inputPassword.equals("admin")) {
                    editor = pref.edit();
                    if (rememberPass.isChecked()) {
                        editor.putBoolean("remember_password", true);
                        editor.putString("account", inputNumber);
                        editor.putString("password", inputPassword);
                    } else {
                        editor.clear();
                    }
                    editor.apply();
                    Intent intent = new Intent(MainActivity.this, MainFuncActivity.class);
                    this.startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(MainActivity.this, "account or password is invalid",
                            Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }

    }
}
