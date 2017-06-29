package com.example.cserhelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editText_no;
    private EditText editText_pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.button);
        editText_no = (EditText) findViewById(R.id.editText_no);
        editText_pwd = (EditText) findViewById(R.id.editText_pwd);
        /* TODO:Checkbox */
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                String inputNumber = editText_no.getText().toString();
                String inputPassword = editText_pwd.getText().toString();
                Log.d("MainActivity", inputNumber);
                Log.d("MainActivity", inputPassword);
                break;
            default:
                break;
        }
        Intent intent=new Intent(MainActivity.this,MainFuncActivity.class);
        this.startActivity(intent);
    }
}
