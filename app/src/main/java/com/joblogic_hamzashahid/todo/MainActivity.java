package com.joblogic_hamzashahid.todo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.telecom.Call;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button Call_List_Btn, Buy_List_Btn , Sell_List_Btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Call_List_Btn = findViewById(R.id.call_list_btn);
        Buy_List_Btn = findViewById(R.id.buy_list_btn);
        Sell_List_Btn = findViewById(R.id.sell_list_btn);

        Call_List_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,Call_List_Activity.class );
                startActivity(i);
            }
        });
        Buy_List_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this,Buy_List_Activity.class );
                startActivity(i);
            }
        });
        Sell_List_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this,Sell_List_Activity.class );
                startActivity(i);

            }
        });
    }
}