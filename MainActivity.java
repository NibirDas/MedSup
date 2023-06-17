package com.example.medsup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

public class MainActivity extends AppCompatActivity
{
    public Button button_dis, button_ret, button_cus;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_dis = (Button) findViewById(R.id.dis);
        button_ret = (Button) findViewById(R.id.ret);
        button_cus = (Button) findViewById(R.id.cus);

        button_dis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
            }
        });

        button_ret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
            }
        });

        button_cus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
            }
        });

    }
}
